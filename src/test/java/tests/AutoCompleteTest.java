package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AutoCompletePage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class AutoCompleteTest extends BaseTest {

    private final AutoCompletePage autoCompletePage = new AutoCompletePage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы автозаполнения", () -> open("https://demoqa.com/auto-complete"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
    }

    @Test(description = "Тестирование автозаполнения", retryAnalyzer = RetryAnalyzer.class)
    public void testMultipleAutoComplete() {
        Allure.step("Ввод нескольких значений в поле автозаполнения", () -> autoCompletePage.fillMultipleInput("Red", "Blue", "Green"));
    }

    @Test(description = "Тест одиночного ввода автозаполнения", retryAnalyzer = RetryAnalyzer.class)
    public void testSingleAutoComplete() {
        Allure.step("Ввод одного значения в поле автозаполнения", () -> autoCompletePage.fillSingleInput("Red"));
    }
}
