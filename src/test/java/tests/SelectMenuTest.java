package tests;

import org.testng.annotations.*;
import pages.SelectMenuPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SelectMenuTest extends BaseTest {

    SelectMenuPage selectMenuPage = new SelectMenuPage();

    @BeforeMethod
    @Step("Открытие страницы SelectMenu и настройка браузера")
    public void setUp() {
        open("https://demoqa.com/select-menu");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Test selecting value from 'Select Option'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест выбора значения из 'Select Option'")
    public void testSelectValue() {
        selectMenuPage.selectOptionByText(selectMenuPage.getSelectValue(), "Group 1, option 1");
    }

    @Test(description = "Test selecting title from 'Select Title'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест выбора заголовка из 'Select Title'")
    public void testSelectOne() {
        selectMenuPage.selectOptionByText(selectMenuPage.getSelectOne(), "Mr.");
    }

    @Test(description = "Test selecting option from Old Style Select Menu", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест выбора значения из Old Style Select Menu")
    public void testOldStyleSelectMenu() {
        selectMenuPage.getOldStyleSelectMenu().selectOptionContainingText("Green");
    }

    @Test(description = "Test multiselect dropdown", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест множественного выбора из dropdown")
    public void testMultiselectDropdown() {
        selectMenuPage.selectOptionByText(selectMenuPage.getMultiselectDropdown(), "Green");
        selectMenuPage.selectOptionByText(selectMenuPage.getMultiselectDropdown(), "Blue");
    }

    @Test(description = "Test selecting multiple options from Standard Multi Select", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест множественного выбора из Standard Multi Select")
    public void testStandardMultiSelect() {
        selectMenuPage.getStandardMultiSelect().selectOptionByValue("volvo");
        selectMenuPage.getStandardMultiSelect().selectOptionByValue("audi");
    }
}
