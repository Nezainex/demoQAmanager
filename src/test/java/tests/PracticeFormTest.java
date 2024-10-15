package tests;

import io.qameta.allure.Allure;
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
    public void setUp() {
        Allure.step("Открытие страницы формы и настройка окна", () -> {
            open("https://demoqa.com/automation-practice-form");
            getWebDriver().manage().window().maximize();
        });
    }

    @Test(description = "Заполнение имени", retryAnalyzer = RetryAnalyzer.class)
    public void testFillFirstName() {
        Allure.step("Заполнение поля имени", () -> practiceFormPage.fillFirstName("John"));
    }

    @Test(description = "Заполнение фамилии", retryAnalyzer = RetryAnalyzer.class)
    public void testFillLastName() {
        Allure.step("Заполнение поля фамилии", () -> practiceFormPage.fillLastName("Doe"));
    }

    @Test(description = "Заполнение email", retryAnalyzer = RetryAnalyzer.class)
    public void testFillEmail() {
        Allure.step("Заполнение поля email", () -> practiceFormPage.fillEmail("john.doe@example.com"));
    }

    @Test(description = "Выбор пола", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectGender() {
        Allure.step("Выбор пола", practiceFormPage::selectGender);
    }

    @Test(description = "Заполнение мобильного номера", retryAnalyzer = RetryAnalyzer.class)
    public void testFillMobile() {
        Allure.step("Заполнение мобильного номера", () -> practiceFormPage.fillMobile("1234567890"));
    }

    @Test(description = "Выбор предмета", retryAnalyzer = RetryAnalyzer.class)
    public void testFillSubject() {
        Allure.step("Выбор предмета", () -> practiceFormPage.fillSubject("Math"));
    }

    @Test(description = "Выбор даты рождения", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectDateOfBirth() {
        Allure.step("Выбор даты рождения", () -> practiceFormPage.selectDateOfBirth("15", "September", "1990"));
    }

    @Test(description = "Выбор хобби", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectHobbies() {
        Allure.step("Выбор хобби", () -> practiceFormPage.selectHobbies("Sports", "Music", "Reading"));
    }

    @Test(description = "Загрузка файла", retryAnalyzer = RetryAnalyzer.class)
    public void testUploadFile() {
        Allure.step("Загрузка файла", () -> practiceFormPage.uploadFile("C:/Users/Username/IdeaProjects/demoQAmanager/src/main/resources/sampleFile.jpeg"));
    }

    @Test(description = "Заполнение текущего адреса", retryAnalyzer = RetryAnalyzer.class)
    public void testFillCurrentAddress() {
        Allure.step("Заполнение текущего адреса", () -> practiceFormPage.fillCurrentAddress("123 Main St, Springfield"));
    }

    @Test(description = "Выбор штата и города", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectState() {
        Allure.step("Выбор штата и города", () -> practiceFormPage.selectStateAndCity("NCR", "Delhi"));
    }

    @Test(description = "Отправка формы", retryAnalyzer = RetryAnalyzer.class)
    public void testSubmitForm() {
        Allure.step("Отправка формы", practiceFormPage::submitForm);
    }
}
