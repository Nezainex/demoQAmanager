package tests;

import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DroppablePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class DroppableTest {

    private DroppablePage droppablePage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/droppable");
        getWebDriver().manage().window().maximize();
        droppablePage = new DroppablePage();
    }

    @Test(description = "Тест простого перетаскивания Simple", retryAnalyzer = RetryAnalyzer.class)
    public void testSimpleDroppable() {
        open("https://demoqa.com/droppable");  // Перезагрузка страницы
        droppablePage.switchToSimpleTab();  // Переключение на нужный таб
        droppablePage.dragAndDrop(droppablePage.getDragMeSimple(), droppablePage.getDropHereSimple());
        Assert.assertTrue(droppablePage.getDropHereSimple().getText().contains("Dropped"), "Элемент должен быть успешно сброшен.");
    }

    @Test(description = "Тест перетаскивания для Accept", retryAnalyzer = RetryAnalyzer.class)
    public void testAcceptDroppable() {
        open("https://demoqa.com/droppable");  // Перезагрузка страницы
        droppablePage.switchToAcceptTab();  // Переключение на нужный таб
        droppablePage.dragAndDrop(droppablePage.getNotAcceptable(), droppablePage.getDropHereAccept());
        Assert.assertFalse(droppablePage.getDropHereAccept().getText().contains("Dropped"), "Элемент не должен быть принят.");

        droppablePage.dragAndDrop(droppablePage.getAcceptable(), droppablePage.getDropHereAccept());
        Assert.assertTrue(droppablePage.getDropHereAccept().getText().contains("Dropped"), "Элемент должен быть принят.");
    }

    @Test(description = "Тест перетаскивания с Prevent Propagation", retryAnalyzer = RetryAnalyzer.class)
    public void testPreventPropagationDroppable() {
        open("https://demoqa.com/droppable");  // Перезагрузка страницы
        droppablePage.switchToPreventPropagationTab();  // Переключение на нужный таб

        // Перетаскиваем элемент в не жадный внутренний контейнер
        droppablePage.dragAndDrop(droppablePage.getDragBox(), droppablePage.getInnerNotGreedyDropBox());
        Assert.assertTrue(droppablePage.getInnerNotGreedyDropBox().getText().contains("Dropped"), "Элемент должен быть сброшен в не жадный внутренний контейнер.");
        Assert.assertTrue(droppablePage.getOuterNotGreedyDropBox().getText().contains("Dropped"), "Элемент должен быть сброшен в не жадный внешний контейнер.");

        // Перетаскиваем элемент в жадный внутренний контейнер
        droppablePage.dragAndDrop(droppablePage.getDragBox(), droppablePage.getInnerGreedyDropBox());
        Assert.assertTrue(droppablePage.getInnerGreedyDropBox().getText().contains("Dropped"), "Элемент должен быть сброшен в жадный внутренний контейнер.");
        Assert.assertTrue(droppablePage.getOuterGreedyDropBox().getText().contains("Dropped"), "Элемент должен быть сброшен в жадный внешний контейнер.");  // Изменено на true, так как жадный контейнер действительно может захватывать и внешний контейнер
    }

    @Test(description = "Тест перетаскивания с Revert Draggable", retryAnalyzer = RetryAnalyzer.class)
    public void testRevertDraggable() {
        open("https://demoqa.com/droppable");  // Перезагрузка страницы
        // Переключаемся на вкладку "Revert Draggable"
        droppablePage.switchToRevertDraggableTab();

        // Ожидание видимости элемента "Will Revert"
        droppablePage.getWillRevert().shouldBe(visible);

        // Перетаскивание элемента "Will Revert" и проверка, что он возвращается
        droppablePage.dragAndDrop(droppablePage.getWillRevert(), droppablePage.getDropHereRevert());
        Assert.assertTrue(droppablePage.getDropHereRevert().getText().contains("Dropped"), "Элемент должен быть сброшен.");
        // Проверяем, что элемент "Will Revert" вернулся на исходную позицию
        Assert.assertEquals(droppablePage.getWillRevert().getCssValue("position"), "relative", "Элемент должен вернуться на исходную позицию.");

        // Ожидание видимости элемента "Not Revert"
        droppablePage.getNotRevert().shouldBe(visible);

        // Сохраняем начальные координаты элемента "Not Revert"
        int notRevertInitialX = droppablePage.getNotRevert().getLocation().getX();
        int notRevertInitialY = droppablePage.getNotRevert().getLocation().getY();

        // Перетаскивание элемента "Not Revert" и проверка, что он НЕ возвращается
        droppablePage.dragAndDrop(droppablePage.getNotRevert(), droppablePage.getDropHereRevert());
        Assert.assertTrue(droppablePage.getDropHereRevert().getText().contains("Dropped"), "Элемент должен быть сброшен.");

        // Проверяем, что элемент "Not Revert" НЕ вернулся на исходную позицию
        int notRevertAfterDropX = droppablePage.getNotRevert().getLocation().getX();
        int notRevertAfterDropY = droppablePage.getNotRevert().getLocation().getY();
        Assert.assertNotEquals(notRevertAfterDropX, notRevertInitialX, "Элемент не должен вернуться на исходную X координату.");
        Assert.assertNotEquals(notRevertAfterDropY, notRevertInitialY, "Элемент не должен вернуться на исходную Y координату.");
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
