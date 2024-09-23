package pages;

import static com.codeborne.selenide.Selenide.$;
import static locators.DynamicPropertiesLocators.*;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;

public class DynamicPropertiesPage {

    @Step("Проверить, что кнопка 'Will enable 5 seconds' активна через 5 секунд")
    public void waitForEnableButton() {
        $(ENABLE_BUTTON).shouldBe(Condition.enabled, Duration.ofSeconds(6));
    }

    @Step("Проверить, что кнопка 'Color Change' изменила цвет")
    public void verifyColorChange() {
        $(COLOR_CHANGE_BUTTON).shouldHave(Condition.cssClass("text-danger"), Duration.ofSeconds(6));
    }

    @Step("Проверить, что кнопка 'Visible After 5 Seconds' стала видимой через 5 секунд")
    public void waitForVisibleButton() {
        $(VISIBLE_AFTER_BUTTON).shouldBe(Condition.visible, Duration.ofSeconds(6));
    }

    @Step("Проверить наличие текста с рандомным id")
    public void verifyRandomText() {
        $(RANDOM_TEXT).shouldBe(Condition.visible);
    }
}
