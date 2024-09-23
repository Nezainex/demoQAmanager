package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;
import pages.DraggablePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class DraggableTest {

    private DraggablePage draggablePage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = false;  // Открытие нового браузера для каждого теста
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/dragabble");
        getWebDriver().manage().window().maximize();
        draggablePage = new DraggablePage();
    }

    @Test(description = "Тест простого перетаскивания Simple", retryAnalyzer = RetryAnalyzer.class)
    public void testSimpleDraggable() {
        draggablePage.getSimpleTab().click();
        draggablePage.dragAndDrop(draggablePage.getDragBoxSimple(), 100, 100);
        // Здесь можно добавить проверки по координатам после перемещения
    }

    @Test(description = "Тест перетаскивания с осевыми ограничениями Axis Restricted", retryAnalyzer = RetryAnalyzer.class)
    public void testAxisRestrictedDraggable() {
        draggablePage.getAxisRestrictedTab().click();

        // Перетаскивание только по оси X
        draggablePage.dragAndDrop(draggablePage.getDragBoxX(), 100, 0);

        // Перетаскивание только по оси Y
        draggablePage.dragAndDrop(draggablePage.getDragBoxY(), 0, 100);
    }

    @Test(description = "Тест перетаскивания с ограничением контейнера Container Restricted", retryAnalyzer = RetryAnalyzer.class)
    public void testContainerRestrictedDraggable() {
        draggablePage.getContainerRestrictedTab().click();

        // Перетаскивание внутри контейнера
        draggablePage.dragAndDrop(draggablePage.getDragBoxContainer(), 50, 50);

        // Перетаскивание внутри родителя
        draggablePage.dragAndDrop(draggablePage.getDragBoxParent(), 30, 30);
    }

    @Test(description = "Тест перетаскивания с различными стилями курсора Cursor Style", retryAnalyzer = RetryAnalyzer.class)
    public void testCursorStyleDraggable() {
        draggablePage.getCursorStyleTab().click();

        // Перетаскивание с фиксированным курсором в центре
        draggablePage.dragAndDrop(draggablePage.getDragBoxCursorCenter(), 100, 100);

        // Перетаскивание с курсором в верхнем левом углу
        draggablePage.dragAndDrop(draggablePage.getDragBoxCursorTopLeft(), 100, 100);

        // Перетаскивание с курсором внизу
        draggablePage.dragAndDrop(draggablePage.getDragBoxCursorBottom(), 100, 100);
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
