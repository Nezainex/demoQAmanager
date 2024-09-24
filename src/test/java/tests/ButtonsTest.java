package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import pages.ButtonsPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ButtonsTest {

    private ButtonsPage buttonsPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/buttons");
        getWebDriver().manage().window().maximize();
        buttonsPage = new ButtonsPage();
    }

    @Test(description = "Проверка двойного клика на кнопке", retryAnalyzer = RetryAnalyzer.class)
    public void testDoubleClickButton() {
        buttonsPage.doubleClickButton();
    }

    @Test(description = "Проверка правого клика на кнопке", retryAnalyzer = RetryAnalyzer.class)
    public void testRightClickButton() {
        buttonsPage.rightClickButton();
    }

    @Test(description = "Проверка обычного клика на кнопке", retryAnalyzer = RetryAnalyzer.class)
    public void testClickMeButton() {
        buttonsPage.clickMeButton();
    }
    @AfterMethod
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
