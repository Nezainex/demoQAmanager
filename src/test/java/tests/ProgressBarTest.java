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
            Allure.step("Установка WebDriver для Firefox", () -> WebDriverManager.firefoxdriver().setup());
            Allure.step("Настройка браузера: firefox", () -> Configuration.browser = "firefox");
            Allure.step("Установка тайм-аута ожидания элементов: 5000ms", () -> Configuration.timeout = 5000);
            Allure.step("Разрешение на перезапуск браузера при ошибке", () -> Configuration.reopenBrowserOnFail = true);
            Allure.step("Установка стратегии загрузки страниц: eager", () -> Configuration.pageLoadStrategy = "eager");
            Allure.step("Установка тайм-аута загрузки страниц: 30000ms", () -> Configuration.pageLoadTimeout = 30000);
            Allure.step("Отключение headless режима (браузер с графическим интерфейсом)", () -> Configuration.headless = false);
        });

        // Открытие страницы прогресс-бара
        Allure.step("Открытие страницы прогресс-бара", () -> open("https://demoqa.com/progress-bar"));

        // Максимизация окна браузера после открытия страницы
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
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
