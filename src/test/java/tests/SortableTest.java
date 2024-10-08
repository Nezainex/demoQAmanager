package tests;

import com.codeborne.selenide.ElementsCollection;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SortablePage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SortableTest extends BaseTest {

    SortablePage sortablePage = new SortablePage();

    @BeforeMethod
    public void setUp() {
        open("https://demoqa.com/sortable");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Test dragging and dropping list items", retryAnalyzer = RetryAnalyzer.class)
    public void testDragAndDropListItems() {
        sortablePage.getListTab().click();

        // Получение элементов списка
        ElementsCollection listItems = sortablePage.getListItems();

        // Перемещение первого элемента на третью позицию
        sortablePage.dragAndDropUsingActions(listItems, 0, 2);

        // Проверка, что элемент успешно перемещен
        listItems = sortablePage.getListItems(); // Обновляем список после перемещения
        assert listItems.get(2).getText().equals("One");

        // Перемещение третьего элемента на первую позицию
        sortablePage.dragAndDropUsingActions(listItems, 2, 0);

        // Проверка, что элемент успешно перемещен
        listItems = sortablePage.getListItems(); // Обновляем список после перемещения
        assert listItems.first().getText().equals("One");
    }

    @Test(description = "Test dragging and dropping grid items", retryAnalyzer = RetryAnalyzer.class)
    public void testDragAndDropGridItems() {
        sortablePage.getGridTab().click();

        // Получение элементов сетки
        ElementsCollection gridItems = sortablePage.getGridItems();

        // Перемещение первого элемента на шестую позицию
        sortablePage.dragAndDropUsingActions(gridItems, 0, 5);

        // Проверка, что элемент успешно перемещен
        gridItems = sortablePage.getGridItems(); // Обновляем список после перемещения
        assert gridItems.get(5).getText().equals("One");

        // Перемещение шестого элемента обратно на первую позицию
        sortablePage.dragAndDropUsingActions(gridItems, 5, 0);

        // Проверка, что элемент успешно перемещен
        gridItems = sortablePage.getGridItems(); // Обновляем список после перемещения
        assert gridItems.first().getText().equals("One");
    }
}
