package pages;

import static com.codeborne.selenide.Selenide.$;
import static locators.ButtonsLocators.*;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

public class ButtonsPage {

    Actions actions = new Actions(Selenide.webdriver().object());

    @Step("Выполнить двойной клик на кнопке")
    public void doubleClickButton() {
        actions.doubleClick($(DOUBLE_CLICK_BUTTON)).perform();
    }

    @Step("Выполнить правый клик на кнопке")
    public void rightClickButton() {
        actions.contextClick($(RIGHT_CLICK_BUTTON)).perform();
    }

    @Step("Выполнить обычный клик на кнопке")
    public void clickMeButton() {
        $(CLICK_ME_BUTTON).click();
    }
}
