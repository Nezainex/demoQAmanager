package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.WebTablePage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class WebTableTest extends BaseTest {

    private WebTablePage webTablePage;

    @BeforeMethod
    public void setUp() {
        open("https://demoqa.com/webtables");
        getWebDriver().manage().window().maximize();
        webTablePage = new WebTablePage();
    }

    @Test(description = "Тестирование webtables", retryAnalyzer = RetryAnalyzer.class)
    public void testWebTableOperations() {
        // Добавление новой записи
        webTablePage.addEntry("John", "Doe", "john.doe@example.com", "30", "5000", "Engineering");

        // Редактирование записи
        webTablePage.editEntry("7000", "HR");

        // Удаление записи
        webTablePage.deleteEntry();
    }
}
