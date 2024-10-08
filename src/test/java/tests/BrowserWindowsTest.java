package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BrowserWindowsPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BrowserWindowsTest extends BaseTest {

    private BrowserWindowsPage browserWindowsPage;

    @BeforeMethod
    public void setUp() {
        open("https://demoqa.com/browser-windows");
        getWebDriver().manage().window().maximize();
        browserWindowsPage = new BrowserWindowsPage();
    }

    @Test(description = "Открыть новую вкладку и проверить контент", retryAnalyzer = RetryAnalyzer.class)
    public void testNewTab() {
        browserWindowsPage.openNewTab();
    }

    @Test(description = "Открыть новое окно и проверить контент", retryAnalyzer = RetryAnalyzer.class)
    public void testNewWindow() {
        browserWindowsPage.openNewWindow();
    }

    @Test(description = "Открыть новое окно с сообщением и проверить текст", retryAnalyzer = RetryAnalyzer.class)
    public void testNewWindowMessage() {
        browserWindowsPage.openNewWindowMessage();
    }
}
