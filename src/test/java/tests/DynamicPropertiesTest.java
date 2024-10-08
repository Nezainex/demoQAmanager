package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DynamicPropertiesPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class DynamicPropertiesTest extends BaseTest {

    private DynamicPropertiesPage dynamicPropertiesPage;

    @BeforeMethod
    public void setUp() {
        open("https://demoqa.com/dynamic-properties");
        getWebDriver().manage().window().maximize();
        dynamicPropertiesPage = new DynamicPropertiesPage();
    }

    @Test(description = "Проверка, что кнопка становится активной через 5 секунд", retryAnalyzer = RetryAnalyzer.class)
    public void testEnableButton() {
        dynamicPropertiesPage.waitForEnableButton();
    }

    @Test(description = "Проверка, что кнопка меняет цвет через 5 секунд", retryAnalyzer = RetryAnalyzer.class)
    public void testColorChange() {
        dynamicPropertiesPage.verifyColorChange();
    }

    @Test(description = "Проверка, что кнопка становится видимой через 5 секунд", retryAnalyzer = RetryAnalyzer.class)
    public void testVisibleAfterButton() {
        dynamicPropertiesPage.waitForVisibleButton();
    }

    @Test(description = "Проверка наличия текста с рандомным id", retryAnalyzer = RetryAnalyzer.class)
    public void testRandomText() {
        dynamicPropertiesPage.verifyRandomText();
    }
}
