package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AlertPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class AlertTest extends BaseTest {

    private final AlertPage alertPage = new AlertPage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы с алертами", () -> {
            open("https://demoqa.com/alerts");
            getWebDriver().manage().window().maximize();
        });
    }

    @Test(description = "Проверка простого алерта", retryAnalyzer = RetryAnalyzer.class)
    public void testSimpleAlert() {
        Allure.step("Тестирование простого алерта", alertPage::clickAlertButton);
    }

    @Test(description = "Проверка алерта с таймером", retryAnalyzer = RetryAnalyzer.class)
    public void testTimerAlert() {
        Allure.step("Тестирование алерта с таймером", alertPage::clickTimerAlertButton);
    }

    @Test(description = "Проверка подтверждения алерта (accept)", retryAnalyzer = RetryAnalyzer.class)
    public void testConfirmAlertAccept() {
        Allure.step("Тестирование подтверждения алерта с нажатием 'accept'", () -> alertPage.clickConfirmButton(true));
    }

    @Test(description = "Проверка подтверждения алерта (dismiss)", retryAnalyzer = RetryAnalyzer.class)
    public void testConfirmAlertDismiss() {
        Allure.step("Тестирование подтверждения алерта с нажатием 'dismiss'", () -> alertPage.clickConfirmButton(false));
    }

    @Test(description = "Проверка prompt алерта", retryAnalyzer = RetryAnalyzer.class)
    public void testPromptAlert() {
        Allure.step("Тестирование prompt алерта с вводом текста", () -> alertPage.clickPromptButton("Тест"));
    }
}
