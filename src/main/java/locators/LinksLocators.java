package locators;

import org.openqa.selenium.By;

public class LinksLocators {
    public static final By SIMPLE_LINK = By.xpath("//a[@id='simpleLink']");
    public static final By DYNAMIC_LINK = By.xpath("//a[@id='dynamicLink']");
    public static final By CREATED_LINK = By.xpath("//a[@id='created']");
    public static final By NO_CONTENT_LINK = By.xpath("//a[@id='no-content']");
    public static final By MOVED_LINK = By.xpath("//a[@id='moved']");
    public static final By BAD_REQUEST_LINK = By.xpath("//a[@id='bad-request']");
    public static final By UNAUTHORIZED_LINK = By.xpath("//a[@id='unauthorized']");
    public static final By FORBIDDEN_LINK = By.xpath("//a[@id='forbidden']");
    public static final By NOT_FOUND_LINK = By.xpath("//a[@id='invalid-url']");
}
