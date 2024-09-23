package locators;

import org.openqa.selenium.By;

public class TextBoxLocators {
    public static final By FULL_NAME = By.xpath("//input[@id='userName']");
    public static final By EMAIL = By.xpath("//input[@id='userEmail']");
    public static final By CURRENT_ADDRESS = By.xpath("//textarea[@id='currentAddress']");
    public static final By PERMANENT_ADDRESS = By.xpath("//textarea[@id='permanentAddress']");
    public static final By SUBMIT = By.xpath("//button[@id='submit']");
}
