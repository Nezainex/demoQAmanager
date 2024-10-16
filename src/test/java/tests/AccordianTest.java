package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AccordianPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class AccordianTest extends BaseTest {

    private final AccordianPage accordianPage = new AccordianPage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Accordian", () -> open("https://demoqa.com/accordian"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
    }

    @Test(description = "Тестирование открытия и закрытия раздела 1", retryAnalyzer = RetryAnalyzer.class)
    public void testSection1() {
        Allure.step("Тестирование раздела 1", () -> {
            verifySection1Visible();
            clickSection1Heading();
            verifySection1Hidden();
            clickSection1Heading();
            verifySection1Visible();
        });
    }

    @Test(description = "Тестирование открытия и закрытия раздела 2", retryAnalyzer = RetryAnalyzer.class)
    public void testSection2() {
        Allure.step("Тестирование раздела 2", () -> {
            verifySection2Hidden();
            clickSection2Heading();
            verifySection2Visible();
            clickSection2Heading();
            verifySection2Hidden();
        });
    }

    @Test(description = "Тестирование открытия и закрытия раздела 3", retryAnalyzer = RetryAnalyzer.class)
    public void testSection3() {
        Allure.step("Тестирование раздела 3", () -> {
            verifySection3Hidden();
            clickSection3Heading();
            verifySection3Visible();
            clickSection3Heading();
            verifySection3Hidden();
        });
    }

    @AfterMethod
    public void tearDown() {
        Allure.step("Закрытие браузера", () -> {
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
        });
    }

    // Steps for Section 1
    private void verifySection1Visible() {
        Allure.step("Проверить, что содержимое раздела 1 видно", accordianPage::verifySection1ContentVisible);
    }

    private void clickSection1Heading() {
        Allure.step("Кликнуть на заголовок раздела 1", accordianPage::clickSection1Heading);
    }

    private void verifySection1Hidden() {
        Allure.step("Проверить, что содержимое раздела 1 скрыто", accordianPage::verifySection1ContentHidden);
    }

    // Steps for Section 2
    private void verifySection2Hidden() {
        Allure.step("Проверить, что содержимое раздела 2 скрыто", accordianPage::verifySection2ContentHidden);
    }

    private void clickSection2Heading() {
        Allure.step("Кликнуть на заголовок раздела 2", accordianPage::clickSection2Heading);
    }

    private void verifySection2Visible() {
        Allure.step("Проверить, что содержимое раздела 2 видно", accordianPage::verifySection2ContentVisible);
    }

    // Steps for Section 3
    private void verifySection3Hidden() {
        Allure.step("Проверить, что содержимое раздела 3 скрыто", accordianPage::verifySection3ContentHidden);
    }

    private void clickSection3Heading() {
        Allure.step("Кликнуть на заголовок раздела 3", accordianPage::clickSection3Heading);
    }

    private void verifySection3Visible() {
        Allure.step("Проверить, что содержимое раздела 3 видно", accordianPage::verifySection3ContentVisible);
    }
}
