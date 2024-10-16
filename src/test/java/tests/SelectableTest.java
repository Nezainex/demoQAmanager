package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.SelectablePage;
import pages.UploadDownloadPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SelectableTest extends BaseTest {

    private SelectablePage selectablePage;

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Selectable", () -> open("https://demoqa.com/selectable"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
        Allure.step("Создание страницы SelectablePage", () -> selectablePage = new SelectablePage());
    }

    @Test(description = "Тест выбора элементов из списка", retryAnalyzer = RetryAnalyzer.class)
    public void testListSelectable() {
        Allure.step("Клик на вкладку списка", () -> selectablePage.getListTab().click());

        Allure.step("Выбор первого элемента", () -> {
            selectablePage.selectItem(selectablePage.getListItems(), 0);
            Assert.assertTrue(selectablePage.isItemSelected(selectablePage.getListItems(), 0), "Первый элемент должен быть выбран.");
        });

        Allure.step("Снятие выбора первого элемента", () -> {
            selectablePage.selectItem(selectablePage.getListItems(), 0);
            Assert.assertFalse(selectablePage.isItemSelected(selectablePage.getListItems(), 0), "Первый элемент должен быть снят с выбора.");
        });
    }

    @Test(description = "Тест выбора элементов из сетки", retryAnalyzer = RetryAnalyzer.class)
    public void testGridSelectable() {
        Allure.step("Клик на вкладку сетки", () -> selectablePage.getGridTab().click());

        Allure.step("Выбор третьего элемента", () -> {
            selectablePage.selectItem(selectablePage.getGridItems(), 2);
            Assert.assertTrue(selectablePage.isItemSelected(selectablePage.getGridItems(), 2), "Третий элемент должен быть выбран.");
        });

        Allure.step("Снятие выбора третьего элемента", () -> {
            selectablePage.selectItem(selectablePage.getGridItems(), 2);
            Assert.assertFalse(selectablePage.isItemSelected(selectablePage.getGridItems(), 2), "Третий элемент должен быть снят с выбора.");
        });
    }
}
