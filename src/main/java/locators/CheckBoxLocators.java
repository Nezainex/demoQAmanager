package locators;

import org.openqa.selenium.By;

public class CheckBoxLocators {
    // Используем XPath для кнопок "развернуть все" и "свернуть все"
    public static final By EXPAND_ALL_BUTTON = By.xpath("//button[@title='Expand all']");
    public static final By COLLAPSE_ALL_BUTTON = By.xpath("//button[@title='Collapse all']");
    // Используем XPath для чекбоксов
    public static final By CHECKBOX_ITEM = By.xpath("//span[@class='rct-checkbox']");
    // Используем XPath для отображения результата
    public static final By RESULT_TEXT = By.xpath("//div[@id='result']");
}
