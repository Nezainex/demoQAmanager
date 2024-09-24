package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import pages.LinksPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class LinksTest {

    private LinksPage linksPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/links");
        getWebDriver().manage().window().maximize();
        linksPage = new LinksPage();
    }

    @Test(description = "Проверка клика на простую ссылку", retryAnalyzer = RetryAnalyzer.class)
    public void testSimpleLink() {
        linksPage.clickSimpleLink();
    }

    @Test(description = "Проверка клика на динамическую ссылку", retryAnalyzer = RetryAnalyzer.class)
    public void testDynamicLink() {
        linksPage.clickDynamicLink();
    }

    @Test(description = "Проверка клика на ссылку 'Created'", retryAnalyzer = RetryAnalyzer.class)
    public void testCreatedLink() {
        linksPage.clickCreatedLink();
    }

    @Test(description = "Проверка клика на ссылку 'No Content'", retryAnalyzer = RetryAnalyzer.class)
    public void testNoContentLink() {
        linksPage.clickNoContentLink();
    }

    @Test(description = "Проверка клика на ссылку 'Moved'", retryAnalyzer = RetryAnalyzer.class)
    public void testMovedLink() {
        linksPage.clickMovedLink();
    }

    @Test(description = "Проверка клика на ссылку 'Bad Request'", retryAnalyzer = RetryAnalyzer.class)
    public void testBadRequestLink() {
        linksPage.clickBadRequestLink();
    }

    @Test(description = "Проверка клика на ссылку 'Unauthorized'", retryAnalyzer = RetryAnalyzer.class)
    public void testUnauthorizedLink() {
        linksPage.clickUnauthorizedLink();
    }

    @Test(description = "Проверка клика на ссылку 'Forbidden'", retryAnalyzer = RetryAnalyzer.class)
    public void testForbiddenLink() {
        linksPage.clickForbiddenLink();
    }

    @Test(description = "Проверка клика на ссылку 'Not Found'", retryAnalyzer = RetryAnalyzer.class)
    public void testNotFoundLink() {
        linksPage.clickNotFoundLink();
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
