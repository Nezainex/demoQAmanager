package tests;

import io.qameta.allure.Allure;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ResizablePage;
import pages.UploadDownloadPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ResizableTest extends BaseTest {

    private ResizablePage resizablePage;

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Resizable", () -> open("https://demoqa.com/resizable"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
        Allure.step("Создание страницы ResizablePage", () ->     resizablePage = new ResizablePage());
    }

    @Test(description = "Тест изменения размеров элемента с ограничениями", retryAnalyzer = RetryAnalyzer.class)
    public void testResizableWithRestriction() {
        Dimension initialSize = Allure.step("Получение начального размера элемента с ограничениями",
                () -> resizablePage.getElementSize(resizablePage.getResizableBoxWithRestriction())
        );

        Allure.step("Изменение размера элемента с ограничениями на 100px по ширине и 100px по высоте",
                () -> resizablePage.resizeElement(resizablePage.getResizableHandleWithRestriction(), 100, 100)
        );

        Dimension newSize = Allure.step("Получение нового размера элемента с ограничениями",
                () -> resizablePage.getElementSize(resizablePage.getResizableBoxWithRestriction())
        );

        Allure.step("Проверка, что элемент был изменен по размеру",
                () -> {
                    Assert.assertTrue(newSize.width > initialSize.width, "Ширина должна увеличиться.");
                    Assert.assertTrue(newSize.height > initialSize.height, "Высота должна увеличиться.");
                }
        );

        Allure.step("Проверка, что размер элемента не превышает ограничения",
                () -> {
                    Assert.assertTrue(newSize.width <= 500, "Ширина не должна превышать 500px.");
                    Assert.assertTrue(newSize.height <= 300, "Высота не должна превышать 300px.");
                }
        );
    }

    @Test(description = "Тест изменения размеров элемента без ограничений", retryAnalyzer = RetryAnalyzer.class)
    public void testResizableNoRestriction() {
        Dimension initialSize = Allure.step("Получение начального размера элемента без ограничений",
                () -> resizablePage.getElementSize(resizablePage.getResizableBoxNoRestriction())
        );

        Allure.step("Изменение размера элемента без ограничений на 200px по ширине и 200px по высоте",
                () -> resizablePage.resizeElement(resizablePage.getResizableHandleNoRestriction(), 200, 200)
        );

        Dimension newSize = Allure.step("Получение нового размера элемента без ограничений",
                () -> resizablePage.getElementSize(resizablePage.getResizableBoxNoRestriction())
        );

        Allure.step("Проверка, что элемент был изменен по размеру",
                () -> {
                    Assert.assertTrue(newSize.width > initialSize.width, "Ширина должна увеличиться.");
                    Assert.assertTrue(newSize.height > initialSize.height, "Высота должна увеличиться.");
                }
        );
    }
}
