package tests;

import org.testng.annotations.*;
import pages.ButtonsPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ButtonsTest extends BaseTest {

    private ButtonsPage buttonsPage;

    @BeforeMethod
    public void setUp() {
        open("https://demoqa.com/buttons");
        getWebDriver().manage().window().maximize();
        buttonsPage = new ButtonsPage();
    }

    @Test(description = "Проверка двойного клика на кнопке", retryAnalyzer = RetryAnalyzer.class)
    public void testDoubleClickButton() {
        buttonsPage.doubleClickButton();
    }

    @Test(description = "Проверка правого клика на кнопке", retryAnalyzer = RetryAnalyzer.class)
    public void testRightClickButton() {
        buttonsPage.rightClickButton();
    }

    @Test(description = "Проверка обычного клика на кнопке", retryAnalyzer = RetryAnalyzer.class)
    public void testClickMeButton() {
        buttonsPage.clickMeButton();
    }
}
