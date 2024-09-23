package locators;

import org.openqa.selenium.By;

public class RadioButtonLocators {
    // Заменяем локаторы на XPath, используя <label> для клика
    public static final By YES_LABEL = By.xpath("//label[@for='yesRadio']");
    public static final By IMPRESSIVE_LABEL = By.xpath("//label[@for='impressiveRadio']");
    public static final By NO_RADIO = By.xpath("//input[@id='noRadio']");
    public static final By RADIO_RESULT = By.xpath("//p[@class='mt-3']");  // Заменяем на XPath
}
