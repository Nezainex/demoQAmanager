package pages;

import static com.codeborne.selenide.Selenide.$;
import static locators.WebTableLocators.*;
import io.qameta.allure.Step;

public class WebTablePage {

    @Step("Добавить новую запись")
    public void addEntry(String firstName, String lastName, String email, String age, String salary, String department) {
        $(ADD_NEW_RECORD_BUTTON).click();
        $(FIRST_NAME).setValue(firstName);
        $(LAST_NAME).setValue(lastName);
        $(EMAIL).setValue(email);
        $(AGE).setValue(age);
        $(SALARY).setValue(salary);
        $(DEPARTMENT).setValue(department);
        $(SUBMIT_BUTTON).click();
    }

    @Step("Редактировать запись")
    public void editEntry(String salary, String department) {
        $(EDIT_RECORD_BUTTON).click();
        $(SALARY).clear();
        $(SALARY).setValue(salary);
        $(DEPARTMENT).clear();
        $(DEPARTMENT).setValue(department);
        $(SUBMIT_BUTTON).click();
    }

    @Step("Удалить запись")
    public void deleteEntry() {
        $(DELETE_RECORD_BUTTON).click();
    }
}
