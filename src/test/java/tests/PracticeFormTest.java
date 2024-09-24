package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import pages.PracticeFormPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class PracticeFormTest {

    private final PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.pageLoadStrategy = "eager";
        Configuration.pageLoadTimeout = 30000;
        Configuration.reopenBrowserOnFail = true;
        open("https://demoqa.com/automation-practice-form");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Заполнение имени", retryAnalyzer = RetryAnalyzer.class)
    public void testFillFirstName() {
        practiceFormPage.fillFirstName("John");
    }

    @Test(description = "Заполнение фамилии", retryAnalyzer = RetryAnalyzer.class)
    public void testFillLastName() {
        practiceFormPage.fillLastName("Doe");
    }

    @Test(description = "Заполнение email", retryAnalyzer = RetryAnalyzer.class)
    public void testFillEmail() {
        practiceFormPage.fillEmail("john.doe@example.com");
    }

    @Test(description = "Выбор пола", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectGender() {
        practiceFormPage.selectGender();
    }

    @Test(description = "Заполнение мобильного номера", retryAnalyzer = RetryAnalyzer.class)
    public void testFillMobile() {
        practiceFormPage.fillMobile("1234567890");
    }

    @Test(description = "Выбор предмета", retryAnalyzer = RetryAnalyzer.class)
    public void testFillSubject() {
        practiceFormPage.fillSubject("Math");
    }

    @Test(description = "Выбор даты рождения", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectDateOfBirth() {
        practiceFormPage.selectDateOfBirth("15", "September", "1990");
    }

    @Test(description = "Выбор хобби", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectHobbies() {
        practiceFormPage.selectHobbies("Sports", "Music", "Reading");
    }

    @Test(description = "Загрузка файла", retryAnalyzer = RetryAnalyzer.class)
    public void testUploadFile() {
        practiceFormPage.uploadFile("C:/Users/Username/IdeaProjects/demoQAmanager/src/main/resources/sampleFile.jpeg");
    }

    @Test(description = "Заполнение текущего адреса", retryAnalyzer = RetryAnalyzer.class)
    public void testFillCurrentAddress() {
        practiceFormPage.fillCurrentAddress("123 Main St, Springfield");
    }

    @Test(description = "Выбор штата и города", retryAnalyzer = RetryAnalyzer.class)
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
    }
*/
    @Test(description = "Отправка формы", retryAnalyzer = RetryAnalyzer.class)
    public void testSubmitForm() {
        practiceFormPage.submitForm();
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