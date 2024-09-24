package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import pages.TabsPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class TabsTest {

    private final TabsPage tabsPage = new TabsPage();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/tabs");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Тестирование открытия вкладки 'What'", retryAnalyzer = RetryAnalyzer.class)
    public void testTabWhat() {
        tabsPage.clickTabWhat();
        assert tabsPage.isWhatTabActive() : "Вкладка 'What' не активна.";
        assert tabsPage.isWhatPanelVisible() : "Панель вкладки 'What' не видна.";
    }

    @Test(description = "Тестирование открытия вкладки 'Origin'", retryAnalyzer = RetryAnalyzer.class)
    public void testTabOrigin() {
        tabsPage.clickTabOrigin();
        assert tabsPage.isOriginTabActive() : "Вкладка 'Origin' не активна.";
        assert tabsPage.isOriginPanelVisible() : "Панель вкладки 'Origin' не видна.";
    }

    @Test(description = "Тестирование открытия вкладки 'Use'", retryAnalyzer = RetryAnalyzer.class)
    public void testTabUse() {
        tabsPage.clickTabUse();
        assert tabsPage.isUseTabActive() : "Вкладка 'Use' не активна.";
        assert tabsPage.isUsePanelVisible() : "Панель вкладки 'Use' не видна.";
    }

    @Test(description = "Тестирование, что вкладка 'More' недоступна", retryAnalyzer = RetryAnalyzer.class)
    public void testTabMore() {
        // Проверяем, что вкладка 'More' недоступна (aria-disabled="true")
        assert tabsPage.isTabMoreDisabled() : "Вкладка 'More' должна быть недоступна (disabled), но она доступна.";
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
