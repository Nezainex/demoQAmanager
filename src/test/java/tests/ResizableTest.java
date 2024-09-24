package tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ResizablePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ResizableTest {

    private ResizablePage resizablePage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/resizable");
        getWebDriver().manage().window().maximize();
        resizablePage = new ResizablePage();
    }

    @Test(description = "Тест изменения размеров элемента с ограничениями", retryAnalyzer = RetryAnalyzer.class)
    public void testResizableWithRestriction() {
        Dimension initialSize = resizablePage.getElementSize(resizablePage.getResizableBoxWithRestriction());

        // Увеличиваем размер на 100px по ширине и высоте
        resizablePage.resizeElement(resizablePage.getResizableHandleWithRestriction(), 100, 100);

        Dimension newSize = resizablePage.getElementSize(resizablePage.getResizableBoxWithRestriction());

        // Проверка, что размер изменился
        Assert.assertTrue(newSize.width > initialSize.width, "Ширина должна увеличиться.");
        Assert.assertTrue(newSize.height > initialSize.height, "Высота должна увеличиться.");

        // Проверка ограничения размера
        Assert.assertTrue(newSize.width <= 500, "Ширина не должна превышать 500px.");
        Assert.assertTrue(newSize.height <= 300, "Высота не должна превышать 300px.");
    }

    @Test(description = "Тест изменения размеров элемента без ограничений", retryAnalyzer = RetryAnalyzer.class)
    public void testResizableNoRestriction() {
        Dimension initialSize = resizablePage.getElementSize(resizablePage.getResizableBoxNoRestriction());

        // Увеличиваем размер на 200px по ширине и высоте
        resizablePage.resizeElement(resizablePage.getResizableHandleNoRestriction(), 200, 200);

        Dimension newSize = resizablePage.getElementSize(resizablePage.getResizableBoxNoRestriction());

        // Проверка, что размер изменился
        Assert.assertTrue(newSize.width > initialSize.width, "Ширина должна увеличиться.");
        Assert.assertTrue(newSize.height > initialSize.height, "Высота должна увеличиться.");
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
