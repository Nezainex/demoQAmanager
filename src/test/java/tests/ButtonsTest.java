package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ButtonsPage;
import pages.UploadDownloadPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ButtonsTest extends BaseTest {

    private ButtonsPage buttonsPage;

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Buttons", () -> open("https://demoqa.com/buttons"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
        Allure.step("Создание страницы ButtonsPage", () -> buttonsPage = new ButtonsPage());
    }

    @Test(description = "Проверка двойного клика на кнопке", retryAnalyzer = RetryAnalyzer.class)
    public void testDoubleClickButton() {
        Allure.step("Двойной клик на кнопке", buttonsPage::doubleClickButton);
    }

    @Test(description = "Проверка правого клика на кнопке", retryAnalyzer = RetryAnalyzer.class)
    public void testRightClickButton() {
        Allure.step("Правый клик на кнопке", buttonsPage::rightClickButton);
    }

    @Test(description = "Проверка обычного клика на кнопке", retryAnalyzer = RetryAnalyzer.class)
    public void testClickMeButton() {
        Allure.step("Обычный клик на кнопке", buttonsPage::clickMeButton);
    }
}
