package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BrowserWindowsPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BrowserWindowsTest extends BaseTest {

    private BrowserWindowsPage browserWindowsPage;

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Browser Windows", () -> open("https://demoqa.com/browser-windows"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
        Allure.step("Создание страницы BrowserWindowsPage", () -> browserWindowsPage = new BrowserWindowsPage());
    }

    @Test(description = "Открыть новую вкладку и проверить контент", retryAnalyzer = RetryAnalyzer.class)
    public void testNewTab() {
        Allure.step("Открытие новой вкладки", browserWindowsPage::openNewTab);
    }

    @Test(description = "Открыть новое окно и проверить контент", retryAnalyzer = RetryAnalyzer.class)
    public void testNewWindow() {
        Allure.step("Открытие нового окна", browserWindowsPage::openNewWindow);
    }

    @Test(description = "Открыть новое окно с сообщением и проверить текст", retryAnalyzer = RetryAnalyzer.class)
    public void testNewWindowMessage() {
        Allure.step("Открытие нового окна с сообщением", browserWindowsPage::openNewWindowMessage);
    }
}
