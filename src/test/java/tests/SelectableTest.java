package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.SelectablePage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SelectableTest extends BaseTest {

    private SelectablePage selectablePage;

    @BeforeMethod
    @Step("Открытие страницы Selectable и настройка браузера")
    public void setUp() {
        open("https://demoqa.com/selectable");
        getWebDriver().manage().window().maximize();
        selectablePage = new SelectablePage();
    }

    @Test(description = "Тест выбора элементов из списка", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест выбора элементов из списка")
    public void testListSelectable() {
        clickOnListTab();
        selectFirstItem();
        deselectFirstItem();
    }

    @Step("Клик на вкладку списка")
    private void clickOnListTab() {
        selectablePage.getListTab().click();
    }

    @Step("Выбор первого элемента")
    private void selectFirstItem() {
        selectablePage.selectItem(selectablePage.getListItems(), 0);
        Assert.assertTrue(selectablePage.isItemSelected(selectablePage.getListItems(), 0), "Первый элемент должен быть выбран.");
    }

    @Step("Снятие выбора первого элемента")
    private void deselectFirstItem() {
        selectablePage.selectItem(selectablePage.getListItems(), 0);
        Assert.assertFalse(selectablePage.isItemSelected(selectablePage.getListItems(), 0), "Первый элемент должен быть снят с выбора.");
    }

    @Test(description = "Тест выбора элементов из сетки", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест выбора элементов из сетки")
    public void testGridSelectable() {
        clickOnGridTab();
        selectThirdItem();
        deselectThirdItem();
    }

    @Step("Клик на вкладку сетки")
    private void clickOnGridTab() {
        selectablePage.getGridTab().click();
    }

    @Step("Выбор третьего элемента")
    private void selectThirdItem() {
        selectablePage.selectItem(selectablePage.getGridItems(), 2);
        Assert.assertTrue(selectablePage.isItemSelected(selectablePage.getGridItems(), 2), "Третий элемент должен быть выбран.");
    }

    @Step("Снятие выбора третьего элемента")
    private void deselectThirdItem() {
        selectablePage.selectItem(selectablePage.getGridItems(), 2);
        Assert.assertFalse(selectablePage.isItemSelected(selectablePage.getGridItems(), 2), "Третий элемент должен быть снят с выбора.");
    }
}
