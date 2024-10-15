package tests;

import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DroppablePage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class DroppableTest extends BaseTest {

    private DroppablePage droppablePage;

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы и настройка окна", () -> {
            open("https://demoqa.com/droppable");
            getWebDriver().manage().window().maximize();
            droppablePage = new DroppablePage();
        });
    }

    @Test(description = "Тест простого перетаскивания Simple", retryAnalyzer = RetryAnalyzer.class)
    public void testSimpleDroppable() {
        Allure.step("Переключение на вкладку Simple", () -> droppablePage.switchToSimpleTab());
        Allure.step("Перетаскивание элемента на зону сброса", () -> droppablePage.dragAndDrop(droppablePage.getDragMeSimple(), droppablePage.getDropHereSimple()));
        Allure.step("Проверка успешного сброса элемента", () -> Assert.assertTrue(droppablePage.getDropHereSimple().getText().contains("Dropped"), "Элемент должен быть успешно сброшен."));
    }

    @Test(description = "Тест перетаскивания для Accept", retryAnalyzer = RetryAnalyzer.class)
    public void testAcceptDroppable() {
        Allure.step("Переключение на вкладку Accept", () -> droppablePage.switchToAcceptTab());
        Allure.step("Перетаскивание неприемлемого элемента", () -> droppablePage.dragAndDrop(droppablePage.getNotAcceptable(), droppablePage.getDropHereAccept()));
        Allure.step("Проверка, что элемент не был принят", () -> Assert.assertFalse(droppablePage.getDropHereAccept().getText().contains("Dropped"), "Элемент не должен быть принят."));

        Allure.step("Перетаскивание приемлемого элемента", () -> droppablePage.dragAndDrop(droppablePage.getAcceptable(), droppablePage.getDropHereAccept()));
        Allure.step("Проверка успешного сброса приемлемого элемента", () -> Assert.assertTrue(droppablePage.getDropHereAccept().getText().contains("Dropped"), "Элемент должен быть принят."));
    }

    @Test(description = "Тест перетаскивания с Prevent Propagation", retryAnalyzer = RetryAnalyzer.class)
    public void testPreventPropagationDroppable() {
        Allure.step("Переключение на вкладку Prevent Propagation", () -> droppablePage.switchToPreventPropagationTab());

        Allure.step("Перетаскивание элемента в не жадный внутренний контейнер", () -> droppablePage.dragAndDrop(droppablePage.getDragBox(), droppablePage.getInnerNotGreedyDropBox()));
        Allure.step("Проверка сброса элемента в не жадный внутренний контейнер", () -> Assert.assertTrue(droppablePage.getInnerNotGreedyDropBox().getText().contains("Dropped"), "Элемент должен быть сброшен в не жадный внутренний контейнер."));
        Allure.step("Проверка сброса элемента в не жадный внешний контейнер", () -> Assert.assertTrue(droppablePage.getOuterNotGreedyDropBox().getText().contains("Dropped"), "Элемент должен быть сброшен в не жадный внешний контейнер."));

        Allure.step("Перетаскивание элемента в жадный внутренний контейнер", () -> droppablePage.dragAndDrop(droppablePage.getDragBox(), droppablePage.getInnerGreedyDropBox()));
        Allure.step("Проверка сброса элемента в жадный внутренний контейнер", () -> Assert.assertTrue(droppablePage.getInnerGreedyDropBox().getText().contains("Dropped"), "Элемент должен быть сброшен в жадный внутренний контейнер."));
        Allure.step("Проверка сброса элемента в жадный внешний контейнер", () -> Assert.assertTrue(droppablePage.getOuterGreedyDropBox().getText().contains("Dropped"), "Элемент должен быть сброшен в жадный внешний контейнер."));
    }

    @Test(description = "Тест перетаскивания с Revert Draggable", retryAnalyzer = RetryAnalyzer.class)
    public void testRevertDraggable() {
        Allure.step("Переключение на вкладку Revert Draggable", () -> droppablePage.switchToRevertDraggableTab());

        Allure.step("Перетаскивание элемента, который вернется", () -> {
            droppablePage.getWillRevert().shouldBe(visible);
            droppablePage.dragAndDrop(droppablePage.getWillRevert(), droppablePage.getDropHereRevert());
            Assert.assertTrue(droppablePage.getDropHereRevert().getText().contains("Dropped"), "Элемент должен быть сброшен.");
            Assert.assertEquals(droppablePage.getWillRevert().getCssValue("position"), "relative", "Элемент должен вернуться на исходную позицию.");
        });

        Allure.step("Перетаскивание элемента, который не вернется", () -> {
            droppablePage.getNotRevert().shouldBe(visible);
            int initialX = droppablePage.getNotRevert().getLocation().getX();
            int initialY = droppablePage.getNotRevert().getLocation().getY();

            droppablePage.dragAndDrop(droppablePage.getNotRevert(), droppablePage.getDropHereRevert());
            Assert.assertTrue(droppablePage.getDropHereRevert().getText().contains("Dropped"), "Элемент должен быть сброшен.");

            int afterDropX = droppablePage.getNotRevert().getLocation().getX();
            int afterDropY = droppablePage.getNotRevert().getLocation().getY();
            Assert.assertNotEquals(afterDropX, initialX, "Элемент не должен вернуться на исходную X координату.");
            Assert.assertNotEquals(afterDropY, initialY, "Элемент не должен вернуться на исходную Y координату.");
        });
    }
}
