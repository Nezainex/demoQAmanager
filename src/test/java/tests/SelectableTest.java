package tests;

import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SelectablePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SelectableTest {

    private SelectablePage selectablePage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = false;  // Открытие нового браузера для каждого теста
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/selectable");
        getWebDriver().manage().window().maximize();
        selectablePage = new SelectablePage();
    }

    @Test(description = "Тест выбора элементов из списка", retryAnalyzer = RetryAnalyzer.class)
    public void testListSelectable() {
        selectablePage.getListTab().click();

        // Выбор первого элемента
        selectablePage.selectItem(selectablePage.getListItems(), 0);
        Assert.assertTrue(selectablePage.isItemSelected(selectablePage.getListItems(), 0), "Первый элемент должен быть выбран.");

        // Снятие выбора
        selectablePage.selectItem(selectablePage.getListItems(), 0);
        Assert.assertFalse(selectablePage.isItemSelected(selectablePage.getListItems(), 0), "Первый элемент должен быть снят с выбора.");
    }

    @Test(description = "Тест выбора элементов из сетки", retryAnalyzer = RetryAnalyzer.class)
    public void testGridSelectable() {
        selectablePage.getGridTab().click();

        // Выбор третьего элемента
        selectablePage.selectItem(selectablePage.getGridItems(), 2);
        Assert.assertTrue(selectablePage.isItemSelected(selectablePage.getGridItems(), 2), "Третий элемент должен быть выбран.");

        // Снятие выбора
        selectablePage.selectItem(selectablePage.getGridItems(), 2);
        Assert.assertFalse(selectablePage.isItemSelected(selectablePage.getGridItems(), 2), "Третий элемент должен быть снят с выбора.");
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
