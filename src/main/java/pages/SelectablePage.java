package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import locators.SelectableLocators;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SelectablePage {

    public SelenideElement getListTab() {
        return $(SelectableLocators.LIST_TAB);
    }

    public SelenideElement getGridTab() {
        return $(SelectableLocators.GRID_TAB);
    }

    public ElementsCollection getListItems() {
        return $$(SelectableLocators.LIST_ITEMS);
    }

    public ElementsCollection getGridItems() {
        return $$(SelectableLocators.GRID_ITEMS);
    }

    public void selectItem(ElementsCollection items, int index) {
        items.get(index).click();
    }

    public boolean isItemSelected(ElementsCollection items, int index) {
        return Objects.requireNonNull(items.get(index).getAttribute("class")).contains("active");
    }
}
