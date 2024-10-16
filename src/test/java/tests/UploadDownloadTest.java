package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.UploadDownloadPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class UploadDownloadTest {

    private UploadDownloadPage uploadDownloadPage;
    private static String downloadsFolder;

    @BeforeClass
    public void setUpClass() {
        Allure.step("Настройка класса для скачивания и загрузки файла",
                () -> {
                    Allure.step("Установка WebDriver для Firefox", () -> WebDriverManager.firefoxdriver().setup());
                    Allure.step("Настройка браузера: firefox", () -> Configuration.browser = "firefox");
                    Allure.step("Создание временной директории для загрузок", () -> downloadsFolder = createDownloadsFolder());
                    Allure.step("Настройка папки для загрузок", () -> Configuration.downloadsFolder = downloadsFolder);
                    Allure.step("Настройка режима загрузки файлов: FileDownloadMode.FOLDER", () -> Configuration.fileDownload = FileDownloadMode.FOLDER);
                    Allure.step("Установка тайм-аута ожидания элементов: 5000ms", () -> Configuration.timeout = 5000);
                    Allure.step("Разрешение на перезапуск браузера при ошибке", () -> Configuration.reopenBrowserOnFail = true);
                    Allure.step("Установка стратегии загрузки страниц: eager", () -> Configuration.pageLoadStrategy = "eager");
                    Allure.step("Установка тайм-аута загрузки страниц: 30000ms", () -> Configuration.pageLoadTimeout = 30000);
                });
    }

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы загрузки и скачивания файла", () -> open("https://demoqa.com/upload-download"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
        Allure.step("Создание страницы UploadDownloadPage", () -> uploadDownloadPage = new UploadDownloadPage());
    }

    @Test(description = "Проверка скачивания файла", retryAnalyzer = RetryAnalyzer.class)
    public void testDownloadFile() {
        Allure.step("Тест скачивания файла",
                () -> {
                    File downloadedFile = downloadFile();
                    validateDownloadedFile(downloadedFile);
                });
    }

    @Test(description = "Проверка загрузки файла", retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "testDownloadFile")
    public void testUploadFile() {
        Allure.step("Тест загрузки файла",
                () -> {
                    Path downloadPath = findDownloadedFile(Paths.get(downloadsFolder));
                    validateFoundDownloadedFile(downloadPath);
                    assert downloadPath != null;
                    uploadFile(downloadPath.toFile());
                });
    }

    @AfterClass
    public void cleanUp() {
        Allure.step("Очистка временной директории после тестов",
                this::deleteDownloadsFolder);
    }

    // Helper methods with Step annotations

    private String createDownloadsFolder() {
        return Allure.step("Создание временной директории для загрузок",
                () -> Files.createTempDirectory("downloads").toAbsolutePath().toString());
    }

    private File downloadFile() {
        return Allure.step("Скачивание файла",
                () -> uploadDownloadPage.downloadFile());
    }

    private void validateDownloadedFile(File downloadedFile) {
        Allure.step("Валидация скачанного файла",
                () -> {
                    Assert.assertTrue(downloadedFile.exists(), "Файл не был скачан.");
                    Assert.assertEquals(downloadedFile.getName(), "sampleFile.jpeg", "Скачан неправильный файл.");
                });
    }

    private Path findDownloadedFile(Path downloadDir) {
        return Allure.step("Поиск скачанного файла в директории загрузок",
                () -> {
                    if (!Files.exists(downloadDir) || !Files.isDirectory(downloadDir)) {
                        System.out.println("Download directory does not exist: " + downloadDir);
                        return null;
                    }

                    for (int i = 0; i < 5; i++) {
                        try (Stream<Path> paths = Files.walk(downloadDir)) {
                            Path path = paths
                                    .filter(Files::isRegularFile)
                                    .filter(p -> p.getFileName().toString().equals("sampleFile.jpeg"))
                                    .findFirst()
                                    .orElse(null);

                            if (path != null) {
                                return path;
                            }

                            Thread.sleep(1000);
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                });
    }

    private void validateFoundDownloadedFile(Path downloadPath) {
        Allure.step("Валидация найденного скачанного файла",
                () -> {
                    Assert.assertNotNull(downloadPath, "Файл для загрузки не найден!");
                    Assert.assertTrue(Files.exists(downloadPath), "Файл для загрузки не найден!");
                });
    }

    private void uploadFile(File fileToUpload) {
        Allure.step("Загрузка файла на сервер",
                () -> {
                    uploadDownloadPage.uploadFile(fileToUpload);
                    Assert.assertEquals(fileToUpload.getName(), "sampleFile.jpeg", "Загружен неправильный файл.");
                });
    }

    private void deleteDownloadsFolder() {
        Allure.step("Удаление временной директории для загрузок",
                () -> {
                    File downloadsDir = new File(downloadsFolder);
                    if (downloadsDir.exists()) {
                        FileUtils.deleteDirectory(downloadsDir);
                    } else {
                        System.out.println("Директория для загрузок не существует: " + downloadsDir.getAbsolutePath());
                    }
                });
    }
}
