package tests;

import io.qameta.allure.Step;
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
    @Step("Открытие страницы Modal Dialogs и настройка окна")
    public void setUp() {
        open("https://demoqa.com/modal-dialogs");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Тест на открытие и закрытие Small Modal через кнопку 'Close'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Открытие и закрытие Small Modal")
    public void testOpenAndCloseSmallModal() {
        modalDialogPage.openSmallModal();
        modalDialogPage.closeSmallModalWithButton();
    }

    @Test(description = "Тест на проверку текста в Small Modal", retryAnalyzer = RetryAnalyzer.class)
    @Step("Проверка текста в Small Modal")
    public void testVerifySmallModalText() {
        modalDialogPage.openSmallModal();
        modalDialogPage.verifySmallModalText();
        modalDialogPage.closeSmallModalWithButton();
    }

    @Test(description = "Тест на открытие и закрытие Large Modal через кнопку 'Close'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Открытие и закрытие Large Modal")
    public void testOpenAndCloseLargeModal() {
        modalDialogPage.openLargeModal();
        modalDialogPage.closeLargeModalWithButton();
    }

    @Test(description = "Тест на проверку текста в Large Modal", retryAnalyzer = RetryAnalyzer.class)
    @Step("Проверка текста в Large Modal")
    public void testVerifyLargeModalText() {
        modalDialogPage.openLargeModal();
        modalDialogPage.verifyLargeModalText();
        modalDialogPage.closeLargeModalWithButton();
    }
}
