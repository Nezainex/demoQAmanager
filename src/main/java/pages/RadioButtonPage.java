package pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;
import static locators.RadioButtonLocators.*;

import io.qameta.allure.Step;

public class RadioButtonPage {

    @Step("Выбрать опцию 'Yes'")
    public void selectYes() {
        // Кликаем по <label> вместо <input>
        $(YES_LABEL).click();
    }

    @Step("Выбрать опцию 'Impressive'")
    public void selectImpressive() {
        // Кликаем по <label> вместо <input>
        $(IMPRESSIVE_LABEL).click();
    }

    @Step("Проверить, что 'No' отключена")
    public boolean isNoDisabled() {
        return $(NO_RADIO).is(Condition.disabled);  // Возвращает состояние кнопки 'No'
    }

    @Step("Получить результат выбранного радио")
    public String getSelectedResult() {
        return $(RADIO_RESULT).shouldBe(Condition.visible).getText();
    }
}
