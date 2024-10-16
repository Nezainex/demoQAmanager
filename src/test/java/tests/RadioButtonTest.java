package tests;

import io.qameta.allure.Allure;
import org.testng.annotations.*;
import pages.RadioButtonPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class RadioButtonTest extends BaseTest {

    private final RadioButtonPage radioButtonPage = new RadioButtonPage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы RadioButton", () -> open("https://demoqa.com/radio-button"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());

    }

    @Test(description = "Проверка работы радио-кнопки 'Yes'", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectYes() {
        Allure.step("Выбор радио-кнопки 'Yes' и проверка результата", () -> {
            radioButtonPage.selectYes();
            String result = radioButtonPage.getSelectedResult();
            assertEquals(result, "You have selected Yes", "Ожидается результат: 'You have selected Yes'");
        });
    }

    @Test(description = "Проверка работы радио-кнопки 'Impressive'", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectImpressive() {
        Allure.step("Выбор радио-кнопки 'Impressive' и проверка результата", () -> {
            radioButtonPage.selectImpressive();
            String result = radioButtonPage.getSelectedResult();
            assertEquals(result, "You have selected Impressive", "Ожидается результат: 'You have selected Impressive'");
        });
    }

    @Test(description = "Проверка, что кнопка 'No' отключена", retryAnalyzer = RetryAnalyzer.class)
    public void testNoDisabled() {
        Allure.step("Проверка, что кнопка 'No' отключена", () -> {
            boolean isDisabled = radioButtonPage.isNoDisabled();
            assertTrue(isDisabled, "Кнопка 'No' должна быть отключена.");
        });
    }
}
