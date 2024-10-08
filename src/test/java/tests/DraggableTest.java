package tests;

import org.testng.annotations.*;
import pages.DraggablePage;
import utils.RetryAnalyzer;
import utils.TestListener;
import utils.TestSuiteListener;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({TestListener.class, TestSuiteListener.class})
public class DraggableTest extends BaseTest {

    private DraggablePage draggablePage;

    @BeforeMethod
    @Step("Открытие страницы Draggable")
    public void setUp() {
        openDraggablePage();  // Открытие страницы и инициализация страницы Draggable
    }

    @Step("Открытие страницы Draggable")
    private void openDraggablePage() {
        open("https://demoqa.com/dragabble");
        getWebDriver().manage().window().maximize();
        draggablePage = new DraggablePage();
    }

    @Test(description = "Тест простого перетаскивания Simple", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест простого перетаскивания Simple")
    public void testSimpleDraggable() {
        clickOnSimpleTab();
        dragSimpleBox();
    }

    @Step("Клик на вкладку Simple")
    private void clickOnSimpleTab() {
        draggablePage.getSimpleTab().click();
    }

    @Step("Перетаскивание коробки Simple")
    private void dragSimpleBox() {
        draggablePage.dragAndDrop(draggablePage.getDragBoxSimple(), 100, 100);
    }

    @Test(description = "Тест перетаскивания с осевыми ограничениями Axis Restricted", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест перетаскивания с осевыми ограничениями Axis Restricted")
    public void testAxisRestrictedDraggable() {
        clickOnAxisRestrictedTab();
        dragBoxOnXAxis();
        dragBoxOnYAxis();
    }

    @Step("Клик на вкладку Axis Restricted")
    private void clickOnAxisRestrictedTab() {
        draggablePage.getAxisRestrictedTab().click();
    }

    @Step("Перетаскивание по оси X")
    private void dragBoxOnXAxis() {
        draggablePage.dragAndDrop(draggablePage.getDragBoxX(), 100, 0);
    }

    @Step("Перетаскивание по оси Y")
    private void dragBoxOnYAxis() {
        draggablePage.dragAndDrop(draggablePage.getDragBoxY(), 0, 100);
    }

    @Test(description = "Тест перетаскивания с ограничением контейнера Container Restricted", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест перетаскивания с ограничением контейнера Container Restricted")
    public void testContainerRestrictedDraggable() {
        clickOnContainerRestrictedTab();
        dragBoxInContainer();
        dragBoxInParentContainer();
    }

    @Step("Клик на вкладку Container Restricted")
    private void clickOnContainerRestrictedTab() {
        draggablePage.getContainerRestrictedTab().click();
    }

    @Step("Перетаскивание коробки в контейнере")
    private void dragBoxInContainer() {
        draggablePage.dragAndDrop(draggablePage.getDragBoxContainer(), 50, 50);
    }

    @Step("Перетаскивание коробки в родительском контейнере")
    private void dragBoxInParentContainer() {
        draggablePage.dragAndDrop(draggablePage.getDragBoxParent(), 30, 30);
    }

    @Test(description = "Тест перетаскивания с различными стилями курсора Cursor Style", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тест перетаскивания с различными стилями курсора Cursor Style")
    public void testCursorStyleDraggable() {
        clickOnCursorStyleTab();
        dragCursorCenterBox();
        dragCursorTopLeftBox();
        dragCursorBottomBox();
    }

    @Step("Клик на вкладку Cursor Style")
    private void clickOnCursorStyleTab() {
        draggablePage.getCursorStyleTab().click();
    }

    @Step("Перетаскивание коробки по центру курсора")
    private void dragCursorCenterBox() {
        draggablePage.dragAndDrop(draggablePage.getDragBoxCursorCenter(), 100, 100);
    }

    @Step("Перетаскивание коробки по верхнему левому углу курсора")
    private void dragCursorTopLeftBox() {
        draggablePage.dragAndDrop(draggablePage.getDragBoxCursorTopLeft(), 100, 100);
    }

    @Step("Перетаскивание коробки по нижней части курсора")
    private void dragCursorBottomBox() {
        draggablePage.dragAndDrop(draggablePage.getDragBoxCursorBottom(), 100, 100);
    }
}
