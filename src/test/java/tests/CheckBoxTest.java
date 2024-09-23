package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import pages.CheckBoxPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class CheckBoxTest {

    private final CheckBoxPage checkBoxPage = new CheckBoxPage();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = false;  // Открытие нового браузера для каждого теста
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";  // Тесты начнутся сразу после загрузки DOM
        Configuration.pageLoadTimeout = 30000;  // Максимум 30 секунд для полной загрузки страницы
        open("https://demoqa.com/checkbox");
        getWebDriver().manage().window().maximize();
    }

    @Test (description = "Тестирование testExpandAndCollapse", retryAnalyzer = RetryAnalyzer.class)
    public void testExpandAndCollapse() {
        checkBoxPage.expandAll();
        checkBoxPage.collapseAll();
    }

    @Test (description = "Тестирование выбора первого чекбокса", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectCheckbox() {
        checkBoxPage.expandAll();
        checkBoxPage.selectCheckbox(0); // Выбираем первый чекбокс
        String result = checkBoxPage.getResultText();
        assert result.contains("home");
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
