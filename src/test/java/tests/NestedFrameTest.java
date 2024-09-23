package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import pages.NestedFramePage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class NestedFrameTest {

    private final NestedFramePage nestedFramePage = new NestedFramePage();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = false;  // Открытие нового браузера для каждого теста
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/nestedframes");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Проверка родительского фрейма", retryAnalyzer = RetryAnalyzer.class)
    public void testParentFrame() {
        nestedFramePage.switchToParentFrame(); // Тест на родительский фрейм
    }

    @Test(description = "Проверка вложенного фрейма", retryAnalyzer = RetryAnalyzer.class)
    public void testChildFrame() {
        nestedFramePage.switchToChildFrame(); // Тест на вложенный фрейм
    }

    @Test(description = "Проверка количества iframe на главной странице", retryAnalyzer = RetryAnalyzer.class)
    public void testIframeCountInMainPage() {
        System.out.println("Number of iframes on the main page: " + nestedFramePage.getIFrameCountInMainPage());
    }

    @Test(description = "Проверка количества iframe в родительском фрейме", retryAnalyzer = RetryAnalyzer.class)
    public void testIframeCountInParentFrame() {
        System.out.println("Number of iframes in the parent frame: " + nestedFramePage.getIFrameCountInParentFrame());
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
