package tests;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ButtonsPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ButtonsTest extends BaseTest {

    private ButtonsPage buttonsPage;

    @BeforeMethod
    @Step("Открытие страницы Buttons и настройка окна")
    public void setUp() {
        open("https://demoqa.com/buttons");
        getWebDriver().manage().window().maximize();
        buttonsPage = new ButtonsPage();
    }

    @Test(description = "Проверка двойного клика на кнопке", retryAnalyzer = RetryAnalyzer.class)
    @Step("Двойной клик на кнопке")
    public void testDoubleClickButton() {
        buttonsPage.doubleClickButton();
    }

    @Test(description = "Проверка правого клика на кнопке", retryAnalyzer = RetryAnalyzer.class)
    @Step("Правый клик на кнопке")
    public void testRightClickButton() {
        buttonsPage.rightClickButton();
    }

    @Test(description = "Проверка обычного клика на кнопке", retryAnalyzer = RetryAnalyzer.class)
    @Step("Обычный клик на кнопке")
    public void testClickMeButton() {
        buttonsPage.clickMeButton();
    }
}
