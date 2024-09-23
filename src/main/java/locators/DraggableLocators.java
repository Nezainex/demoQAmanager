package locators;

import org.openqa.selenium.By;

public class DraggableLocators {

    // Tabs
    public static final By SIMPLE_TAB = By.xpath("//a[@id='draggableExample-tab-simple']");
    public static final By AXIS_RESTRICTED_TAB = By.xpath("//a[@id='draggableExample-tab-axisRestriction']");
    public static final By CONTAINER_RESTRICTED_TAB = By.xpath("//a[@id='draggableExample-tab-containerRestriction']");
    public static final By CURSOR_STYLE_TAB = By.xpath("//a[@id='draggableExample-tab-cursorStyle']");

    // Simple
    public static final By DRAG_BOX_SIMPLE = By.xpath("//div[@id='dragBox']");

    // Axis Restricted
    public static final By DRAG_BOX_X = By.xpath("//div[@id='restrictedX']");
    public static final By DRAG_BOX_Y = By.xpath("//div[@id='restrictedY']");

    // Container Restricted
    public static final By DRAG_BOX_CONTAINER = By.xpath("//div[@id='containmentWrapper']//div[contains(@class,'draggable')]");
    public static final By DRAG_BOX_PARENT = By.xpath("//div[@class='draggable ui-widget-content m-3']//span");

    // Cursor Style
    public static final By DRAG_BOX_CURSOR_CENTER = By.xpath("//div[@id='cursorCenter']");
    public static final By DRAG_BOX_CURSOR_TOP_LEFT = By.xpath("//div[@id='cursorTopLeft']");
    public static final By DRAG_BOX_CURSOR_BOTTOM = By.xpath("//div[@id='cursorBottom']//span");
}
