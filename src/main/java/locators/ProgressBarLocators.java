package locators;

import org.openqa.selenium.By;

public class ProgressBarLocators {
    public static final By START_STOP_BUTTON = By.xpath("//button[@id='startStopButton']");
    public static final By RESET_BUTTON = By.xpath("//button[@id='resetButton']");
    public static final By PROGRESS_BAR = By.xpath("//div[@role='progressbar']");
}
