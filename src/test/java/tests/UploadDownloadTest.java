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
    public void setUpClass() throws IOException {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        // Используем временную директорию системы для загрузок
        downloadsFolder = Files.createTempDirectory("downloads").toAbsolutePath().toString();
        Configuration.downloadsFolder = downloadsFolder;
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";
        Configuration.pageLoadTimeout = 30000;
    }

    @BeforeMethod
    public void setUp() {
        open("https://demoqa.com/upload-download");
        getWebDriver().manage().window().maximize();
        uploadDownloadPage = new UploadDownloadPage();
    }

    @Test(description = "Проверка скачивания файла", retryAnalyzer = RetryAnalyzer.class)
    public void testDownloadFile() throws Exception {
        // Скачиваем файл
        File downloadedFile = uploadDownloadPage.downloadFile();

        // Проверяем, что файл существует в папке загрузок
        Assert.assertTrue(downloadedFile.exists(), "Файл не был скачан.");
        Assert.assertEquals(downloadedFile.getName(), "sampleFile.jpeg", "Скачан неправильный файл.");
    }

    @Test(description = "Проверка загрузки файла", retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "testDownloadFile")
    public void testUploadFile() {
        // Убеждаемся, что директория загрузок существует
        Path downloadDir = Paths.get(downloadsFolder);
        if (!Files.exists(downloadDir)) {
            System.out.println("Download directory does not exist: " + downloadDir);
            Assert.fail("Download directory does not exist.");
        }

        // Поиск файла в настроенной директории загрузок
        Path downloadPath = findDownloadedFile(downloadDir);

        // Проверяем, что файл найден
        Assert.assertNotNull(downloadPath, "Файл для загрузки не найден!");
        Assert.assertTrue(Files.exists(downloadPath), "Файл для загрузки не найден!");

        File fileToUpload = downloadPath.toFile();

        // Загрузка файла через форму
        uploadDownloadPage.uploadFile(fileToUpload);

        // Проверяем, что загруженный файл имеет правильное имя
        Assert.assertEquals(fileToUpload.getName(), "sampleFile.jpeg", "Загружен неправильный файл.");
    }

    /**
     * Метод для поиска загруженного файла по имени в указанной директории и ее поддиректориях.
     */
    private Path findDownloadedFile(Path downloadDir) {
        if (!Files.exists(downloadDir) || !Files.isDirectory(downloadDir)) {
            System.out.println("Download directory does not exist: " + downloadDir);
            return null;
        }

        for (int i = 0; i < 5; i++) {  // Попробуем до 5 раз найти файл
            try (Stream<Path> paths = Files.walk(downloadDir)) {
                Path path = paths
                        .filter(Files::isRegularFile)  // Только файлы
                        .filter(p -> p.getFileName().toString().equals("sampleFile.jpeg"))  // Проверяем имя файла
                        .findFirst()
                        .orElse(null);  // Возвращаем null, если файл не найден

                if (path != null) {
                    return path;  // Файл найден
                }

                Thread.sleep(1000);  // Задержка перед повторной попыткой
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;  // Если файл не найден за 5 попыток
    }

    @AfterClass
    public void cleanUp() throws IOException {
        File downloadsDir = new File(downloadsFolder);
        if (downloadsDir.exists()) {
            FileUtils.deleteDirectory(downloadsDir);  // Удаляем директорию и ее содержимое
        } else {
            System.out.println("Директория для загрузок не существует: " + downloadsDir.getAbsolutePath());
        }
    }
}
