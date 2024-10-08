package tests;

import org.testng.annotations.*;
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
        open("https://demoqa.com/frames");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Проверка фрейма 1", retryAnalyzer = RetryAnalyzer.class)
    public void testFrameOne() {
        framePage.switchToFrameOne();
    }

    @Test(description = "Проверка фрейма 2", retryAnalyzer = RetryAnalyzer.class)
    public void testFrameTwo() {
        framePage.switchToFrameTwo();
    }
}
