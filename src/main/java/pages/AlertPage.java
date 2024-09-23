package pages;

import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.*;
import static locators.AlertLocators.*;  // Подключаем локаторы

public class AlertPage {

    public void clickAlertButton() {
        $(ALERT_BUTTON).click();  // Используем локатор через XPath
        Selenide.switchTo().alert().accept();
    }

    public void clickTimerAlertButton() {
        $(TIMER_ALERT_BUTTON).click();
        Selenide.sleep(6000); // Ждем 6 секунд
        Selenide.switchTo().alert().accept();
    }

    public void clickConfirmButton(boolean accept) {
        $(CONFIRM_BUTTON).click();
        if (accept) {
            Selenide.switchTo().alert().accept();
        } else {
            Selenide.switchTo().alert().dismiss();
        }
    }

    public void clickPromptButton(String inputText) {
        $(PROMPT_BUTTON).click();
        Selenide.switchTo().alert().sendKeys(inputText);
        Selenide.switchTo().alert().accept();
    }
}