package tests;

import io.qameta.allure.Step;
import org.testng.annotations.*;
        import pages.PracticeFormPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class PracticeFormTest extends BaseTest {

    private final PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeMethod
    @Step("Открытие страницы формы и настройка окна")
    public void setUp() {
        open("https://demoqa.com/automation-practice-form");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Заполнение имени", retryAnalyzer = RetryAnalyzer.class)
    @Step("Заполнение поля имени")
    public void testFillFirstName() {
        practiceFormPage.fillFirstName("John");
    }

    @Test(description = "Заполнение фамилии", retryAnalyzer = RetryAnalyzer.class)
    @Step("Заполнение поля фамилии")
    public void testFillLastName() {
        practiceFormPage.fillLastName("Doe");
    }

    @Test(description = "Заполнение email", retryAnalyzer = RetryAnalyzer.class)
    @Step("Заполнение поля email")
    public void testFillEmail() {
        practiceFormPage.fillEmail("john.doe@example.com");
    }

    @Test(description = "Выбор пола", retryAnalyzer = RetryAnalyzer.class)
    @Step("Выбор пола")
    public void testSelectGender() {
        practiceFormPage.selectGender();
    }

    @Test(description = "Заполнение мобильного номера", retryAnalyzer = RetryAnalyzer.class)
    @Step("Заполнение мобильного номера")
    public void testFillMobile() {
        practiceFormPage.fillMobile("1234567890");
    }

    @Test(description = "Выбор предмета", retryAnalyzer = RetryAnalyzer.class)
    @Step("Выбор предмета")
    public void testFillSubject() {
        practiceFormPage.fillSubject("Math");
    }

    @Test(description = "Выбор даты рождения", retryAnalyzer = RetryAnalyzer.class)
    @Step("Выбор даты рождения")
    public void testSelectDateOfBirth() {
        practiceFormPage.selectDateOfBirth("15", "September", "1990");
    }

    @Test(description = "Выбор хобби", retryAnalyzer = RetryAnalyzer.class)
    @Step("Выбор хобби")
    public void testSelectHobbies() {
        practiceFormPage.selectHobbies("Sports", "Music", "Reading");
    }

    @Test(description = "Загрузка файла", retryAnalyzer = RetryAnalyzer.class)
    @Step("Загрузка файла")
    public void testUploadFile() {
        practiceFormPage.uploadFile("C:/Users/Username/IdeaProjects/demoQAmanager/src/main/resources/sampleFile.jpeg");
    }

    @Test(description = "Заполнение текущего адреса", retryAnalyzer = RetryAnalyzer.class)
    @Step("Заполнение текущего адреса")
    public void testFillCurrentAddress() {
        practiceFormPage.fillCurrentAddress("123 Main St, Springfield");
    }

    @Test(description = "Выбор штата и города", retryAnalyzer = RetryAnalyzer.class)
    @Step("Выбор штата и города")
    public void testSelectState() {
        practiceFormPage.selectStateAndCity("NCR", "Delhi");
    }

    /*  @Test(description = "Выбор штата", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectState() {
        practiceFormPage.selectState("NCR");
    }

    @Test(description = "Выбор города", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectCity() {
        practiceFormPage.selectCity("Delhi");
    }*/

    @Test(description = "Отправка формы", retryAnalyzer = RetryAnalyzer.class)
    @Step("Отправка формы")
    public void testSubmitForm() {
        practiceFormPage.submitForm();
    }
}
