package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ResizablePage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ResizableTest extends BaseTest {

    private ResizablePage resizablePage;

    @BeforeMethod
    @Step("Открытие страницы Resizable и настройка окна")
    public void setUp() {
        open("https://demoqa.com/resizable");
        getWebDriver().manage().window().maximize();
        resizablePage = new ResizablePage();
    }

    @Test(description = "Тест изменения размеров элемента с ограничениями", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тестирование изменения размеров элемента с ограничениями")
    public void testResizableWithRestriction() {
        Dimension initialSize = getInitialElementSizeWithRestriction();

        // Увеличиваем размер на 100px по ширине и высоте
        resizeElementWithRestriction();

        Dimension newSize = getNewElementSizeWithRestriction();

        // Проверка, что размер изменился
        assertElementResized(newSize, initialSize);

        // Проверка ограничения размера
        assertSizeRestriction(newSize);
    }

    @Test(description = "Тест изменения размеров элемента без ограничений", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тестирование изменения размеров элемента без ограничений")
    public void testResizableNoRestriction() {
        Dimension initialSize = getInitialElementSizeNoRestriction();

        // Увеличиваем размер на 200px по ширине и высоте
        resizeElementNoRestriction();

        Dimension newSize = getNewElementSizeNoRestriction();

        // Проверка, что размер изменился
        assertElementResized(newSize, initialSize);
    }

    @Step("Получение начального размера элемента с ограничениями")
    private Dimension getInitialElementSizeWithRestriction() {
        return resizablePage.getElementSize(resizablePage.getResizableBoxWithRestriction());
    }

    @Step("Изменение размера элемента с ограничениями на {0}px по ширине и {1}px по высоте")
    private void resizeElementWithRestriction() {
        resizablePage.resizeElement(resizablePage.getResizableHandleWithRestriction(), 100, 100);
    }

    @Step("Получение нового размера элемента с ограничениями")
    private Dimension getNewElementSizeWithRestriction() {
        return resizablePage.getElementSize(resizablePage.getResizableBoxWithRestriction());
    }

    @Step("Получение начального размера элемента без ограничений")
    private Dimension getInitialElementSizeNoRestriction() {
        return resizablePage.getElementSize(resizablePage.getResizableBoxNoRestriction());
    }

    @Step("Изменение размера элемента без ограничений на {0}px по ширине и {1}px по высоте")
    private void resizeElementNoRestriction() {
        resizablePage.resizeElement(resizablePage.getResizableHandleNoRestriction(), 200, 200);
    }

    @Step("Получение нового размера элемента без ограничений")
    private Dimension getNewElementSizeNoRestriction() {
        return resizablePage.getElementSize(resizablePage.getResizableBoxNoRestriction());
    }

    @Step("Проверка, что элемент был изменен по размеру")
    private void assertElementResized(Dimension newSize, Dimension initialSize) {
        Assert.assertTrue(newSize.width > initialSize.width, "Ширина должна увеличиться.");
        Assert.assertTrue(newSize.height > initialSize.height, "Высота должна увеличиться.");
    }

    @Step("Проверка, что размер элемента не превышает ограничения")
    private void assertSizeRestriction(Dimension newSize) {
        Assert.assertTrue(newSize.width <= 500, "Ширина не должна превышать 500px.");
        Assert.assertTrue(newSize.height <= 300, "Высота не должна превышать 300px.");
    }
}
