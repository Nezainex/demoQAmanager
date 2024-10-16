package tests;

import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.FramePage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class FrameTest extends BaseTest {

    private final FramePage framePage = new FramePage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Frames", () -> open("https://demoqa.com/frames"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
    }

    @Test(description = "Проверка фрейма 1", retryAnalyzer = RetryAnalyzer.class)
    public void testFrameOne() {
        Allure.step("Переключение на фрейм 1", framePage::switchToFrameOne);
    }

    @Test(description = "Проверка фрейма 2", retryAnalyzer = RetryAnalyzer.class)
    public void testFrameTwo() {
        Allure.step("Переключение на фрейм 2", framePage::switchToFrameTwo);
    }
}
