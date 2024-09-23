package pages;

import com.codeborne.selenide.SelenideElement;
import locators.SelectMenuLocators;

import static com.codeborne.selenide.Selenide.$;

public class SelectMenuPage {

    // Метод для получения элемента Select Value
    public SelenideElement getSelectValue() {
        return $(SelectMenuLocators.SELECT_VALUE);
    }

    // Метод для получения элемента Select One
    public SelenideElement getSelectOne() {
        return $(SelectMenuLocators.SELECT_ONE);
    }

    // Метод для получения Old Style Select Menu
    public SelenideElement getOldStyleSelectMenu() {
        return $(SelectMenuLocators.OLD_STYLE_SELECT_MENU);
    }

    // Метод для получения Multiselect Dropdown
    public SelenideElement getMultiselectDropdown() {
        return $(SelectMenuLocators.MULTISELECT_DROPDOWN);
    }

    // Метод для получения Standard Multi Select
    public SelenideElement getStandardMultiSelect() {
        return $(SelectMenuLocators.STANDARD_MULTI_SELECT);
    }

    // Универсальный метод для выбора опции по тексту
    public void selectOptionByText(SelenideElement element, String option) {
        element.setValue(option).pressEnter();  // Для селектов с вводом текста
    }
}
