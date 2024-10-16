package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.LogUtil;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

@Listeners(TestListener.class)
public class LogTest {

    @BeforeClass
    public void setUp() {
        Allure.step("Дополнительная настройка перед тестами", () -> {
            // Здесь может быть добавлена любая дополнительная настройка
        });
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testLogging() {
        Allure.step("Тестирование логов", () -> {
            logInfoStep();
            logErrorStep();
            logDebugStep();
        });
    }

    private void logInfoStep() {
        Allure.step("Логирование info сообщения", () -> LogUtil.info("This is an info log message"));
    }

    private void logErrorStep() {
        Allure.step("Логирование error сообщения", () -> LogUtil.error("This is an error log message", new RuntimeException("Test exception")));
    }

    private void logDebugStep() {
        Allure.step("Логирование debug сообщения", () -> LogUtil.debug("This is a debug log message"));
    }
}
