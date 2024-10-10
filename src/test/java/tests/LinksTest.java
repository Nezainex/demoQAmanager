package tests;

import io.qameta.allure.Step;
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
    @Step("Открытие страницы Links и настройка окна")
    public void setUp() {
        open("https://demoqa.com/links");
        getWebDriver().manage().window().maximize();
        linksPage = new LinksPage();
    }

    @Test(description = "Проверка клика на простую ссылку", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на простую ссылку")
    public void testSimpleLink() {
        linksPage.clickSimpleLink();
    }

    @Test(description = "Проверка клика на динамическую ссылку", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на динамическую ссылку")
    public void testDynamicLink() {
        linksPage.clickDynamicLink();
    }

    @Test(description = "Проверка клика на ссылку 'Created'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на ссылку 'Created'")
    public void testCreatedLink() {
        linksPage.clickCreatedLink();
    }

    @Test(description = "Проверка клика на ссылку 'No Content'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на ссылку 'No Content'")
    public void testNoContentLink() {
        linksPage.clickNoContentLink();
    }

    @Test(description = "Проверка клика на ссылку 'Moved'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на ссылку 'Moved'")
    public void testMovedLink() {
        linksPage.clickMovedLink();
    }

    @Test(description = "Проверка клика на ссылку 'Bad Request'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на ссылку 'Bad Request'")
    public void testBadRequestLink() {
        linksPage.clickBadRequestLink();
    }

    @Test(description = "Проверка клика на ссылку 'Unauthorized'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на ссылку 'Unauthorized'")
    public void testUnauthorizedLink() {
        linksPage.clickUnauthorizedLink();
    }

    @Test(description = "Проверка клика на ссылку 'Forbidden'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на ссылку 'Forbidden'")
    public void testForbiddenLink() {
        linksPage.clickForbiddenLink();
    }

    @Test(description = "Проверка клика на ссылку 'Not Found'", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на ссылку 'Not Found'")
    public void testNotFoundLink() {
        linksPage.clickNotFoundLink();
    }
}
