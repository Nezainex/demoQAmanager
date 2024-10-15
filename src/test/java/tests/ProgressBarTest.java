package tests;

import io.qameta.allure.Allure;
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
        Allure.step("Настройка окружения для теста ProgressBar", () -> {
            WebDriverManager.firefoxdriver().setup();
            Configuration.browser = "firefox";
            Configuration.timeout = 5000;
            Configuration.pageLoadStrategy = "eager";
            Configuration.pageLoadTimeout = 30000;
            open("https://demoqa.com/progress-bar");
            getWebDriver().manage().window().maximize();
        });
    }

    @Test(description = "Testing progress bar completion to 100%", retryAnalyzer = RetryAnalyzer.class)
    public void testProgressBarComplete() {
        Allure.step("Запуск прогресс-бара и ожидание завершения до 100%", () -> {
            progressBarPage.clickStartStopButton();
            progressBarPage.waitForProgressBarToComplete();
        });
    }

    @Test(description = "Testing progress bar reset", retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "testProgressBarComplete")
    public void testResetProgressBar() {
        Allure.step("Сброс прогресс-бара", () -> {
            progressBarPage.clickResetButton();
            progressBarPage.waitForProgressBarToReset();
        });
    }

    @AfterClass
    public void tearDown() {
        Allure.step("Закрытие браузера после теста", () -> {
            try {
                for (String handle : getWebDriver().getWindowHandles()) {
                    getWebDriver().switchTo().window(handle).close();
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка при закрытии окон: " + e.getMessage());
            } finally {
                try {
                    getWebDriver().quit();
                } catch (Exception e) {
                    System.out.println("Ошибка при завершении сессии WebDriver: " + e.getMessage());
                }
            }
        });
    }
}
