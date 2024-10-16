package tests;

import io.qameta.allure.Allure;
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
    public void setUp() {
        Allure.step("Открытие страницы Nested Frames", () -> open("https://demoqa.com/nestedframes"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
    }

    @Test(description = "Проверка родительского фрейма", retryAnalyzer = RetryAnalyzer.class)
    public void testParentFrame() {
        Allure.step("Переключение на родительский фрейм", nestedFramePage::switchToParentFrame);
    }

    @Test(description = "Проверка вложенного фрейма", retryAnalyzer = RetryAnalyzer.class)
    public void testChildFrame() {
        Allure.step("Переключение на вложенный фрейм", nestedFramePage::switchToChildFrame);
    }

    @Test(description = "Проверка количества iframe на главной странице", retryAnalyzer = RetryAnalyzer.class)
    public void testIframeCountInMainPage() {
        Allure.step("Проверка количества iframe на главной странице", () -> {
            int iframeCount = nestedFramePage.getIFrameCountInMainPage();
            System.out.println("Number of iframes on the main page: " + iframeCount);
        });
    }

    @Test(description = "Проверка количества iframe в родительском фрейме", retryAnalyzer = RetryAnalyzer.class)
    public void testIframeCountInParentFrame() {
        Allure.step("Проверка количества iframe в родительском фрейме", () -> {
            int iframeCount = nestedFramePage.getIFrameCountInParentFrame();
            System.out.println("Number of iframes in the parent frame: " + iframeCount);
        });
    }
}
