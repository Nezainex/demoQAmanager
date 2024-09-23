package pages;

import static com.codeborne.selenide.Selenide.$;
import static locators.UploadDownloadLocators.*;

import io.qameta.allure.Step;
import java.io.File;

public class UploadDownloadPage {

    @Step("Скачать файл")
    public File downloadFile() throws Exception {
        // Скачиваем файл с помощью метода Selenide download()
        File downloadedFile = $(DOWNLOAD_BUTTON).download();

        // Увеличим время ожидания, чтобы убедиться, что файл скачался полностью
        for (int i = 0; i < 5; i++) {
            if (downloadedFile.exists() && downloadedFile.length() > 0) {
                break;  // Файл успешно скачан
            }
            Thread.sleep(1000);  // Ждём секунду перед проверкой снова
        }

        // Проверяем, что файл скачан и не пустой
        if (!downloadedFile.exists() || downloadedFile.length() == 0) {
            throw new Exception("Файл не был корректно скачан или он пустой!");
        }

        return downloadedFile;
    }


    @Step("Загрузить файл")
    public void uploadFile(File file) {
        // Загрузка файла через форму
        $(UPLOAD_FILE_INPUT).uploadFile(file);
    }
}
