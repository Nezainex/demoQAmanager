package tests;

import org.testng.annotations.*;
import pages.TabsPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class TabsTest extends BaseTest {

    private final TabsPage tabsPage = new TabsPage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Tabs", () -> open("https://demoqa.com/tabs"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
    }

    @Test(description = "Тестирование открытия вкладки 'What'", retryAnalyzer = RetryAnalyzer.class)
    public void testTabWhat() {
        Allure.step("Тест открытия вкладки 'What'", () -> {
            tabsPage.clickTabWhat();
            assert tabsPage.isWhatTabActive() : "Вкладка 'What' не активна.";
            assert tabsPage.isWhatPanelVisible() : "Панель вкладки 'What' не видна.";
        });
    }

    @Test(description = "Тестирование открытия вкладки 'Origin'", retryAnalyzer = RetryAnalyzer.class)
    public void testTabOrigin() {
        Allure.step("Тест открытия вкладки 'Origin'", () -> {
            tabsPage.clickTabOrigin();
            assert tabsPage.isOriginTabActive() : "Вкладка 'Origin' не активна.";
            assert tabsPage.isOriginPanelVisible() : "Панель вкладки 'Origin' не видна.";
        });
    }

    @Test(description = "Тестирование открытия вкладки 'Use'", retryAnalyzer = RetryAnalyzer.class)
    public void testTabUse() {
        Allure.step("Тест открытия вкладки 'Use'", () -> {
            tabsPage.clickTabUse();
            assert tabsPage.isUseTabActive() : "Вкладка 'Use' не активна.";
            assert tabsPage.isUsePanelVisible() : "Панель вкладки 'Use' не видна.";
        });
    }

    @Test(description = "Тестирование, что вкладка 'More' недоступна", retryAnalyzer = RetryAnalyzer.class)
    public void testTabMore() {
        Allure.step("Тест проверки доступности вкладки 'More'", () -> {
            assert tabsPage.isTabMoreDisabled() : "Вкладка 'More' должна быть недоступна (disabled), но она доступна.";
        });
    }
}
