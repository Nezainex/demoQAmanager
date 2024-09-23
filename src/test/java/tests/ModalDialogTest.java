package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.codeborne.selenide.Configuration;
import pages.ModalDialogPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ModalDialogTest {

    private final ModalDialogPage modalDialogPage = new ModalDialogPage();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = false;  // Открытие нового браузера для каждого теста
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
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
    @AfterMethod
    public void tearDown() {
        try {
            // Закрываем все окна, если они существуют
            for (String handle : getWebDriver().getWindowHandles()) {
                getWebDriver().switchTo().window(handle).close();
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка при закрытии окон: " + e.getMessage());
        } finally {
            try {
                // Закрываем WebDriver, если сессия активна
                getWebDriver().quit();
            } catch (Exception e) {
                System.out.println("Ошибка при завершении сессии WebDriver: " + e.getMessage());
            }
        }
    }
}
