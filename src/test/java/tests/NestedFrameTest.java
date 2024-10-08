package tests;

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
        open("https://demoqa.com/nestedframes");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Проверка родительского фрейма", retryAnalyzer = RetryAnalyzer.class)
    public void testParentFrame() {
        nestedFramePage.switchToParentFrame(); // Тест на родительский фрейм
    }

    @Test(description = "Проверка вложенного фрейма", retryAnalyzer = RetryAnalyzer.class)
    public void testChildFrame() {
        nestedFramePage.switchToChildFrame(); // Тест на вложенный фрейм
    }

    @Test(description = "Проверка количества iframe на главной странице", retryAnalyzer = RetryAnalyzer.class)
    public void testIframeCountInMainPage() {
        System.out.println("Number of iframes on the main page: " + nestedFramePage.getIFrameCountInMainPage());
    }

    @Test(description = "Проверка количества iframe в родительском фрейме", retryAnalyzer = RetryAnalyzer.class)
    public void testIframeCountInParentFrame() {
        System.out.println("Number of iframes in the parent frame: " + nestedFramePage.getIFrameCountInParentFrame());
    }
}
