package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SortablePage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SortableTest {

    SortablePage sortablePage = new SortablePage();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
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

    @AfterMethod
    public void tearDown() {
        try {
            // Закрываем все окна, если они существуют
            for (String handle : getWebDriver().getWindowHandles()) {
                getWebDriver().switchTo().window(handle).close();
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка при закрытии окон: " + e.getMessage());
        } finally {
            try {
                // Закрываем WebDriver, если сессия активна
                getWebDriver().quit();
            } catch (Exception e) {
                System.out.println("Ошибка при завершении сессии WebDriver: " + e.getMessage());
            }
        }
    }
}
