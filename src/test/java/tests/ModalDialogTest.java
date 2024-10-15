package tests;

import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ModalDialogPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ModalDialogTest extends BaseTest {

    private final ModalDialogPage modalDialogPage = new ModalDialogPage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Modal Dialogs и настройка окна", () -> {
            open("https://demoqa.com/modal-dialogs");
            getWebDriver().manage().window().maximize();
        });
    }

    @Test(description = "Тест на открытие и закрытие Small Modal через кнопку 'Close'", retryAnalyzer = RetryAnalyzer.class)
    public void testOpenAndCloseSmallModal() {
        Allure.step("Открытие Small Modal", modalDialogPage::openSmallModal);
        Allure.step("Закрытие Small Modal через кнопку 'Close'", modalDialogPage::closeSmallModalWithButton);
    }

    @Test(description = "Тест на проверку текста в Small Modal", retryAnalyzer = RetryAnalyzer.class)
    public void testVerifySmallModalText() {
        Allure.step("Открытие Small Modal", modalDialogPage::openSmallModal);
        Allure.step("Проверка текста в Small Modal", modalDialogPage::verifySmallModalText);
        Allure.step("Закрытие Small Modal через кнопку 'Close'", modalDialogPage::closeSmallModalWithButton);
    }

    @Test(description = "Тест на открытие и закрытие Large Modal через кнопку 'Close'", retryAnalyzer = RetryAnalyzer.class)
    public void testOpenAndCloseLargeModal() {
        Allure.step("Открытие Large Modal", modalDialogPage::openLargeModal);
        Allure.step("Закрытие Large Modal через кнопку 'Close'", modalDialogPage::closeLargeModalWithButton);
    }

    @Test(description = "Тест на проверку текста в Large Modal", retryAnalyzer = RetryAnalyzer.class)
    public void testVerifyLargeModalText() {
        Allure.step("Открытие Large Modal", modalDialogPage::openLargeModal);
        Allure.step("Проверка текста в Large Modal", modalDialogPage::verifyLargeModalText);
        Allure.step("Закрытие Large Modal через кнопку 'Close'", modalDialogPage::closeLargeModalWithButton);
    }
}
