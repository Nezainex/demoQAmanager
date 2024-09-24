package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BrowserWindowsPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BrowserWindowsTest {

    private BrowserWindowsPage browserWindowsPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
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
