package utils;

import io.qameta.allure.Allure;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 3; // Максимум 3 попытки

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            Allure.step("Попытка #" + retryCount, () -> {
                System.out.println("Retrying " + result.getName() + " for the " + retryCount + " time.");
            });
            return true;
        }
        return false;
    }
}
