package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;
import static locators.DatePickerLocators.*;

public class DatePickerPage {

    @Step("Выбор даты через ввод текста в первом DatePicker")
    public void setDateByTextInFirstPicker(String date) {
        $(FIRST_DATE_PICKER_INPUT).scrollIntoView(true).clear();
        $(FIRST_DATE_PICKER_INPUT).setValue(date);
    }

    @Step("Выбор даты через селекторы в первом DatePicker")
    public void selectDateInFirstPicker(String month, String year) {
        $(FIRST_DATE_PICKER_INPUT).scrollIntoView(true).click();
        $(MONTH_DROPDOWN).shouldBe(Condition.visible).selectOption(month);
        $(YEAR_DROPDOWN).shouldBe(Condition.visible).selectOption(year);
    }

    @Step("Выбор даты через ввод текста во втором DatePicker")
    public void setDateByTextInSecondPicker(String date) {
        // Если второй календарь открыт, закрываем его перед началом работы
        if ($(SECOND_DATE_AND_TIME_PICKER_INPUT).is(Condition.visible)) {
            $(SECOND_DATE_AND_TIME_PICKER_INPUT).clear();
            $(SECOND_DATE_AND_TIME_PICKER_INPUT).setValue(date);
        }
    }

    @Step("Выбор даты через селекторы во втором DatePicker")
    public void selectDateInSecondPicker(String month, String year) {
        // Скроллим к элементу, чтобы избежать перекрытий
        $(SECOND_DATE_AND_TIME_PICKER_INPUT).scrollIntoView(true).click();

        // Проверяем, что выпадающие меню не перекрывают элемент
        if ($(MONTH_DROPDOWN).is(Condition.visible)) {
            $(MONTH_DROPDOWN).shouldBe(Condition.visible).selectOption(month);
        }
        if ($(YEAR_DROPDOWN).is(Condition.visible)) {
            $(YEAR_DROPDOWN).shouldBe(Condition.visible).selectOption(year);
        }
    }

    @Step("Выбор времени во втором DatePicker")
    public void selectTimeInSecondPicker(String time) {
        // Скроллим к элементу, чтобы избежать перекрытий
        $(SECOND_DATE_AND_TIME_PICKER_INPUT).scrollIntoView(true).click();

        // Проверяем, что время доступно для выбора
        $$(TIME_LIST).findBy(Condition.text(time)).scrollIntoView(true).click();
    }

    @Step("Уменьшить дату и время на 1 месяц во втором DatePicker")
    public void decreaseDateAndTimeInSecondPicker() {
        $(SECOND_DATE_AND_TIME_PICKER_INPUT).scrollIntoView(true).click();
        $(PREVIOUS_MONTH_BUTTON).click();
    }

    @Step("Увеличить дату на 1 месяц во втором DatePicker")
    public void increaseDateByOneMonthInSecondPicker() {
        $(SECOND_DATE_AND_TIME_PICKER_INPUT).scrollIntoView(true).click();
        $(NEXT_MONTH_BUTTON).click();
    }
}
