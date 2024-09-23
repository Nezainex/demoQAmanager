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
import java.util.UUID;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class UploadDownloadTest {

    private UploadDownloadPage uploadDownloadPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.downloadsFolder = "C:/Users/Username/Downloads/" + UUID.randomUUID(); // Указываем папку для загрузок
        Configuration.fileDownload = FileDownloadMode.FOLDER;  // Указываем метод скачивания файлов
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = false;  // Открытие нового браузера для каждого теста
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
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

    @Test(description = "Проверка загрузки файла", retryAnalyzer = RetryAnalyzer.class)
    public void testUploadFile() throws IOException {
        // Поиск файла во всех подпапках папки загрузок
        Path downloadPath = findDownloadedFile(Paths.get("C:/Users/Username/Downloads"));

        // Проверяем, что файл найден и существует
        Assert.assertNotNull(downloadPath, "Файл для загрузки не найден!");
        Assert.assertTrue(Files.exists(downloadPath), "Файл для загрузки не найден!");

        File fileToUpload = downloadPath.toFile();

        // Загрузка файла через форму
        uploadDownloadPage.uploadFile(fileToUpload);

        // Проверка, что загруженный файл имеет правильное имя
        Assert.assertEquals(fileToUpload.getName(), "sampleFile.jpeg", "Загружен неправильный файл.");
    }

    /**
     * Метод для поиска загруженного файла по имени во всех подпапках указанной директории.
     */
    private Path findDownloadedFile(Path downloadDir) throws IOException {
        for (int i = 0; i < 5; i++) {  // Попробуем до 5 раз найти файл
            try (Stream<Path> paths = Files.walk(downloadDir)) {
                Path path = paths
                        .filter(Files::isRegularFile)  // Фильтруем только файлы
                        .filter(p -> p.getFileName().toString().equals("sampleFile.jpeg"))  // Проверяем имя файла
                        .findFirst()
                        .orElse(null);  // Возвращаем null, если файл не найден

                if (path != null) {
                    return path;  // Файл найден
                }

                Thread.sleep(1000);  // Задержка в 1 секунду перед повторной попыткой
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;  // Если файл не найден за 5 попыток
    }

    @AfterMethod
    public void cleanUp() throws IOException {
        File downloadsDir = new File(Configuration.downloadsFolder);
        if (downloadsDir.exists()) {
            FileUtils.cleanDirectory(downloadsDir);  // Очищаем директорию, если она существует
        } else {
            System.out.println("Директория для загрузок не существует: " + downloadsDir.getAbsolutePath());
        }
    }
}
