package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AccordianPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class AccordianTest extends BaseTest {


    private final AccordianPage accordianPage = new AccordianPage();

    @BeforeMethod
    public void setUp() {
        open("https://demoqa.com/accordian");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Тестирование открытия и закрытия раздела 1", retryAnalyzer = RetryAnalyzer.class)
    public void testSection1() {
        verifySection1Visible();
        clickSection1Heading();
        verifySection1Hidden();
        clickSection1Heading();
        verifySection1Visible();
    }

    @Test(description = "Тестирование открытия и закрытия раздела 2", retryAnalyzer = RetryAnalyzer.class)
    public void testSection2() {
        verifySection2Hidden();
        clickSection2Heading();
        verifySection2Visible();
        clickSection2Heading();
        verifySection2Hidden();
    }

    @Test(description = "Тестирование открытия и закрытия раздела 3", retryAnalyzer = RetryAnalyzer.class)
    public void testSection3() {
        verifySection3Hidden();
        clickSection3Heading();
        verifySection3Visible();
        clickSection3Heading();
        verifySection3Hidden();
    }

    @AfterMethod
    public void tearDown() {
        try {
            for (String handle : getWebDriver().getWindowHandles()) {
                getWebDriver().switchTo().window(handle).close();
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка при закрытии окон: " + e.getMessage());
        } finally {
            try {
                getWebDriver().quit();
            } catch (Exception e) {
                System.out.println("Ошибка при завершении сессии WebDriver: " + e.getMessage());
            }
        }
    }

    // Steps for Section 1
    @Step("Проверить, что содержимое раздела 1 видно")
    public void verifySection1Visible() {
        accordianPage.verifySection1ContentVisible();
    }

    @Step("Кликнуть на заголовок раздела 1")
    public void clickSection1Heading() {
        accordianPage.clickSection1Heading();
    }

    @Step("Проверить, что содержимое раздела 1 скрыто")
    public void verifySection1Hidden() {
        accordianPage.verifySection1ContentHidden();
    }

    // Steps for Section 2
    @Step("Проверить, что содержимое раздела 2 скрыто")
    public void verifySection2Hidden() {
        accordianPage.verifySection2ContentHidden();
    }

    @Step("Кликнуть на заголовок раздела 2")
    public void clickSection2Heading() {
        accordianPage.clickSection2Heading();
    }

    @Step("Проверить, что содержимое раздела 2 видно")
    public void verifySection2Visible() {
        accordianPage.verifySection2ContentVisible();
    }

    // Steps for Section 3
    @Step("Проверить, что содержимое раздела 3 скрыто")
    public void verifySection3Hidden() {
        accordianPage.verifySection3ContentHidden();
    }

    @Step("Кликнуть на заголовок раздела 3")
    public void clickSection3Heading() {
        accordianPage.clickSection3Heading();
    }

    @Step("Проверить, что содержимое раздела 3 видно")
    public void verifySection3Visible() {
        accordianPage.verifySection3ContentVisible();
    }
}
