package locators;

import org.openqa.selenium.By;

public class MenuLocators {
    public static final By MAIN_ITEM_1 = By.xpath("//a[text()='Main Item 1']");
    public static final By MAIN_ITEM_2 = By.xpath("//a[text()='Main Item 2']");
    public static final By MAIN_ITEM_3 = By.xpath("//a[text()='Main Item 3']");
    public static final By SUB_ITEM_1 = By.xpath("//ul/li/a[text()='Sub Item']");
    public static final By SUB_SUB_LIST = By.xpath("//a[text()='SUB SUB LIST »']");
    public static final By SUB_SUB_ITEM_1 = By.xpath("//ul/li/a[text()='Sub Sub Item 1']");
    public static final By SUB_SUB_ITEM_2 = By.xpath("//ul/li/a[text()='Sub Sub Item 2']");
}
