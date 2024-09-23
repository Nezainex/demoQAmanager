package pages;

import static com.codeborne.selenide.Selenide.*;
import static locators.CheckBoxLocators.*;

public class CheckBoxPage {

    public void expandAll() {
        $(EXPAND_ALL_BUTTON).click();
    }

    public void collapseAll() {
        $(COLLAPSE_ALL_BUTTON).click();
    }

    public void selectCheckbox(int index) {
        $$(CHECKBOX_ITEM).get(index).click();
    }

    public String getResultText() {
        return $(RESULT_TEXT).getText();
    }
}
