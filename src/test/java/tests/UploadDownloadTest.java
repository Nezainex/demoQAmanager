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
import io.qameta.allure.Step;

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
    @Step("Настройка класса для скачивания и загрузки файла")
    public void setUpClass() throws IOException {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        downloadsFolder = createDownloadsFolder();
        Configuration.downloadsFolder = downloadsFolder;
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";
        Configuration.pageLoadTimeout = 30000;
    }

    @BeforeMethod
    @Step("Открытие страницы загрузки и скачивания файла")
    public void setUp() {
        open("https://demoqa.com/upload-download");
        getWebDriver().manage().window().maximize();
        uploadDownloadPage = new UploadDownloadPage();
    }

    @Test(description = "Проверка скачивания файла", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест скачивания файла")
    public void testDownloadFile() throws Exception {
        File downloadedFile = downloadFile();
        validateDownloadedFile(downloadedFile);
    }

    @Test(description = "Проверка загрузки файла", retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "testDownloadFile")
    @Step("Тест загрузки файла")
    public void testUploadFile() {
        Path downloadPath = findDownloadedFile(Paths.get(downloadsFolder));
        validateFoundDownloadedFile(downloadPath);
        assert downloadPath != null;
        uploadFile(downloadPath.toFile());
    }

    @AfterClass
    @Step("Очистка временной директории после тестов")
    public void cleanUp() throws IOException {
        deleteDownloadsFolder();
    }

    // Helper methods with Step annotations
    @Step("Создание временной директории для загрузок")
    private String createDownloadsFolder() throws IOException {
        return Files.createTempDirectory("downloads").toAbsolutePath().toString();
    }

    @Step("Скачивание файла")
    private File downloadFile() throws Exception {
        return uploadDownloadPage.downloadFile();
    }

    @Step("Валидация скачанного файла")
    private void validateDownloadedFile(File downloadedFile) {
        Assert.assertTrue(downloadedFile.exists(), "Файл не был скачан.");
        Assert.assertEquals(downloadedFile.getName(), "sampleFile.jpeg", "Скачан неправильный файл.");
    }

    @Step("Поиск скачанного файла в директории загрузок")
    private Path findDownloadedFile(Path downloadDir) {
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
    }

    @Step("Валидация найденного скачанного файла")
    private void validateFoundDownloadedFile(Path downloadPath) {
        Assert.assertNotNull(downloadPath, "Файл для загрузки не найден!");
        Assert.assertTrue(Files.exists(downloadPath), "Файл для загрузки не найден!");
    }

    @Step("Загрузка файла на сервер")
    private void uploadFile(File fileToUpload) {
        uploadDownloadPage.uploadFile(fileToUpload);
        Assert.assertEquals(fileToUpload.getName(), "sampleFile.jpeg", "Загружен неправильный файл.");
    }

    @Step("Удаление временной директории для загрузок")
    private void deleteDownloadsFolder() throws IOException {
        File downloadsDir = new File(downloadsFolder);
        if (downloadsDir.exists()) {
            FileUtils.deleteDirectory(downloadsDir);
        } else {
            System.out.println("Директория для загрузок не существует: " + downloadsDir.getAbsolutePath());
        }
    }
}
