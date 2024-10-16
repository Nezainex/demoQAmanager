package tests;

import io.qameta.allure.Allure;
import org.testng.annotations.*;
import pages.MenuPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class MenuTest extends BaseTest {

    MenuPage menuPage = new MenuPage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Menu", () -> open("https://demoqa.com/menu"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
    }

    @Test(description = "Click on Main Item 1", retryAnalyzer = RetryAnalyzer.class)
    public void clickMainItem1() {
        Allure.step("Клик на Main Item 1", () -> menuPage.getMainItem1().click());
    }

    @Test(description = "Hover over Main Item 2 and click on Sub Item", retryAnalyzer = RetryAnalyzer.class)
    public void clickSubItem() {
        Allure.step("Наведение на Main Item 2 и клик на Sub Item 1", () -> {
            menuPage.hoverMainItem2();
            menuPage.getSubItem1().click();
        });
    }

    @Test(description = "Hover over Sub Sub List and click on Sub Sub Item 1", retryAnalyzer = RetryAnalyzer.class)
    public void clickSubSubItem1() {
        Allure.step("Наведение на Sub Sub List и клик на Sub Sub Item 1", () -> {
            menuPage.hoverMainItem2();
            menuPage.hoverSubSubList();
            menuPage.getSubSubItem1().click();
        });
    }

    @Test(description = "Hover over Sub Sub List and click on Sub Sub Item 2", retryAnalyzer = RetryAnalyzer.class)
    public void clickSubSubItem2() {
        Allure.step("Наведение на Sub Sub List и клик на Sub Sub Item 2", () -> {
            menuPage.hoverMainItem2();
            menuPage.hoverSubSubList();
            menuPage.getSubSubItem2().click();
        });
    }

    @Test(description = "Click on Main Item 3", retryAnalyzer = RetryAnalyzer.class)
    public void clickMainItem3() {
        Allure.step("Клик на Main Item 3", () -> menuPage.getMainItem3().click());
    }
}
