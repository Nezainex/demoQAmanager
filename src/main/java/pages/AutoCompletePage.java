package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static locators.AutoCompleteLocators.*;

public class AutoCompletePage {

    @Step("Ввод значений в multiple input: {text}")
    public void fillMultipleInput(String... text) {
        SelenideElement input = $(MULTIPLE_INPUT).shouldBe(Condition.visible);
        for (String value : text) {
            input.setValue(value);
            $(AUTO_COMPLETE_OPTION).click();
        }
    }

    @Step("Ввод значения в single input: {text}")
    public void fillSingleInput(String text) {
        SelenideElement input = $(SINGLE_INPUT).shouldBe(Condition.visible);
        input.setValue(text);
        $(AUTO_COMPLETE_OPTION).click();
    }
}
