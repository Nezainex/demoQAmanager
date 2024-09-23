package locators;

import org.openqa.selenium.By;

public class ResizableLocators {
    // Используем XPath для определения элементов
    public static final By RESIZABLE_BOX_WITH_RESTRICTION = By.xpath("//div[@id='resizableBoxWithRestriction']");
    public static final By RESIZABLE_HANDLE_WITH_RESTRICTION = By.xpath("//div[@id='resizableBoxWithRestriction']//span[contains(@class, 'react-resizable-handle-se')]");

    public static final By RESIZABLE_BOX_NO_RESTRICTION = By.xpath("//div[@id='resizable']");
    public static final By RESIZABLE_HANDLE_NO_RESTRICTION = By.xpath("//div[@id='resizable']//span[contains(@class, 'react-resizable-handle-se')]");
}
