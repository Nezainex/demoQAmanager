package locators;

import org.openqa.selenium.By;

public class BrowserWindowsLocators {
    // Используем XPath для кнопки новой вкладки
    public static final By NEW_TAB_BUTTON = By.xpath("//button[@id='tabButton']");
    // Используем XPath для кнопки нового окна
    public static final By NEW_WINDOW_BUTTON = By.xpath("//button[@id='windowButton']");
    // Используем XPath для кнопки нового окна с сообщением
    public static final By NEW_WINDOW_MESSAGE_BUTTON = By.xpath("//button[@id='messageWindowButton']");
}
