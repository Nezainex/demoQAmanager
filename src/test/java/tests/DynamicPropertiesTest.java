package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DynamicPropertiesPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class DynamicPropertiesTest {

    private DynamicPropertiesPage dynamicPropertiesPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/dynamic-properties");
        getWebDriver().manage().window().maximize();
        dynamicPropertiesPage = new DynamicPropertiesPage();
    }

    @Test(description = "Проверка, что кнопка становится активной через 5 секунд", retryAnalyzer = RetryAnalyzer.class)
    public void testEnableButton() {
        dynamicPropertiesPage.waitForEnableButton();
    }

    @Test(description = "Проверка, что кнопка меняет цвет через 5 секунд", retryAnalyzer = RetryAnalyzer.class)
    public void testColorChange() {
        dynamicPropertiesPage.verifyColorChange();
    }

    @Test(description = "Проверка, что кнопка становится видимой через 5 секунд", retryAnalyzer = RetryAnalyzer.class)
    public void testVisibleAfterButton() {
        dynamicPropertiesPage.waitForVisibleButton();
    }

    @Test(description = "Проверка наличия текста с рандомным id", retryAnalyzer = RetryAnalyzer.class)
    public void testRandomText() {
        dynamicPropertiesPage.verifyRandomText();
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
