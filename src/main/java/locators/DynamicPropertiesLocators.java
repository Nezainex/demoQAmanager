package locators;

import org.openqa.selenium.By;

public class DynamicPropertiesLocators {
    // Кнопка, которая становится активной через 5 секунд
    public static final By ENABLE_BUTTON = By.xpath("//button[@id='enableAfter']");

    // Кнопка с изменяющимся цветом
    public static final By COLOR_CHANGE_BUTTON = By.xpath("//button[@id='colorChange']");

    // Кнопка, которая становится видимой через 5 секунд
    public static final By VISIBLE_AFTER_BUTTON = By.xpath("//button[@id='visibleAfter']");

    // Параграф с текстом, содержащим случайный Id
    public static final By RANDOM_TEXT = By.xpath("//p[contains(text(), 'This text has random Id')]");
}
