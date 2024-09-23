package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static locators.SliderLocators.*;

public class SliderPage {

    @Step("Переместить слайдер до значения: {value}")
    public void moveSliderToValue(int value) {
        $(SLIDER).setValue(String.valueOf(value));  // Устанавливаем значение слайдера
        $(SLIDER_VALUE).shouldHave(Condition.value(String.valueOf(value)));  // Проверяем, что значение отображается правильно
    }
}
