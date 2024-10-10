package tests;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BrokenLinksPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BrokenLinksTest extends BaseTest {

    private BrokenLinksPage brokenLinksPage;

    @BeforeMethod
    @Step("Открытие страницы проверки ссылок и настройка окна")
    public void setUp() {
        open("https://demoqa.com/broken");
        getWebDriver().manage().window().maximize();
        brokenLinksPage = new BrokenLinksPage();
    }

    @Test(description = "Проверка наличия валидного изображения", retryAnalyzer = RetryAnalyzer.class)
    @Step("Проверка отображения валидного изображения")
    public void testValidImage() {
        Assert.assertTrue(brokenLinksPage.isValidImageDisplayed(), "Valid image is not displayed");
    }

    @Test(description = "Проверка наличия сломанного изображения", retryAnalyzer = RetryAnalyzer.class)
    @Step("Проверка отображения сломанного изображения")
    public void testBrokenImage() {
        Assert.assertTrue(brokenLinksPage.isBrokenImageDisplayed(), "Broken image is not displayed");
    }

    @Test(description = "Проверка клика на валидную ссылку", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на валидную ссылку")
    public void testValidLink() {
        brokenLinksPage.clickValidLink();
    }

    @Test(description = "Проверка клика на сломанную ссылку и получения кода 500", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на сломанную ссылку и проверка получения статуса 500")
    public void testBrokenLink() {
        brokenLinksPage.clickBrokenLink();
        Assert.assertTrue(Objects.requireNonNull(getWebDriver().getPageSource()).contains("500"), "Ожидался статус 500, но он не был найден");
    }
}
