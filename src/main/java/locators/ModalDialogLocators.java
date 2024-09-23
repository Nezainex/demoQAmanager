package locators;

import org.openqa.selenium.By;

public class ModalDialogLocators {
    // Локаторы для кнопок открытия модальных окон через XPath
    public static final By SMALL_MODAL_BUTTON = By.xpath("//button[@id='showSmallModal']");
    public static final By LARGE_MODAL_BUTTON = By.xpath("//button[@id='showLargeModal']");

    // Локаторы для кнопок закрытия модальных окон через XPath
    public static final By CLOSE_SMALL_MODAL_BUTTON = By.xpath("//button[@id='closeSmallModal']");
    public static final By CLOSE_LARGE_MODAL_BUTTON = By.xpath("//button[@id='closeLargeModal']");
}
