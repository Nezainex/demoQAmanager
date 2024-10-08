package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.SelectablePage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SelectableTest extends BaseTest {

    private SelectablePage selectablePage;

    @BeforeMethod
    public void setUp() {
        open("https://demoqa.com/selectable");
        getWebDriver().manage().window().maximize();
        selectablePage = new SelectablePage();
    }

    @Test(description = "Тест выбора элементов из списка", retryAnalyzer = RetryAnalyzer.class)
    public void testListSelectable() {
        selectablePage.getListTab().click();

        // Выбор первого элемента
        selectablePage.selectItem(selectablePage.getListItems(), 0);
        Assert.assertTrue(selectablePage.isItemSelected(selectablePage.getListItems(), 0), "Первый элемент должен быть выбран.");

        // Снятие выбора
        selectablePage.selectItem(selectablePage.getListItems(), 0);
        Assert.assertFalse(selectablePage.isItemSelected(selectablePage.getListItems(), 0), "Первый элемент должен быть снят с выбора.");
    }

    @Test(description = "Тест выбора элементов из сетки", retryAnalyzer = RetryAnalyzer.class)
    public void testGridSelectable() {
        selectablePage.getGridTab().click();

        // Выбор третьего элемента
        selectablePage.selectItem(selectablePage.getGridItems(), 2);
        Assert.assertTrue(selectablePage.isItemSelected(selectablePage.getGridItems(), 2), "Третий элемент должен быть выбран.");

        // Снятие выбора
        selectablePage.selectItem(selectablePage.getGridItems(), 2);
        Assert.assertFalse(selectablePage.isItemSelected(selectablePage.getGridItems(), 2), "Третий элемент должен быть снят с выбора.");
    }
}
