package pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;
import static locators.PracticeFormLocators.*;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.File;
import java.time.Duration;

public class PracticeFormPage {

    @Step("Заполнить имя: {firstName}")
    public void fillFirstName(String firstName) {
        $(FIRST_NAME).setValue(firstName);
    }

    @Step("Заполнить фамилию: {lastName}")
    public void fillLastName(String lastName) {
        $(LAST_NAME).setValue(lastName);
    }

    @Step("Заполнить email: {email}")
    public void fillEmail(String email) {
        $(EMAIL).setValue(email);
    }

    @Step("Выбрать пол")
    public void selectGender() {
        $(GENDER).click();
    }

    @Step("Заполнить мобильный номер: {mobile}")
    public void fillMobile(String mobile) {
        $(MOBILE).setValue(mobile);
    }

    @Step("Заполнить дату рождения через селекторы: {day}, {month}, {year}")
    public void selectDateOfBirth(String day, String month, String year) {
        // Открыть календарь через JavaScript
        Selenide.executeJavaScript("arguments[0].click();", $(DATE_OF_BIRTH));

        // Выбор месяца через селектор
        $(MONTH_DROPDOWN).shouldBe(Condition.visible).selectOption(month);

        // Выбор года через селектор
        $(YEAR_DROPDOWN).shouldBe(Condition.visible).selectOption(year);

        // Клик по дню с использованием JavaScript
        Selenide.executeJavaScript("arguments[0].click();", $(By.xpath(String.format(DAY_OPTION_TEMPLATE, day))));
    }

    @Step("Выбрать предмет: {subject}")
    public void fillSubject(String subject) {
        $(SUBJECT).setValue(subject).pressEnter();
    }

    @Step("Выбрать хобби")
    public void selectHobbies(String... hobbies) {
        for (String hobby : hobbies) {
            switch (hobby) {
                case "Sports":
                    Selenide.executeJavaScript("arguments[0].click();", $(HOBBIES_LABEL_1));  // Кликаем по метке через JavaScript
                    break;
                case "Reading":
                    Selenide.executeJavaScript("arguments[0].click();", $(HOBBIES_LABEL_2));  // Кликаем по метке через JavaScript
                    break;
                case "Music":
                    Selenide.executeJavaScript("arguments[0].click();", $(HOBBIES_LABEL_3));  // Кликаем по метке через JavaScript
                    break;
            }
        }
    }

    @Step("Загрузить файл")
    public void uploadFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не найден: " + filePath);
        }
        $(FILE_INPUT).uploadFile(file);
    }

    @Step("Заполнить текущий адрес: {address}")
    public void fillCurrentAddress(String address) {
        $(CURRENT_ADDRESS).setValue(address);
    }

    @Step("Заполнить штат и город") //Вместе выбор штата и города работают, а раздельно я не смог сделать.
    public void selectStateAndCity(String state, String city) {
        // Попробуем удалить модальное окно, если оно есть
        Selenide.executeJavaScript("if (document.querySelector('.modal') !== null) { document.querySelector('.modal').remove(); }");

        // Открыть выпадающий список для выбора штата
        $(STATE_DROPDOWN).scrollIntoView(true).click();

        // Ожидание появления элемента с нужным штатом и его выбор
        $(By.xpath(String.format(STATE_OPTION_TEMPLATE, state)))
                .shouldBe(Condition.visible, Duration.ofSeconds(10))  // Ждем появления элемента в течение 10 секунд
                .click();

        // Открыть выпадающий список для выбора города
        $(CITY_DROPDOWN).scrollIntoView(true).click();

        // Ожидание появления элемента с нужным городом и его выбор
        $(By.xpath(String.format(CITY_OPTION_TEMPLATE, city)))
                .shouldBe(Condition.visible, Duration.ofSeconds(10))  // Ждем появления элемента в течение 10 секунд
                .click();
    }

/*  @Step("Заполнить штат")
    public void selectState(String state) {
        // Попробуем удалить модальное окно, если оно есть
        Selenide.executeJavaScript("if (document.querySelector('.modal') !== null) { document.querySelector('.modal').remove(); }");

        // Открыть выпадающий список для выбора штата
        $(STATE_DROPDOWN).scrollIntoView(true).click();

        // Ожидание появления элемента с нужным штатом и его выбор
        $(By.xpath(String.format(STATE_OPTION_TEMPLATE, state)))
                .shouldBe(Condition.visible, Duration.ofSeconds(10))  // Ждем появления элемента в течение 10 секунд
                .click();
    }

    @Step("Заполнить город")
    public void selectCity(String city) {
        // Попробуем удалить модальное окно, если оно есть
        Selenide.executeJavaScript("if (document.querySelector('.modal') !== null) { document.querySelector('.modal').remove(); }");

        // Открыть выпадающий список для выбора города
        $(CITY_DROPDOWN).scrollIntoView(true).click();

        // Ожидание появления элемента с нужным городом и его выбор
        $(By.xpath(String.format(CITY_OPTION_TEMPLATE, city)))
                .shouldBe(Condition.visible, Duration.ofSeconds(10))  // Ждем появления элемента в течение 10 секунд
                .click();
    }
*/
    @Step("Отправить форму")
    public void submitForm() {
        $(SUBMIT).scrollIntoView(true).click();
    }
}
