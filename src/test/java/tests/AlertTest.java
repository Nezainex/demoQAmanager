package tests;


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
}

