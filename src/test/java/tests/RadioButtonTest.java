package tests;

import com.codeborne.selenide.Configuration;
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
        Configuration.reopenBrowserOnFail = true;
        open("https://demoqa.com/radio-button");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Проверка работы радио-кнопки 'Yes'", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectYes() {
        radioButtonPage.selectYes();
        String result = radioButtonPage.getSelectedResult();
        assertEquals(result, "You have selected Yes", "Ожидается результат: 'You have selected Yes'");
    }

    @Test(description = "Проверка работы радио-кнопки 'Impressive'", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectImpressive() {
        radioButtonPage.selectImpressive();
        String result = radioButtonPage.getSelectedResult();
        assertEquals(result, "You have selected Impressive", "Ожидается результат: 'You have selected Impressive'");
    }

    @Test(description = "Проверка, что кнопка 'No' отключена", retryAnalyzer = RetryAnalyzer.class)
    public void testNoDisabled() {
        boolean isDisabled = radioButtonPage.isNoDisabled();  // Проверяем состояние кнопки
        assertTrue(isDisabled, "Кнопка 'No' должна быть отключена.");
    }
}