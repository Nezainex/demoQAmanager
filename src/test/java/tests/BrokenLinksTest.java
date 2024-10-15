package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BrokenLinksPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BrokenLinksTest extends BaseTest {

    private BrokenLinksPage brokenLinksPage;

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы проверки ссылок и настройка окна", () -> open("https://demoqa.com/broken"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
        brokenLinksPage = new BrokenLinksPage();
    }

    @Test(description = "Проверка наличия валидного изображения", retryAnalyzer = RetryAnalyzer.class)
    public void testValidImage() {
        Allure.step("Проверка отображения валидного изображения",
                () -> Assert.assertTrue(brokenLinksPage.isValidImageDisplayed(), "Valid image is not displayed"));
    }

    @Test(description = "Проверка наличия сломанного изображения", retryAnalyzer = RetryAnalyzer.class)
    public void testBrokenImage() {
        Allure.step("Проверка отображения сломанного изображения",
                () -> Assert.assertTrue(brokenLinksPage.isBrokenImageDisplayed(), "Broken image is not displayed"));
    }

    @Test(description = "Проверка клика на валидную ссылку", retryAnalyzer = RetryAnalyzer.class)
    public void testValidLink() {
        Allure.step("Клик на валидную ссылку", brokenLinksPage::clickValidLink);
    }

    @Test(description = "Проверка клика на сломанную ссылку и получения кода 500", retryAnalyzer = RetryAnalyzer.class)
    public void testBrokenLink() {
        Allure.step("Клик на сломанную ссылку", brokenLinksPage::clickBrokenLink);
        Allure.step("Проверка получения кода 500",
                () -> Assert.assertTrue(Objects.requireNonNull(getWebDriver().getPageSource()).contains("500"), "Ожидался статус 500, но он не был найден"));
    }
}
