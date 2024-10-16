package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.TestSuiteListener;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({TestSuiteListener.class})
public class BaseTest {

    @BeforeMethod
    public void setUpBrowser() {
        Allure.step("Настройка браузера", () -> {
            Allure.step("Установка WebDriver для Firefox", () -> WebDriverManager.firefoxdriver().setup());
            Allure.step("Настройка браузера: firefox", () -> Configuration.browser = "firefox");
            Allure.step("Установка тайм-аута ожидания элементов: 5000ms", () -> Configuration.timeout = 5000);
            Allure.step("Разрешение на перезапуск браузера при ошибке", () -> Configuration.reopenBrowserOnFail = true);
            Allure.step("Установка стратегии загрузки страниц: eager", () -> Configuration.pageLoadStrategy = "eager");
            Allure.step("Установка тайм-аута загрузки страниц: 30000ms", () -> Configuration.pageLoadTimeout = 30000);
            Allure.step("Отключение headless режима (браузер с графическим интерфейсом)", () -> Configuration.headless = false);
        });
    }

    @AfterMethod
    public void tearDownBrowser() {
        Allure.step("Закрытие браузера", () -> {
            try {
                for (String handle : getWebDriver().getWindowHandles()) {
                    Allure.step("Закрытие окна браузера с handle: " + handle, () -> getWebDriver().switchTo().window(handle).close());
                }
            } catch (Exception e) {
                Allure.step("Ошибка при закрытии окон: " + e.getMessage(), () -> {});
            } finally {
                try {
                    Allure.step("Завершение сессии WebDriver", () -> getWebDriver().quit());
                } catch (Exception e) {
                    Allure.step("Ошибка при завершении сессии WebDriver: " + e.getMessage(), () -> {});
                }
            }
        });
    }
}
