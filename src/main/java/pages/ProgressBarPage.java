package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static locators.ProgressBarLocators.*;

public class ProgressBarPage {

    @Step("Запуск/остановка прогресс-бара")
    public void clickStartStopButton() {
        $(START_STOP_BUTTON).shouldBe(Condition.visible).click();
    }

    @Step("Сброс прогресс-бара")
    public void clickResetButton() {
        $(RESET_BUTTON).shouldBe(Condition.visible).click();
    }

    @Step("Ожидание завершения прогресса до 100%")
    public void waitForProgressBarToComplete() {
        waitForProgressBarToReach(100);
        $(RESET_BUTTON).shouldBe(Condition.visible);  // Убедимся, что кнопка "Reset" видна
    }

    @Step("Ожидание сброса прогресса до 0%")
    public void waitForProgressBarToReset() {
        $(PROGRESS_BAR).shouldHave(Condition.attribute("aria-valuenow", "0"));
        $(START_STOP_BUTTON).shouldHave(Condition.text("Start"));  // Убедимся, что кнопка "Start" появилась
    }

    @Step("Ожидание изменения прогресс-бара до {value}%")
    public void waitForProgressBarToReach(int value) {
        int currentValue;
        do {
            currentValue = Integer.parseInt(Objects.requireNonNull($(PROGRESS_BAR).getAttribute("aria-valuenow")));
            Selenide.sleep(100);  // Небольшая задержка для обновления состояния прогресс-бара
        } while (currentValue < value);
    }
}
