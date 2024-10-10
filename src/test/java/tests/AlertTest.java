package tests;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import pages.AlertPage;
import utils.RetryAnalyzer;
import utils.TestListener;

@Listeners(TestListener.class)
public class AlertTest extends BaseTest {

    private final AlertPage alertPage = new AlertPage();

    @BeforeMethod
    @Step("Открытие страницы с алертами")
    public void setUp() {
        open("https://demoqa.com/alerts");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Проверка простого алерта", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тестирование простого алерта")
    public void testSimpleAlert() {
        alertPage.clickAlertButton();
    }

    @Test(description = "Проверка алерта с таймером", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тестирование алерта с таймером")
    public void testTimerAlert() {
        alertPage.clickTimerAlertButton();
    }

    @Test(description = "Проверка подтверждения алерта (accept)", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тестирование подтверждения алерта с нажатием 'accept'")
    public void testConfirmAlertAccept() {
        alertPage.clickConfirmButton(true);
    }

    @Test(description = "Проверка подтверждения алерта (dismiss)", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тестирование подтверждения алерта с нажатием 'dismiss'")
    public void testConfirmAlertDismiss() {
        alertPage.clickConfirmButton(false);
    }

    @Test(description = "Проверка prompt алерта", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тестирование prompt алерта с вводом текста")
    public void testPromptAlert() {
        alertPage.clickPromptButton("Тест");
    }
}
