package tests;

import io.qameta.allure.Step;
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
    @Step("Открытие страницы и настройка окна")
    public void setUp() {
        open("https://demoqa.com/droppable");
        getWebDriver().manage().window().maximize();
        droppablePage = new DroppablePage();
    }

    @Test(description = "Тест простого перетаскивания Simple", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест перетаскивания элемента Simple")
    public void testSimpleDroppable() {
        open("https://demoqa.com/droppable");
        droppablePage.switchToSimpleTab();
        droppablePage.dragAndDrop(droppablePage.getDragMeSimple(), droppablePage.getDropHereSimple());
        Assert.assertTrue(droppablePage.getDropHereSimple().getText().contains("Dropped"), "Элемент должен быть успешно сброшен.");
    }

    @Test(description = "Тест перетаскивания для Accept", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест перетаскивания элемента Accept")
    public void testAcceptDroppable() {
        open("https://demoqa.com/droppable");
        droppablePage.switchToAcceptTab();
        droppablePage.dragAndDrop(droppablePage.getNotAcceptable(), droppablePage.getDropHereAccept());
        Assert.assertFalse(droppablePage.getDropHereAccept().getText().contains("Dropped"), "Элемент не должен быть принят.");

        droppablePage.dragAndDrop(droppablePage.getAcceptable(), droppablePage.getDropHereAccept());
        Assert.assertTrue(droppablePage.getDropHereAccept().getText().contains("Dropped"), "Элемент должен быть принят.");
    }

    @Test(description = "Тест перетаскивания с Prevent Propagation", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест перетаскивания с Prevent Propagation")
    public void testPreventPropagationDroppable() {
        open("https://demoqa.com/droppable");
        droppablePage.switchToPreventPropagationTab();

        droppablePage.dragAndDrop(droppablePage.getDragBox(), droppablePage.getInnerNotGreedyDropBox());
        Assert.assertTrue(droppablePage.getInnerNotGreedyDropBox().getText().contains("Dropped"), "Элемент должен быть сброшен в не жадный внутренний контейнер.");
        Assert.assertTrue(droppablePage.getOuterNotGreedyDropBox().getText().contains("Dropped"), "Элемент должен быть сброшен в не жадный внешний контейнер.");

        droppablePage.dragAndDrop(droppablePage.getDragBox(), droppablePage.getInnerGreedyDropBox());
        Assert.assertTrue(droppablePage.getInnerGreedyDropBox().getText().contains("Dropped"), "Элемент должен быть сброшен в жадный внутренний контейнер.");
        Assert.assertTrue(droppablePage.getOuterGreedyDropBox().getText().contains("Dropped"), "Элемент должен быть сброшен в жадный внешний контейнер.");
    }

    @Test(description = "Тест перетаскивания с Revert Draggable", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест перетаскивания с Revert Draggable")
    public void testRevertDraggable() {
        open("https://demoqa.com/droppable");
        droppablePage.switchToRevertDraggableTab();

        droppablePage.getWillRevert().shouldBe(visible);
        droppablePage.dragAndDrop(droppablePage.getWillRevert(), droppablePage.getDropHereRevert());
        Assert.assertTrue(droppablePage.getDropHereRevert().getText().contains("Dropped"), "Элемент должен быть сброшен.");
        Assert.assertEquals(droppablePage.getWillRevert().getCssValue("position"), "relative", "Элемент должен вернуться на исходную позицию.");

        droppablePage.getNotRevert().shouldBe(visible);
        int notRevertInitialX = droppablePage.getNotRevert().getLocation().getX();
        int notRevertInitialY = droppablePage.getNotRevert().getLocation().getY();

        droppablePage.dragAndDrop(droppablePage.getNotRevert(), droppablePage.getDropHereRevert());
        Assert.assertTrue(droppablePage.getDropHereRevert().getText().contains("Dropped"), "Элемент должен быть сброшен.");

        int notRevertAfterDropX = droppablePage.getNotRevert().getLocation().getX();
        int notRevertAfterDropY = droppablePage.getNotRevert().getLocation().getY();
        Assert.assertNotEquals(notRevertAfterDropX, notRevertInitialX, "Элемент не должен вернуться на исходную X координату.");
        Assert.assertNotEquals(notRevertAfterDropY, notRevertInitialY, "Элемент не должен вернуться на исходную Y координату.");
    }
}
