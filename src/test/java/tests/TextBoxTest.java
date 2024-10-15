package tests;

import org.testng.annotations.*;
import pages.TextBoxPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class TextBoxTest extends BaseTest {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Text Box и настройка браузера", () -> {
            open("https://demoqa.com/text-box");
            getWebDriver().manage().window().maximize();
        });
    }

    @Test(description = "Тестирование Text Box", retryAnalyzer = RetryAnalyzer.class)
    public void testFillTextBox() {
        Allure.step("Тестирование заполнения формы Text Box", () -> {
            fillTextBoxFields();
            submitForm();
        });
    }

    private void fillTextBoxFields() {
        Allure.step("Заполнение полей формы Text Box", () -> {
            textBoxPage.fillFullName("John Doe");
            textBoxPage.fillEmail("john.doe@example.com");
            textBoxPage.fillCurrentAddress("123 Street");
            textBoxPage.fillPermanentAddress("456 Avenue");
        });
    }

    private void submitForm() {
        Allure.step("Отправка формы Text Box", textBoxPage::submitForm);
    }
}
