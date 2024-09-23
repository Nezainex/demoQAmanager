package pages;

import com.codeborne.selenide.SelenideElement;
import locators.DraggableLocators;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class DraggablePage {

    // Tabs
    public SelenideElement getSimpleTab() {
        return $(DraggableLocators.SIMPLE_TAB);
    }

    public SelenideElement getAxisRestrictedTab() {
        return $(DraggableLocators.AXIS_RESTRICTED_TAB);
    }

    public SelenideElement getContainerRestrictedTab() {
        return $(DraggableLocators.CONTAINER_RESTRICTED_TAB);
    }

    public SelenideElement getCursorStyleTab() {
        return $(DraggableLocators.CURSOR_STYLE_TAB);
    }

    // Simple
    public SelenideElement getDragBoxSimple() {
        return $(DraggableLocators.DRAG_BOX_SIMPLE);
    }

    // Axis Restricted
    public SelenideElement getDragBoxX() {
        return $(DraggableLocators.DRAG_BOX_X);
    }

    public SelenideElement getDragBoxY() {
        return $(DraggableLocators.DRAG_BOX_Y);
    }

    // Container Restricted
    public SelenideElement getDragBoxContainer() {
        return $(DraggableLocators.DRAG_BOX_CONTAINER);
    }

    public SelenideElement getDragBoxParent() {
        return $(DraggableLocators.DRAG_BOX_PARENT);
    }

    // Cursor Style
    public SelenideElement getDragBoxCursorCenter() {
        return $(DraggableLocators.DRAG_BOX_CURSOR_CENTER);
    }

    public SelenideElement getDragBoxCursorTopLeft() {
        return $(DraggableLocators.DRAG_BOX_CURSOR_TOP_LEFT);
    }

    public SelenideElement getDragBoxCursorBottom() {
        return $(DraggableLocators.DRAG_BOX_CURSOR_BOTTOM);
    }

    public void dragAndDrop(SelenideElement element, int xOffset, int yOffset) {
        actions().dragAndDropBy(element, xOffset, yOffset).perform();
    }
}
