package tests;

import com.codeborne.selenide.ElementsCollection;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SortablePage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SortableTest extends BaseTest {

    SortablePage sortablePage = new SortablePage();

    @BeforeMethod
    @Step("Открытие страницы Sortable и настройка браузера")
    public void setUp() {
        open("https://demoqa.com/sortable");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Test dragging and dropping list items", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест перетаскивания и выбора элементов списка")
    public void testDragAndDropListItems() {
        clickOnListTab();
        moveFirstItemToThirdPosition();
        moveThirdItemToFirstPosition();
    }

    @Step("Клик на вкладку списка")
    private void clickOnListTab() {
        sortablePage.getListTab().click();
    }

    @Step("Перемещение первого элемента на третью позицию")
    private void moveFirstItemToThirdPosition() {
        ElementsCollection listItems = sortablePage.getListItems();
        sortablePage.dragAndDropUsingActions(listItems, 0, 2);
        listItems = sortablePage.getListItems(); // Обновляем список после перемещения
        assert listItems.get(2).getText().equals("One");
    }

    @Step("Перемещение третьего элемента на первую позицию")
    private void moveThirdItemToFirstPosition() {
        ElementsCollection listItems = sortablePage.getListItems();
        sortablePage.dragAndDropUsingActions(listItems, 2, 0);
        listItems = sortablePage.getListItems(); // Обновляем список после перемещения
        assert listItems.first().getText().equals("One");
    }

    @Test(description = "Test dragging and dropping grid items", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест перетаскивания и выбора элементов сетки")
    public void testDragAndDropGridItems() {
        clickOnGridTab();
        moveFirstGridItemToSixthPosition();
        moveSixthGridItemToFirstPosition();
    }

    @Step("Клик на вкладку сетки")
    private void clickOnGridTab() {
        sortablePage.getGridTab().click();
    }

    @Step("Перемещение первого элемента сетки на шестую позицию")
    private void moveFirstGridItemToSixthPosition() {
        ElementsCollection gridItems = sortablePage.getGridItems();
        sortablePage.dragAndDropUsingActions(gridItems, 0, 5);
        gridItems = sortablePage.getGridItems(); // Обновляем список после перемещения
        assert gridItems.get(5).getText().equals("One");
    }

    @Step("Перемещение шестого элемента сетки обратно на первую позицию")
    private void moveSixthGridItemToFirstPosition() {
        ElementsCollection gridItems = sortablePage.getGridItems();
        sortablePage.dragAndDropUsingActions(gridItems, 5, 0);
        gridItems = sortablePage.getGridItems(); // Обновляем список после перемещения
        assert gridItems.first().getText().equals("One");
    }
}
