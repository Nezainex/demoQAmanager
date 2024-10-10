package tests;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AutoCompletePage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class AutoCompleteTest extends BaseTest {

    private final AutoCompletePage autoCompletePage = new AutoCompletePage();

    @BeforeMethod
    @Step("Открытие страницы автозаполнения и настройка окна")
    public void setUp() {
        open("https://demoqa.com/auto-complete");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Тестирование автозаполнения", retryAnalyzer = RetryAnalyzer.class)
    @Step("Ввод нескольких значений в поле автозаполнения")
    public void testMultipleAutoComplete() {
        autoCompletePage.fillMultipleInput("Red", "Blue", "Green");
    }

    @Test(description = "Тест одиночного ввода автозаполнения", retryAnalyzer = RetryAnalyzer.class)
    @Step("Ввод одного значения в поле автозаполнения")
    public void testSingleAutoComplete() {
        autoCompletePage.fillSingleInput("Red");
    }
}
