package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import pages.ToolTipsPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ToolTipsTest {

    ToolTipsPage toolTipsPage = new ToolTipsPage();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/tool-tips");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Hover over the 'Hover me to see' button")
    public void hoverOverButton() {
        toolTipsPage.hoverOnButton();
        // Добавьте проверку появления всплывающей подсказки
    }

    @Test(description = "Hover over the text field", retryAnalyzer = RetryAnalyzer.class)
    public void hoverOverTextField() {
        toolTipsPage.hoverOnTextField();
        // Добавьте проверку появления всплывающей подсказки
    }

    @Test(description = "Hover over the 'Contrary' link", retryAnalyzer = RetryAnalyzer.class)
    public void hoverOverContraryLink() {
        toolTipsPage.hoverOnContraryLink();
        // Добавьте проверку появления всплывающей подсказки
    }

    @Test(description = "Hover over the '1.10.32' link", retryAnalyzer = RetryAnalyzer.class)
    public void hoverOverSectionLink() {
        toolTipsPage.hoverOnSectionLink();
        // Добавьте проверку появления всплывающей подсказки
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
