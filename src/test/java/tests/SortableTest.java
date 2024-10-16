package tests;

import com.codeborne.selenide.ElementsCollection;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SortablePage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SortableTest extends BaseTest {

    SortablePage sortablePage = new SortablePage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Sortable", () -> open("https://demoqa.com/sortable"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
    }

    @Test(description = "Test dragging and dropping list items", retryAnalyzer = RetryAnalyzer.class)
    public void testDragAndDropListItems() {
        Allure.step("Тест перетаскивания и выбора элементов списка", () -> {
            clickOnListTab();
            moveFirstItemToThirdPosition();
            moveThirdItemToFirstPosition();
        });
    }

    private void clickOnListTab() {
        Allure.step("Клик на вкладку списка", () -> sortablePage.getListTab().click());
    }

    private void moveFirstItemToThirdPosition() {
        Allure.step("Перемещение первого элемента на третью позицию", () -> {
            ElementsCollection listItems = sortablePage.getListItems();
            sortablePage.dragAndDropUsingActions(listItems, 0, 2);
            listItems = sortablePage.getListItems(); // Обновляем список после перемещения
            assert listItems.get(2).getText().equals("One");
        });
    }

    private void moveThirdItemToFirstPosition() {
        Allure.step("Перемещение третьего элемента на первую позицию", () -> {
            ElementsCollection listItems = sortablePage.getListItems();
            sortablePage.dragAndDropUsingActions(listItems, 2, 0);
            listItems = sortablePage.getListItems(); // Обновляем список после перемещения
            assert listItems.first().getText().equals("One");
        });
    }

    @Test(description = "Test dragging and dropping grid items", retryAnalyzer = RetryAnalyzer.class)
    public void testDragAndDropGridItems() {
        Allure.step("Тест перетаскивания и выбора элементов сетки", () -> {
            clickOnGridTab();
            moveFirstGridItemToSixthPosition();
            moveSixthGridItemToFirstPosition();
        });
    }

    private void clickOnGridTab() {
        Allure.step("Клик на вкладку сетки", () -> sortablePage.getGridTab().click());
    }

    private void moveFirstGridItemToSixthPosition() {
        Allure.step("Перемещение первого элемента сетки на шестую позицию", () -> {
            ElementsCollection gridItems = sortablePage.getGridItems();
            sortablePage.dragAndDropUsingActions(gridItems, 0, 5);
            gridItems = sortablePage.getGridItems(); // Обновляем список после перемещения
            assert gridItems.get(5).getText().equals("One");
        });
    }

    private void moveSixthGridItemToFirstPosition() {
        Allure.step("Перемещение шестого элемента сетки обратно на первую позицию", () -> {
            ElementsCollection gridItems = sortablePage.getGridItems();
            sortablePage.dragAndDropUsingActions(gridItems, 5, 0);
            gridItems = sortablePage.getGridItems(); // Обновляем список после перемещения
            assert gridItems.first().getText().equals("One");
        });
    }
}
