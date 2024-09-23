package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.LogUtil;
import utils.RetryAnalyzer;
import utils.TestListener;

@Listeners(TestListener.class)
public class LogTest {

    @BeforeClass
    public void setUp() {
        // Дополнительная настройка может быть добавлена здесь, если нужно
    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void testLogging() {
        LogUtil.info("This is an info log message");
        LogUtil.error("This is an error log message", new RuntimeException("Test exception"));
        LogUtil.debug("This is a debug log message");
    }
}
