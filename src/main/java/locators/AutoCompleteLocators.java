package locators;

import org.openqa.selenium.By;

public class AutoCompleteLocators {
    // XPath для элемента с multiple input
    public static final By MULTIPLE_INPUT = By.xpath("//input[@id='autoCompleteMultipleInput']");
    // XPath для элемента с single input
    public static final By SINGLE_INPUT = By.xpath("//input[@id='autoCompleteSingleInput']");
    // XPath для опций автозаполнения
    public static final By AUTO_COMPLETE_OPTION = By.xpath("//div[contains(@class, 'auto-complete__option')]");
}
