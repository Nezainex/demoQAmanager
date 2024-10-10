package tests;

import org.testng.annotations.*;
import pages.TabsPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class TabsTest extends BaseTest {

    private final TabsPage tabsPage = new TabsPage();

    @BeforeMethod
    @Step("Открытие страницы Tabs и настройка браузера")
    public void setUp() {
        open("https://demoqa.com/tabs");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Тестирование открытия вкладки 'What'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест открытия вкладки 'What'")
    public void testTabWhat() {
        tabsPage.clickTabWhat();
        assert tabsPage.isWhatTabActive() : "Вкладка 'What' не активна.";
        assert tabsPage.isWhatPanelVisible() : "Панель вкладки 'What' не видна.";
    }

    @Test(description = "Тестирование открытия вкладки 'Origin'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест открытия вкладки 'Origin'")
    public void testTabOrigin() {
        tabsPage.clickTabOrigin();
        assert tabsPage.isOriginTabActive() : "Вкладка 'Origin' не активна.";
        assert tabsPage.isOriginPanelVisible() : "Панель вкладки 'Origin' не видна.";
    }

    @Test(description = "Тестирование открытия вкладки 'Use'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест открытия вкладки 'Use'")
    public void testTabUse() {
        tabsPage.clickTabUse();
        assert tabsPage.isUseTabActive() : "Вкладка 'Use' не активна.";
        assert tabsPage.isUsePanelVisible() : "Панель вкладки 'Use' не видна.";
    }

    @Test(description = "Тестирование, что вкладка 'More' недоступна", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест проверки доступности вкладки 'More'")
    public void testTabMore() {
        assert tabsPage.isTabMoreDisabled() : "Вкладка 'More' должна быть недоступна (disabled), но она доступна.";
    }
}
