package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import pages.AlertPage;
import utils.RetryAnalyzer;
import utils.TestListener;

@Listeners(TestListener.class)
public class AlertTest {

    private final AlertPage alertPage = new AlertPage();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = false;  // Открытие нового браузера для каждого теста
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/alerts");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Проверка простого алерта", retryAnalyzer = RetryAnalyzer.class)
    public void testSimpleAlert() {
        alertPage.clickAlertButton();
    }

    @Test(description = "Проверка алерта с таймером", retryAnalyzer = RetryAnalyzer.class)
    public void testTimerAlert() {
        alertPage.clickTimerAlertButton();
    }

    @Test(description = "Проверка подтверждения алерта (accept)", retryAnalyzer = RetryAnalyzer.class)
    public void testConfirmAlertAccept() {
        alertPage.clickConfirmButton(true);
    }

    @Test(description = "Проверка подтверждения алерта (dismiss)", retryAnalyzer = RetryAnalyzer.class)
    public void testConfirmAlertDismiss() {
        alertPage.clickConfirmButton(false);
    }

    @Test(description = "Проверка prompt алерта", retryAnalyzer = RetryAnalyzer.class)
    public void testPromptAlert() {
        alertPage.clickPromptButton("Тест");
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

