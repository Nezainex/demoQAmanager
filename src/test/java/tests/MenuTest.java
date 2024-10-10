package tests;

import io.qameta.allure.Step;
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
    @Step("Открытие страницы Menu и настройка окна")
    public void setUp() {
        open("https://demoqa.com/menu");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Click on Main Item 1", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на Main Item 1")
    public void clickMainItem1() {
        menuPage.getMainItem1().click();
    }

    @Test(description = "Hover over Main Item 2 and click on Sub Item", retryAnalyzer = RetryAnalyzer.class)
    @Step("Наведение на Main Item 2 и клик на Sub Item 1")
    public void clickSubItem() {
        menuPage.hoverMainItem2();
        menuPage.getSubItem1().click();
    }

    @Test(description = "Hover over Sub Sub List and click on Sub Sub Item 1", retryAnalyzer = RetryAnalyzer.class)
    @Step("Наведение на Sub Sub List и клик на Sub Sub Item 1")
    public void clickSubSubItem1() {
        menuPage.hoverMainItem2();
        menuPage.hoverSubSubList();
        menuPage.getSubSubItem1().click();
    }

    @Test(description = "Hover over Sub Sub List and click on Sub Sub Item 2", retryAnalyzer = RetryAnalyzer.class)
    @Step("Наведение на Sub Sub List и клик на Sub Sub Item 2")
    public void clickSubSubItem2() {
        menuPage.hoverMainItem2();
        menuPage.hoverSubSubList();
        menuPage.getSubSubItem2().click();
    }

    @Test(description = "Click on Main Item 3", retryAnalyzer = RetryAnalyzer.class)
    @Step("Клик на Main Item 3")
    public void clickMainItem3() {
        menuPage.getMainItem3().click();
    }
}
