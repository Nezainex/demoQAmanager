package locators;

import org.openqa.selenium.By;

public class SelectMenuLocators {
    // Обновляем локаторы на XPath
    public static final By SELECT_VALUE = By.xpath("//div[@id='withOptGroup']//input");  // Локатор для "Select Value"
    public static final By SELECT_ONE = By.xpath("//div[@id='selectOne']//input");        // Локатор для "Select One"
    public static final By OLD_STYLE_SELECT_MENU = By.xpath("//select[@id='oldSelectMenu']");
    public static final By MULTISELECT_DROPDOWN = By.xpath("//div[@id='selectMenuContainer']//input[@id='react-select-4-input']");
    public static final By STANDARD_MULTI_SELECT = By.xpath("//select[@id='cars']");
}
