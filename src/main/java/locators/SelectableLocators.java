package locators;

import org.openqa.selenium.By;

public class SelectableLocators {
    // Используем XPath для табов и элементов списка и сетки
    public static final By LIST_TAB = By.xpath("//a[@id='demo-tab-list']");
    public static final By GRID_TAB = By.xpath("//a[@id='demo-tab-grid']");

    // Локаторы для списка и элементов списка
    public static final By LIST_ITEMS = By.xpath("//ul[@id='verticalListContainer']//li[contains(@class, 'list-group-item')]");

    // Локаторы для сетки и элементов сетки
    public static final By GRID_ITEMS = By.xpath("//div[@id='gridContainer']//li[contains(@class, 'list-group-item')]");
}
