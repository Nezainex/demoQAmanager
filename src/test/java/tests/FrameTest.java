package tests;

import io.qameta.allure.Step;
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
    @Step("Открытие страницы Frames и настройка окна")
    public void setUp() {
        open("https://demoqa.com/frames");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Проверка фрейма 1", retryAnalyzer = RetryAnalyzer.class)
    @Step("Переключение на фрейм 1")
    public void testFrameOne() {
        framePage.switchToFrameOne();
    }

    @Test(description = "Проверка фрейма 2", retryAnalyzer = RetryAnalyzer.class)
    @Step("Переключение на фрейм 2")
    public void testFrameTwo() {
        framePage.switchToFrameTwo();
    }
}
