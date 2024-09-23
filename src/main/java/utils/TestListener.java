package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        Object retryCount = result.getAttribute("retryCount");
        if (retryCount != null) {
            System.out.println(result.getName() + " passed successfully on attempt " + retryCount);
        } else {
            System.out.println(result.getName() + " passed successfully on the first attempt.");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getName() + " failed.");
    }

    // Остальные методы можно оставить пустыми, если они не нужны
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
