package tests;

import org.testng.annotations.*;
import pages.SelectMenuPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SelectMenuTest extends BaseTest {

    SelectMenuPage selectMenuPage = new SelectMenuPage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы SelectMenu и настройка браузера", () -> {
            open("https://demoqa.com/select-menu");
            getWebDriver().manage().window().maximize();
        });
    }

    @Test(description = "Test selecting value from 'Select Option'", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectValue() {
        Allure.step("Тест выбора значения из 'Select Option'", () -> selectMenuPage.selectOptionByText(selectMenuPage.getSelectValue(), "Group 1, option 1"));
    }

    @Test(description = "Test selecting title from 'Select Title'", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectOne() {
        Allure.step("Тест выбора заголовка из 'Select Title'", () -> selectMenuPage.selectOptionByText(selectMenuPage.getSelectOne(), "Mr."));
    }

    @Test(description = "Test selecting option from Old Style Select Menu", retryAnalyzer = RetryAnalyzer.class)
    public void testOldStyleSelectMenu() {
        Allure.step("Тест выбора значения из Old Style Select Menu", () -> selectMenuPage.getOldStyleSelectMenu().selectOptionContainingText("Green"));
    }

    @Test(description = "Test multiselect dropdown", retryAnalyzer = RetryAnalyzer.class)
    public void testMultiselectDropdown() {
        Allure.step("Тест множественного выбора из dropdown", () -> {
            selectMenuPage.selectOptionByText(selectMenuPage.getMultiselectDropdown(), "Green");
            selectMenuPage.selectOptionByText(selectMenuPage.getMultiselectDropdown(), "Blue");
        });
    }

    @Test(description = "Test selecting multiple options from Standard Multi Select", retryAnalyzer = RetryAnalyzer.class)
    public void testStandardMultiSelect() {
        Allure.step("Тест множественного выбора из Standard Multi Select", () -> {
            selectMenuPage.getStandardMultiSelect().selectOptionByValue("volvo");
            selectMenuPage.getStandardMultiSelect().selectOptionByValue("audi");
        });
    }
}
