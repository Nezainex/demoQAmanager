package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import pages.SelectMenuPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SelectMenuTest {

    SelectMenuPage selectMenuPage = new SelectMenuPage();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = false;  // Открытие нового браузера для каждого теста
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/select-menu");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Test selecting value from 'Select Option'", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectValue() {
        selectMenuPage.selectOptionByText(selectMenuPage.getSelectValue(), "Group 1, option 1");
    }

    @Test(description = "Test selecting title from 'Select Title'", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectOne() {
        selectMenuPage.selectOptionByText(selectMenuPage.getSelectOne(), "Mr.");
    }

    @Test(description = "Test selecting option from Old Style Select Menu", retryAnalyzer = RetryAnalyzer.class)
    public void testOldStyleSelectMenu() {
        selectMenuPage.getOldStyleSelectMenu().selectOptionContainingText("Green");
    }

    @Test(description = "Test multiselect dropdown", retryAnalyzer = RetryAnalyzer.class)
    public void testMultiselectDropdown() {
        selectMenuPage.selectOptionByText(selectMenuPage.getMultiselectDropdown(), "Green");
        selectMenuPage.selectOptionByText(selectMenuPage.getMultiselectDropdown(), "Blue");  // Можно выбрать несколько значений
    }

    @Test(description = "Test selecting multiple options from Standard Multi Select", retryAnalyzer = RetryAnalyzer.class)
    public void testStandardMultiSelect() {
        selectMenuPage.getStandardMultiSelect().selectOptionByValue("volvo");
        selectMenuPage.getStandardMultiSelect().selectOptionByValue("audi");
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
