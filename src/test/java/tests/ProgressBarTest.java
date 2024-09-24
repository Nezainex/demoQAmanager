package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import pages.ProgressBarPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ProgressBarTest {

    private final ProgressBarPage progressBarPage = new ProgressBarPage();

    @BeforeClass
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.pageLoadStrategy = "eager";  // Tests start immediately after DOM loads
        Configuration.pageLoadTimeout = 30000;  // Max 30 seconds for full page load
        open("https://demoqa.com/progress-bar");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Testing progress bar completion to 100%", retryAnalyzer = RetryAnalyzer.class)
    public void testProgressBarComplete() {
        progressBarPage.clickStartStopButton();  // Start progress bar
        progressBarPage.waitForProgressBarToComplete();  // Wait until progress reaches 100%
    }

    @Test(description = "Testing progress bar reset", retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "testProgressBarComplete")
    public void testResetProgressBar() {
        progressBarPage.clickResetButton();  // Reset progress bar
        progressBarPage.waitForProgressBarToReset();  // Wait until progress resets to 0%
    }

    @AfterClass
    public void tearDown() {
        try {
            // Закрываем все окна, если они существуют
            for (String handle : getWebDriver().getWindowHandles()) {
                getWebDriver().switchTo().window(handle).close();
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка при закрытии окон: " + e.getMessage());
        } finally {
            try {
                // Закрываем WebDriver, если сессия активна
                getWebDriver().quit();
            } catch (Exception e) {
                System.out.println("Ошибка при завершении сессии WebDriver: " + e.getMessage());
            }
        }
    }
}
