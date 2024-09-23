package pages;

import com.codeborne.selenide.SelenideElement;
import locators.MenuLocators;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class MenuPage {

    public SelenideElement getMainItem1() {
        return $(MenuLocators.MAIN_ITEM_1);
    }

    public SelenideElement getMainItem2() {
        return $(MenuLocators.MAIN_ITEM_2);
    }

    public SelenideElement getMainItem3() {
        return $(MenuLocators.MAIN_ITEM_3);
    }

    public SelenideElement getSubItem1() {
        return $(MenuLocators.SUB_ITEM_1);
    }

    public SelenideElement getSubSubList() {
        return $(MenuLocators.SUB_SUB_LIST);
    }

    public SelenideElement getSubSubItem1() {
        return $(MenuLocators.SUB_SUB_ITEM_1);
    }

    public SelenideElement getSubSubItem2() {
        return $(MenuLocators.SUB_SUB_ITEM_2);
    }

    public void hoverMainItem2() {
        actions().moveToElement(getMainItem2()).perform();
    }

    public void hoverSubSubList() {
        actions().moveToElement(getSubSubList()).perform();
    }
}
