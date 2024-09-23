package locators;

import org.openqa.selenium.By;

public class NestedFrameLocators {
    // Локатор для родительского фрейма
    public static final By PARENT_FRAME = By.xpath("//iframe[@id='frame1']");

    // Локатор для текста в родительском фрейме
    public static final By PARENT_FRAME_BODY = By.xpath("//body");

    // Локатор для вложенного фрейма внутри родительского фрейма
    public static final By CHILD_IFRAME = By.xpath("//iframe");

    // Локатор для текста в дочернем фрейме
    public static final By CHILD_FRAME_BODY = By.xpath("//body");
}
