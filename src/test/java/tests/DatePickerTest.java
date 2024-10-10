package tests;

import io.qameta.allure.Step;
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
public class DatePickerTest extends BaseTest{

    private final DatePickerPage datePickerPage = new DatePickerPage();

    @BeforeMethod
    @Step("Открытие страницы Date Picker и настройка окна")
    public void setUpBrowser() {
        open("https://demoqa.com/date-picker");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Тестирование ввода даты вручную в первом DatePicker", retryAnalyzer = RetryAnalyzer.class)
    @Step("Ввод даты вручную в первом DatePicker")
    public void testSetDateByTextInFirstPicker() {
        datePickerPage.setDateByTextInFirstPicker("08/08/2023");
    }

    @Test(description = "Тестирование выбора даты через селекторы в первом DatePicker", retryAnalyzer = RetryAnalyzer.class)
    @Step("Выбор даты через селекторы в первом DatePicker")
    public void testSelectDateInFirstPicker() {
        datePickerPage.selectDateInFirstPicker("August", "2023");
    }

    @Test(description = "Тестирование выбора времени во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    @Step("Выбор времени во втором DatePicker")
    public void testSelectTimeInSecondPicker() {
        datePickerPage.selectTimeInSecondPicker("03:00");
    }

    @Test(description = "Тестирование уменьшения даты и времени во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    @Step("Уменьшение даты и времени во втором DatePicker")
    public void testDecreaseDateAndTimeInSecondPicker() {
        datePickerPage.decreaseDateAndTimeInSecondPicker();
    }

    @Test(description = "Тестирование увеличения даты на один месяц во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    @Step("Увеличение даты на один месяц во втором DatePicker")
    public void testIncreaseDateByOneMonthInSecondPicker() {
        datePickerPage.increaseDateByOneMonthInSecondPicker();
    }

    @Test(description = "Тестирование ввода даты вручную во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    @Step("Ввод даты вручную во втором DatePicker")
    public void testSetDateByTextInSecondPicker() {
        datePickerPage.setDateByTextInSecondPicker("09/10/2024");
    }

    @Test(description = "Тестирование выбора даты через селекторы во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    @Step("Выбор даты через селекторы во втором DatePicker")
    public void testSelectDateInSecondPicker() {
        datePickerPage.selectDateInSecondPicker("September", "2024");
    }

    @AfterMethod
    @Step("Сброс страницы DatePicker")
    public void resetPage() {
        open("https://demoqa.com/date-picker");
    }
}
