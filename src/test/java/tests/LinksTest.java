package tests;

import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LinksPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class LinksTest extends BaseTest {

    private LinksPage linksPage;

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Links и настройка окна", () -> {
            open("https://demoqa.com/links");
            getWebDriver().manage().window().maximize();
            linksPage = new LinksPage();
        });
    }

    @Test(description = "Проверка клика на простую ссылку", retryAnalyzer = RetryAnalyzer.class)
    public void testSimpleLink() {
        Allure.step("Клик на простую ссылку", () -> linksPage.clickSimpleLink());
    }

    @Test(description = "Проверка клика на динамическую ссылку", retryAnalyzer = RetryAnalyzer.class)
    public void testDynamicLink() {
        Allure.step("Клик на динамическую ссылку", () -> linksPage.clickDynamicLink());
    }

    @Test(description = "Проверка клика на ссылку 'Created'", retryAnalyzer = RetryAnalyzer.class)
    public void testCreatedLink() {
        Allure.step("Клик на ссылку 'Created'", () -> linksPage.clickCreatedLink());
    }

    @Test(description = "Проверка клика на ссылку 'No Content'", retryAnalyzer = RetryAnalyzer.class)
    public void testNoContentLink() {
        Allure.step("Клик на ссылку 'No Content'", () -> linksPage.clickNoContentLink());
    }

    @Test(description = "Проверка клика на ссылку 'Moved'", retryAnalyzer = RetryAnalyzer.class)
    public void testMovedLink() {
        Allure.step("Клик на ссылку 'Moved'", () -> linksPage.clickMovedLink());
    }

    @Test(description = "Проверка клика на ссылку 'Bad Request'", retryAnalyzer = RetryAnalyzer.class)
    public void testBadRequestLink() {
        Allure.step("Клик на ссылку 'Bad Request'", () -> linksPage.clickBadRequestLink());
    }

    @Test(description = "Проверка клика на ссылку 'Unauthorized'", retryAnalyzer = RetryAnalyzer.class)
    public void testUnauthorizedLink() {
        Allure.step("Клик на ссылку 'Unauthorized'", () -> linksPage.clickUnauthorizedLink());
    }

    @Test(description = "Проверка клика на ссылку 'Forbidden'", retryAnalyzer = RetryAnalyzer.class)
    public void testForbiddenLink() {
        Allure.step("Клик на ссылку 'Forbidden'", () -> linksPage.clickForbiddenLink());
    }

    @Test(description = "Проверка клика на ссылку 'Not Found'", retryAnalyzer = RetryAnalyzer.class)
    public void testNotFoundLink() {
        Allure.step("Клик на ссылку 'Not Found'", () -> linksPage.clickNotFoundLink());
    }
}
