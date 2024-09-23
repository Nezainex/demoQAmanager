package pages;

import com.codeborne.selenide.SelenideElement;
import locators.ResizableLocators;
import org.openqa.selenium.Dimension;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class ResizablePage {

    public SelenideElement getResizableBoxWithRestriction() {
        return $(ResizableLocators.RESIZABLE_BOX_WITH_RESTRICTION);
    }

    public SelenideElement getResizableHandleWithRestriction() {
        return $(ResizableLocators.RESIZABLE_HANDLE_WITH_RESTRICTION);
    }

    public SelenideElement getResizableBoxNoRestriction() {
        return $(ResizableLocators.RESIZABLE_BOX_NO_RESTRICTION);
    }

    public SelenideElement getResizableHandleNoRestriction() {
        return $(ResizableLocators.RESIZABLE_HANDLE_NO_RESTRICTION);
    }

    public void resizeElement(SelenideElement handle, int xOffset, int yOffset) {
        actions().clickAndHold(handle).moveByOffset(xOffset, yOffset).release().perform();
    }

    public Dimension getElementSize(SelenideElement element) {
        return element.getSize();
    }
}
