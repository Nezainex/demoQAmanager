package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.WebTablePage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class WebTableTest extends BaseTest {

    private WebTablePage webTablePage;

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Web Tables и настройка теста",
                () -> {
                    open("https://demoqa.com/webtables");
                    getWebDriver().manage().window().maximize();
                    webTablePage = new WebTablePage();
                });
    }

    @Test(description = "Тестирование операций с webtables", retryAnalyzer = RetryAnalyzer.class)
    public void testWebTableOperations() {
        Allure.step("Тестирование операций с Web Table: добавление, редактирование и удаление записи",
                () -> {
                    addNewEntry();
                    editEntry();
                    deleteEntry();
                });
    }

    private void addNewEntry() {
        Allure.step("Добавление новой записи в Web Table",
                () -> webTablePage.addEntry("John", "Doe", "john.doe@example.com", "30", "5000", "Engineering"));
    }

    private void editEntry() {
        Allure.step("Редактирование записи в Web Table",
                () -> webTablePage.editEntry("7000", "HR"));
    }

    private void deleteEntry() {
        Allure.step("Удаление записи из Web Table",
                () -> webTablePage.deleteEntry());
    }
}
