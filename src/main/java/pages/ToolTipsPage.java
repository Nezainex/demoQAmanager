package pages;

import com.codeborne.selenide.SelenideElement;
import locators.ToolTipsLocators;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class ToolTipsPage {

    public SelenideElement getToolTipButton() {
        return $(ToolTipsLocators.TOOLTIP_BUTTON);
    }

    public SelenideElement getToolTipTextField() {
        return $(ToolTipsLocators.TOOLTIP_TEXT_FIELD);
    }

    public SelenideElement getContraryLink() {
        return $(ToolTipsLocators.CONTRARY_LINK);
    }

    public SelenideElement getSectionLink() {
        return $(ToolTipsLocators.SECTION_LINK);
    }

    public void hoverOnButton() {
        actions().moveToElement(getToolTipButton()).perform();
    }

    public void hoverOnTextField() {
        actions().moveToElement(getToolTipTextField()).perform();
    }

    public void hoverOnContraryLink() {
        actions().moveToElement(getContraryLink()).perform();
    }

    public void hoverOnSectionLink() {
        actions().moveToElement(getSectionLink()).perform();
    }
}
