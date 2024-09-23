package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import locators.SortableLocators;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.Selenide.*;

public class SortablePage {

    public SelenideElement getListTab() {
        return $(SortableLocators.LIST_TAB);
    }

    public SelenideElement getGridTab() {
        return $(SortableLocators.GRID_TAB);
    }

    public ElementsCollection getListItems() {
        return $$(SortableLocators.LIST_ITEMS);
    }

    public ElementsCollection getGridItems() {
        return $$(SortableLocators.GRID_ITEMS);
    }

    public void dragAndDropUsingActions(ElementsCollection items, int fromIndex, int toIndex) {
        Actions actions = new Actions(getWebDriver());
        actions.clickAndHold(items.get(fromIndex))
                .pause(1000) // Пауза на 1 секунду
                .moveToElement(items.get(toIndex))
                .release()
                .build()
                .perform();
    }
}
