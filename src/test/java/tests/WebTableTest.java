package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.WebTablePage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class WebTableTest extends BaseTest {

    private WebTablePage webTablePage;

    @BeforeMethod
    @Step("Открытие страницы Web Tables и настройка теста")
    public void setUp() {
        open("https://demoqa.com/webtables");
        getWebDriver().manage().window().maximize();
        webTablePage = new WebTablePage();
    }

    @Test(description = "Тестирование операций с webtables", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тестирование операций с Web Table: добавление, редактирование и удаление записи")
    public void testWebTableOperations() {
        addNewEntry();
        editEntry();
        deleteEntry();
    }

    @Step("Добавление новой записи в Web Table")
    private void addNewEntry() {
        webTablePage.addEntry("John", "Doe", "john.doe@example.com", "30", "5000", "Engineering");
    }

    @Step("Редактирование записи в Web Table")
    private void editEntry() {
        webTablePage.editEntry("7000", "HR");
    }

    @Step("Удаление записи из Web Table")
    private void deleteEntry() {
        webTablePage.deleteEntry();
    }
}
