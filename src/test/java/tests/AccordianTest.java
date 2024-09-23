package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AccordianPage;
import utils.RetryAnalyzer;
import utils.TestListener;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class AccordianTest {

    private final AccordianPage accordianPage = new AccordianPage();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = false;  // Открытие нового браузера для каждого теста
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/accordian");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Тестирование открытия и закрытия раздела 1", retryAnalyzer = RetryAnalyzer.class)
    public void testSection1() {
        accordianPage.verifySection1ContentVisible();
        accordianPage.clickSection1Heading();
        accordianPage.verifySection1ContentHidden();
        accordianPage.clickSection1Heading();
        accordianPage.verifySection1ContentVisible();
    }

    @Test(description = "Тестирование открытия и закрытия раздела 2", retryAnalyzer = RetryAnalyzer.class)
    public void testSection2() {
        accordianPage.verifySection2ContentHidden();
        accordianPage.clickSection2Heading();
        accordianPage.verifySection2ContentVisible();
        accordianPage.clickSection2Heading();
        accordianPage.verifySection2ContentHidden();
    }

    @Test(description = "Тестирование открытия и закрытия раздела 3", retryAnalyzer = RetryAnalyzer.class)
    public void testSection3() {
        accordianPage.verifySection3ContentHidden();
        accordianPage.clickSection3Heading();
        accordianPage.verifySection3ContentVisible();
        accordianPage.clickSection3Heading();
        accordianPage.verifySection3ContentHidden();
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
