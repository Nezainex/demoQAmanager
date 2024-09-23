package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static locators.TabsLocators.*;
import com.codeborne.selenide.Condition;

import java.util.Objects;

public class TabsPage {

    @Step("Нажать на вкладку 'What'")
    public void clickTabWhat() {
        $(TAB_WHAT).click();
    }

    @Step("Нажать на вкладку 'Origin'")
    public void clickTabOrigin() {
        $(TAB_ORIGIN).click();
    }

    @Step("Нажать на вкладку 'Use'")
    public void clickTabUse() {
        $(TAB_USE).click();
    }

    @Step("Проверить, что вкладка 'What' активна")
    public boolean isWhatTabActive() {
        return Objects.equals($(TAB_WHAT).getAttribute("aria-selected"), "true");
    }

    @Step("Проверить, что вкладка 'Origin' активна")
    public boolean isOriginTabActive() {
        return Objects.equals($(TAB_ORIGIN).getAttribute("aria-selected"), "true");
    }

    @Step("Проверить, что вкладка 'Use' активна")
    public boolean isUseTabActive() {
        return Objects.equals($(TAB_USE).getAttribute("aria-selected"), "true");
    }

    @Step("Проверить, что панель вкладки 'What' видна")
    public boolean isWhatPanelVisible() {
        return $(TAB_PANEL_WHAT).isDisplayed();
    }

    @Step("Проверить, что панель вкладки 'Origin' видна")
    public boolean isOriginPanelVisible() {
        return $(TAB_PANEL_ORIGIN).isDisplayed();
    }

    @Step("Проверить, что панель вкладки 'Use' видна")
    public boolean isUsePanelVisible() {
        return $(TAB_PANEL_USE).isDisplayed();
    }

    @Step("Проверить, что вкладка 'More' недоступна")
    public boolean isTabMoreDisabled() {
        // Проверяем, что у вкладки 'More' есть атрибут aria-disabled="true"
        return $(TAB_MORE).shouldHave(Condition.attribute("aria-disabled", "true")).exists();
    }
}
