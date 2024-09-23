package pages;

import com.codeborne.selenide.SelenideElement;
import locators.DroppableLocators;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DroppablePage {

    // Методы для переключения табов
    public void switchToSimpleTab() {
        $(DroppableLocators.SIMPLE_TAB).scrollIntoView(true).click();
    }

    public void switchToAcceptTab() {
        $(DroppableLocators.ACCEPT_TAB).scrollIntoView(true).click();
    }

    public void switchToPreventPropagationTab() {
        $(DroppableLocators.PREVENT_PROPAGATION_TAB).scrollIntoView(true).click();
    }

    public void switchToRevertDraggableTab() {
        $(DroppableLocators.REVERT_DRAGGABLE_TAB).scrollIntoView(true).click();
    }

    // Simple
    public SelenideElement getDragMeSimple() {
        return $(DroppableLocators.DRAG_ME_SIMPLE).shouldBe(visible);
    }

    public SelenideElement getDropHereSimple() {
        return $(DroppableLocators.DROP_HERE_SIMPLE).shouldBe(visible);
    }

    // Accept
    public SelenideElement getAcceptable() {
        return $(DroppableLocators.ACCEPTABLE).shouldBe(visible);
    }

    public SelenideElement getNotAcceptable() {
        return $(DroppableLocators.NOT_ACCEPTABLE).shouldBe(visible);
    }

    public SelenideElement getDropHereAccept() {
        return $(DroppableLocators.DROP_HERE_ACCEPT).shouldBe(visible);
    }

    // Prevent Propagation
    public SelenideElement getDragBox() {
        return $(DroppableLocators.DRAG_BOX).shouldBe(visible);
    }

    public SelenideElement getOuterNotGreedyDropBox() {
        return $(DroppableLocators.OUTER_NOT_GREEDY_DROPBOX).shouldBe(visible);
    }

    public SelenideElement getInnerNotGreedyDropBox() {
        return $(DroppableLocators.INNER_NOT_GREEDY_DROPBOX).shouldBe(visible);
    }

    public SelenideElement getOuterGreedyDropBox() {
        return $(DroppableLocators.OUTER_GREEDY_DROPBOX).shouldBe(visible);
    }

    public SelenideElement getInnerGreedyDropBox() {
        return $(DroppableLocators.INNER_GREEDY_DROPBOX).shouldBe(visible);
    }

    // Revert Draggable
    public SelenideElement getWillRevert() {
        return $(DroppableLocators.WILL_REVERT).shouldBe(visible);
    }

    public SelenideElement getNotRevert() {
        return $(DroppableLocators.NOT_REVERT).shouldBe(visible);
    }

    public SelenideElement getDropHereRevert() {
        return $(DroppableLocators.DROP_HERE_REVERT).shouldBe(visible);
    }

    public void dragAndDrop(SelenideElement source, SelenideElement target) {
        source.scrollIntoView(true);
        target.scrollIntoView(true);
        actions().dragAndDrop(source, target).perform();
        // Пауза для ожидания реакции на дроп
        sleep(1000); // Пауза на 1 секунду
    }
}
