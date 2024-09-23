package locators;

import org.openqa.selenium.By;

public class BrokenLinksLocators {
    public static final By VALID_IMAGE = By.xpath("//img[@src='/images/Toolsqa.jpg']");
    public static final By BROKEN_IMAGE = By.xpath("//img[@src='/images/Toolsqa_1.jpg']");
    public static final By VALID_LINK = By.xpath("//a[contains(text(), 'Click Here for Valid Link')]");
    public static final By BROKEN_LINK = By.xpath("//a[@href='http://the-internet.herokuapp.com/status_codes/500']");

}
