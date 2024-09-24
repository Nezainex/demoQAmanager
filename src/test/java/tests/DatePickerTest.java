package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
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
public class DatePickerTest {

    private final DatePickerPage datePickerPage = new DatePickerPage();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/date-picker");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Тестирование ввода даты вручную в первом DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testSetDateByTextInFirstPicker() {
        datePickerPage.setDateByTextInFirstPicker("08/08/2023");
    }

    @Test(description = "Тестирование выбора даты через селекторы в первом DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectDateInFirstPicker() {
        datePickerPage.selectDateInFirstPicker("August", "2023");
    }

    @Test(description = "Тестирование выбора времени во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectTimeInSecondPicker() {
        datePickerPage.selectTimeInSecondPicker("03:00");
    }

    @Test(description = "Тестирование уменьшения даты и времени во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testDecreaseDateAndTimeInSecondPicker() {
        datePickerPage.decreaseDateAndTimeInSecondPicker();
    }

    @Test(description = "Тестирование увеличения даты на один месяц во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testIncreaseDateByOneMonthInSecondPicker() {
        datePickerPage.increaseDateByOneMonthInSecondPicker();
    }

    @Test(description = "Тестирование ввода даты вручную во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testSetDateByTextInSecondPicker() {
        datePickerPage.setDateByTextInSecondPicker("09/10/2024");
    }

    @Test(description = "Тестирование выбора даты через селекторы во втором DatePicker", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectDateInSecondPicker() {
        datePickerPage.selectDateInSecondPicker("September", "2024");
    }

    @AfterMethod
    public void resetPage() {
        open("https://demoqa.com/date-picker");
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