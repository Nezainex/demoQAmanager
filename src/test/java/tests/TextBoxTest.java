package tests;

import org.testng.annotations.*;
import pages.TextBoxPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class TextBoxTest extends BaseTest {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeMethod
    public void setUp() {
        open("https://demoqa.com/text-box");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Тестирование Text Box", retryAnalyzer = RetryAnalyzer.class)
    public void testFillTextBox() {
        textBoxPage.fillFullName("John Doe");
        textBoxPage.fillEmail("john.doe@example.com");
        textBoxPage.fillCurrentAddress("123 Street");
        textBoxPage.fillPermanentAddress("456 Avenue");
        textBoxPage.submitForm();
    }
}
