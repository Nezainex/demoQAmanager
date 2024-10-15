package tests;

import io.qameta.allure.Allure;
import org.testng.annotations.*;
import pages.DraggablePage;
import utils.RetryAnalyzer;
import utils.TestListener;
import utils.TestSuiteListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({TestListener.class, TestSuiteListener.class})
public class DraggableTest extends BaseTest {

    private DraggablePage draggablePage;

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Draggable", () -> {
            open("https://demoqa.com/dragabble");
            getWebDriver().manage().window().maximize();
            draggablePage = new DraggablePage();
        });
    }

    @Test(description = "Тест простого перетаскивания Simple", retryAnalyzer = RetryAnalyzer.class)
    public void testSimpleDraggable() {
        Allure.step("Клик на вкладку Simple", () -> draggablePage.getSimpleTab().click());
        Allure.step("Перетаскивание коробки Simple", () -> draggablePage.dragAndDrop(draggablePage.getDragBoxSimple(), 100, 100));
    }

    @Test(description = "Тест перетаскивания с осевыми ограничениями Axis Restricted", retryAnalyzer = RetryAnalyzer.class)
    public void testAxisRestrictedDraggable() {
        Allure.step("Клик на вкладку Axis Restricted", () -> draggablePage.getAxisRestrictedTab().click());
        Allure.step("Перетаскивание по оси X", () -> draggablePage.dragAndDrop(draggablePage.getDragBoxX(), 100, 0));
        Allure.step("Перетаскивание по оси Y", () -> draggablePage.dragAndDrop(draggablePage.getDragBoxY(), 0, 100));
    }

    @Test(description = "Тест перетаскивания с ограничением контейнера Container Restricted", retryAnalyzer = RetryAnalyzer.class)
    public void testContainerRestrictedDraggable() {
        Allure.step("Клик на вкладку Container Restricted", () -> draggablePage.getContainerRestrictedTab().click());
        Allure.step("Перетаскивание коробки в контейнере", () -> draggablePage.dragAndDrop(draggablePage.getDragBoxContainer(), 50, 50));
        Allure.step("Перетаскивание коробки в родительском контейнере", () -> draggablePage.dragAndDrop(draggablePage.getDragBoxParent(), 30, 30));
    }

    @Test(description = "Тест перетаскивания с различными стилями курсора Cursor Style", retryAnalyzer = RetryAnalyzer.class)
    public void testCursorStyleDraggable() {
        Allure.step("Клик на вкладку Cursor Style", () -> draggablePage.getCursorStyleTab().click());
        Allure.step("Перетаскивание коробки по центру курсора", () -> draggablePage.dragAndDrop(draggablePage.getDragBoxCursorCenter(), 100, 100));
        Allure.step("Перетаскивание коробки по верхнему левому углу курсора", () -> draggablePage.dragAndDrop(draggablePage.getDragBoxCursorTopLeft(), 100, 100));
        Allure.step("Перетаскивание коробки по нижней части курсора", () -> draggablePage.dragAndDrop(draggablePage.getDragBoxCursorBottom(), 100, 100));
    }
}
