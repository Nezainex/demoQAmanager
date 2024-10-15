package tests;

import io.qameta.allure.Allure;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DatePickerPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class DatePickerTest extends BaseTest {

    private final DatePickerPage datePickerPage = new DatePickerPage();

    @BeforeMethod
    public void setUpBrowser() {
        Allure.step("Открытие страницы Date Picker", () -> open("https://demoqa.com/date-picker"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
    }

    @Test(description = "Тестирование ввода даты вручную в первом DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testSetDateByTextInFirstPicker() {
        Allure.step("Ввод даты вручную в первом DatePicker", () -> datePickerPage.setDateByTextInFirstPicker("08/08/2023"));
    }

    @Test(description = "Тестирование выбора даты через селекторы в первом DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectDateInFirstPicker() {
        Allure.step("Выбор даты через селекторы в первом DatePicker",
                () -> datePickerPage.selectDateInFirstPicker("August", "2023"));
    }

    @Test(description = "Тестирование выбора времени во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectTimeInSecondPicker() {
        Allure.step("Выбор времени во втором DatePicker", () -> datePickerPage.selectTimeInSecondPicker("03:00"));
    }

    @Test(description = "Тестирование уменьшения даты и времени во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testDecreaseDateAndTimeInSecondPicker() {
        Allure.step("Уменьшение даты и времени во втором DatePicker", datePickerPage::decreaseDateAndTimeInSecondPicker);
    }

    @Test(description = "Тестирование увеличения даты на один месяц во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testIncreaseDateByOneMonthInSecondPicker() {
        Allure.step("Увеличение даты на один месяц во втором DatePicker", datePickerPage::increaseDateByOneMonthInSecondPicker);
    }

    @Test(description = "Тестирование ввода даты вручную во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testSetDateByTextInSecondPicker() {
        Allure.step("Ввод даты вручную во втором DatePicker",
                () -> datePickerPage.setDateByTextInSecondPicker("09/10/2024"));
    }

    @Test(description = "Тестирование выбора даты через селекторы во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectDateInSecondPicker() {
        Allure.step("Выбор даты через селекторы во втором DatePicker",
                () -> datePickerPage.selectDateInSecondPicker("September", "2024"));
    }

    @AfterMethod
    public void resetPage() {
        Allure.step("Сброс страницы DatePicker", () -> open("https://demoqa.com/date-picker"));
    }
}
