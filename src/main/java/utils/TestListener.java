package utils;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        Object retryCount = result.getAttribute("retryCount");
        if (retryCount != null) {
            Allure.step(result.getName() + " прошел успешно с " + retryCount + "-й попытки.",
                    () -> System.out.println(result.getName() + " passed successfully on attempt " + retryCount));
        } else {
            Allure.step(result.getName() + " прошел успешно с первой попытки.",
                    () -> System.out.println(result.getName() + " passed successfully on the first attempt."));
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.step(result.getName() + " не прошел.",
                () -> System.out.println(result.getName() + " failed."));
    }

    // Остальные методы остаются без изменений
    @Override
    public void onTestStart(ITestResult result) {}
    @Override
    public void onTestSkipped(ITestResult result) {}
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {}
}
