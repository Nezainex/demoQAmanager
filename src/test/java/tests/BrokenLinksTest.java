package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BrokenLinksPage;
import utils.RetryAnalyzer;
import utils.TestListener;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BrokenLinksTest {

    private BrokenLinksPage brokenLinksPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/broken");
        getWebDriver().manage().window().maximize();
        brokenLinksPage = new BrokenLinksPage();
    }

    @Test(description = "Проверка наличия валидного изображения", retryAnalyzer = RetryAnalyzer.class)
    public void testValidImage() {
        Assert.assertTrue(brokenLinksPage.isValidImageDisplayed(), "Valid image is not displayed");
    }

    @Test(description = "Проверка наличия сломанного изображения", retryAnalyzer = RetryAnalyzer.class)
    public void testBrokenImage() {
        Assert.assertTrue(brokenLinksPage.isBrokenImageDisplayed(), "Broken image is not displayed");
    }

    @Test(description = "Проверка клика на валидную ссылку", retryAnalyzer = RetryAnalyzer.class)
    public void testValidLink() {
        brokenLinksPage.clickValidLink();
    }

    @Test(description = "Проверка клика на сломанную ссылку и получения кода 500", retryAnalyzer = RetryAnalyzer.class)
    public void testBrokenLink() {
        brokenLinksPage.clickBrokenLink();
        // Проверяем, что после перехода на сломанную ссылку видим код ошибки 500
        Assert.assertTrue(getWebDriver().getPageSource().contains("500"), "Ожидался статус 500, но он не был найден");
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
