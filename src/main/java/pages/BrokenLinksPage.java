package pages;

import static com.codeborne.selenide.Selenide.$;
import static locators.BrokenLinksLocators.*;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;

public class BrokenLinksPage {

    @Step("Проверка наличия валидного изображения")
    public boolean isValidImageDisplayed() {
        return $(VALID_IMAGE).shouldBe(visible).isDisplayed();
    }

    @Step("Проверка наличия сломанного изображения")
    public boolean isBrokenImageDisplayed() {
        return $(BROKEN_IMAGE).shouldBe(visible).isDisplayed();
    }

    @Step("Кликнуть на валидную ссылку и проверить переход")
    public void clickValidLink() {
        $(VALID_LINK).shouldBe(visible, enabled).click();
        // Добавляем проверку перехода на корректную страницу
        $("body").shouldHave(text("ToolsQA"));
    }

    @Step("Кликнуть на сломанную ссылку и проверить код ошибки")
    public void clickBrokenLink() {
        $(BROKEN_LINK).shouldBe(visible, enabled).click();  // Кликаем по сломанной ссылке
        $("body").shouldHave(text("500"));   // Проверяем, что на новой странице отображается код ошибки 500
    }
}
