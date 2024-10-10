package tests;

import org.testng.annotations.*;
import pages.TextBoxPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class TextBoxTest extends BaseTest {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeMethod
    @Step("Открытие страницы Text Box и настройка браузера")
    public void setUp() {
        open("https://demoqa.com/text-box");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Тестирование Text Box", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тестирование заполнения формы Text Box")
    public void testFillTextBox() {
        fillTextBoxFields();
        submitForm();
    }

    @Step("Заполнение полей формы Text Box")
    private void fillTextBoxFields() {
        textBoxPage.fillFullName("John Doe");
        textBoxPage.fillEmail("john.doe@example.com");
        textBoxPage.fillCurrentAddress("123 Street");
        textBoxPage.fillPermanentAddress("456 Avenue");
    }

    @Step("Отправка формы Text Box")
    private void submitForm() {
        textBoxPage.submitForm();
    }
}
