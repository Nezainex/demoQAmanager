package locators;

import org.openqa.selenium.By;

public class WebTableLocators {
    public static final By ADD_NEW_RECORD_BUTTON = By.xpath("//button[@id='addNewRecordButton']");
    public static final By FIRST_NAME = By.xpath("//input[@id='firstName']");
    public static final By LAST_NAME = By.xpath("//input[@id='lastName']");
    public static final By EMAIL = By.xpath("//input[@id='userEmail']");
    public static final By AGE = By.xpath("//input[@id='age']");
    public static final By SALARY = By.xpath("//input[@id='salary']");
    public static final By DEPARTMENT = By.xpath("//input[@id='department']");
    public static final By SUBMIT_BUTTON = By.xpath("//button[@id='submit']");
    public static final By EDIT_RECORD_BUTTON = By.xpath("//span[@id='edit-record-4']");
    public static final By DELETE_RECORD_BUTTON = By.xpath("//span[@id='delete-record-4']");
}
