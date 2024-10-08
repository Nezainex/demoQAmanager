package tests;

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
        open("https://demoqa.com/modal-dialogs");
        getWebDriver().manage().window().maximize();
    }

    // Тест на открытие и закрытие Small Modal
    @Test(description = "Тест на открытие и закрытие Small Modal через кнопку 'Close", retryAnalyzer = RetryAnalyzer.class)
    public void testOpenAndCloseSmallModal() {
        modalDialogPage.openSmallModal();
        modalDialogPage.closeSmallModalWithButton();
    }

    // Тест на проверку текста в Small Modal
    @Test(description = "Тест на проверку текста в Small Modal", retryAnalyzer = RetryAnalyzer.class)
    public void testVerifySmallModalText() {
        modalDialogPage.openSmallModal();
        modalDialogPage.verifySmallModalText();
        modalDialogPage.closeSmallModalWithButton();
    }

    // Тест на открытие и закрытие Large Modal
    @Test(description = "Тест на открытие и закрытие Large Modal через кнопку 'Close", retryAnalyzer = RetryAnalyzer.class)
    public void testOpenAndCloseLargeModal() {
        modalDialogPage.openLargeModal();
        modalDialogPage.closeLargeModalWithButton();
    }

    // Тест на проверку текста в Large Modal
    @Test(description = "Тест на проверку текста в Large Modal", retryAnalyzer = RetryAnalyzer.class)
    public void testVerifyLargeModalText() {
        modalDialogPage.openLargeModal();
        modalDialogPage.verifyLargeModalText();
        modalDialogPage.closeLargeModalWithButton();
    }
}
