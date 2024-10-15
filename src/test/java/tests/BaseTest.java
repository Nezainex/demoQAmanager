package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import io.qameta.allure.Step;
import utils.TestSuiteListener;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({TestSuiteListener.class})  // Добавляем слушателя ко всем тестам, наследующим этот класс
public class BaseTest {

    @BeforeMethod
    @Step("Настройка браузера")
    public void setUpBrowser() {
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        Configuration.timeout = 5000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.pageLoadStrategy = "eager";
        Configuration.pageLoadTimeout = 30000;
        Configuration.headless = false; //Если true, то позволяет провести тесты без отображения пользовательского интерфейса, но это работает не со всеми тестами
        // (Некоторые специфические элементы, такие как модальные окна или всплывающие окна, могут вести себя по-разному в headless режиме)

    }

    @AfterMethod
    @Step("Закрытие браузера")
    public void tearDownBrowser() {
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
