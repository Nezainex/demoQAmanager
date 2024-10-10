package tests;

import io.qameta.allure.Step;
import org.testng.annotations.*;
import pages.NestedFramePage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class NestedFrameTest extends BaseTest {

    private final NestedFramePage nestedFramePage = new NestedFramePage();

    @BeforeMethod
    @Step("Открытие страницы Nested Frames и настройка окна")
    public void setUp() {
        open("https://demoqa.com/nestedframes");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Проверка родительского фрейма", retryAnalyzer = RetryAnalyzer.class)
    @Step("Переключение на родительский фрейм")
    public void testParentFrame() {
        nestedFramePage.switchToParentFrame();
    }

    @Test(description = "Проверка вложенного фрейма", retryAnalyzer = RetryAnalyzer.class)
    @Step("Переключение на вложенный фрейм")
    public void testChildFrame() {
        nestedFramePage.switchToChildFrame();
    }

    @Test(description = "Проверка количества iframe на главной странице", retryAnalyzer = RetryAnalyzer.class)
    @Step("Проверка количества iframe на главной странице")
    public void testIframeCountInMainPage() {
        System.out.println("Number of iframes on the main page: " + nestedFramePage.getIFrameCountInMainPage());
    }

    @Test(description = "Проверка количества iframe в родительском фрейме", retryAnalyzer = RetryAnalyzer.class)
    @Step("Проверка количества iframe в родительском фрейме")
    public void testIframeCountInParentFrame() {
        System.out.println("Number of iframes in the parent frame: " + nestedFramePage.getIFrameCountInParentFrame());
    }
}
