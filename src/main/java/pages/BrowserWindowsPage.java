package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static locators.BrowserWindowsLocators.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Set;
import java.util.Objects;

public class BrowserWindowsPage {

    @Step("Открыть новую вкладку и проверить контент")
    public void openNewTab() {
        $(NEW_TAB_BUTTON).click();
        switchToNewWindow();
        $(By.tagName("h1")).shouldHave(Condition.text("This is a sample page"));
        Selenide.closeWindow();  // Закрываем вкладку
        switchTo().window(0);  // Возвращаемся на исходную вкладку
    }

    @Step("Открыть новое окно и проверить контент")
    public void openNewWindow() {
        $(NEW_WINDOW_BUTTON).click();
        switchToNewWindow();
        $(By.tagName("h1")).shouldHave(Condition.text("This is a sample page"));
        Selenide.closeWindow();  // Закрываем окно
        switchTo().window(0);  // Возвращаемся на исходное окно
    }

    @Step("Открыть окно с сообщением и проверить текст")
    public void openNewWindowMessage() {
        $(NEW_WINDOW_MESSAGE_BUTTON).click();
        switchToNewWindow();
        String message = Selenide.executeJavaScript("return document.body.textContent;");
        assert Objects.requireNonNull(message).contains("Knowledge increases by sharing but not by saving.");
        Selenide.closeWindow();  // Закрываем окно с сообщением
        switchTo().window(0);  // Возвращаемся на исходное окно
    }

    // Метод для переключения на новое окно или вкладку
    private void switchToNewWindow() {
        String currentWindow = Selenide.webdriver().object().getWindowHandle();
        Set<String> windows = Selenide.webdriver().object().getWindowHandles();
        for (String window : windows) {
            if (!window.equals(currentWindow)) {
                switchTo().window(window);
                break;
            }
        }
    }
}
