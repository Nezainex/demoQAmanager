package locators;

import org.openqa.selenium.By;

public class SortableLocators {
    // Используем XPath для табов
    public static final By LIST_TAB = By.xpath("//a[@id='demo-tab-list']");
    public static final By GRID_TAB = By.xpath("//a[@id='demo-tab-grid']");

    // Локаторы для элементов списка
    public static final By LIST_ITEMS = By.xpath("//div[@id='demo-tabpane-list']//div[contains(@class, 'list-group-item')]");

    // Локаторы для элементов сетки
    public static final By GRID_ITEMS = By.xpath("//div[@id='demo-tabpane-grid']//div[contains(@class, 'list-group-item')]");
}
