package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import pages.MenuPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class MenuTest {

    MenuPage menuPage = new MenuPage();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/menu");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Click on Main Item 1", retryAnalyzer = RetryAnalyzer.class)
    public void clickMainItem1() {
        menuPage.getMainItem1().click();
    }

    @Test(description = "Hover over Main Item 2 and click on Sub Item", retryAnalyzer = RetryAnalyzer.class)
    public void clickSubItem() {
        menuPage.hoverMainItem2();
        menuPage.getSubItem1().click();
    }

    @Test(description = "Hover over Sub Sub List and click on Sub Sub Item 1", retryAnalyzer = RetryAnalyzer.class)
    public void clickSubSubItem1() {
        menuPage.hoverMainItem2();
        menuPage.hoverSubSubList();
        menuPage.getSubSubItem1().click();
    }

    @Test(description = "Hover over Sub Sub List and click on Sub Sub Item 2", retryAnalyzer = RetryAnalyzer.class)
    public void clickSubSubItem2() {
        menuPage.hoverMainItem2();
        menuPage.hoverSubSubList();
        menuPage.getSubSubItem2().click();
    }

    @Test(description = "Click on Main Item 3", retryAnalyzer = RetryAnalyzer.class)
    public void clickMainItem3() {
        menuPage.getMainItem3().click();
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
