package locators;

import org.openqa.selenium.By;

public class PracticeFormLocators {
    public static final By FIRST_NAME = By.xpath("//input[@id='firstName']");
    public static final By LAST_NAME = By.xpath("//input[@id='lastName']");
    public static final By EMAIL = By.xpath("//input[@id='userEmail']");
    public static final By GENDER = By.xpath("//input[@id='gender-radio-1']/following-sibling::label");
    public static final By MOBILE = By.xpath("//input[@id='userNumber']");

    public static final By DATE_OF_BIRTH = By.xpath("//input[@id='dateOfBirthInput']");
    public static final By YEAR_DROPDOWN = By.xpath("//select[contains(@class, 'react-datepicker__year-select')]");
    public static final By MONTH_DROPDOWN = By.xpath("//select[contains(@class, 'react-datepicker__month-select')]");
    public static final String DAY_OPTION_TEMPLATE = "//div[contains(@class, 'react-datepicker__day') and text()='%s']";

    public static final By SUBJECT = By.xpath("//input[@id='subjectsInput']");

    public static final By HOBBIES_LABEL_1 = By.xpath("//input[@id='hobbies-checkbox-1']/following-sibling::label");
    public static final By HOBBIES_LABEL_2 = By.xpath("//input[@id='hobbies-checkbox-2']/following-sibling::label");
    public static final By HOBBIES_LABEL_3 = By.xpath("//input[@id='hobbies-checkbox-3']/following-sibling::label");

    public static final By FILE_INPUT = By.xpath("//input[@id='uploadPicture']");
    public static final By CURRENT_ADDRESS = By.xpath("//textarea[@id='currentAddress']");

    // Локаторы для выпадающего списка штатов и городов
    public static final By STATE_DROPDOWN = By.xpath("//div[@id='state']");
    public static final By CITY_DROPDOWN = By.xpath("//div[@id='city']");

    // Опции для выбора штата и города - динамически подставляем значение через форматирование
    public static final String STATE_OPTION_TEMPLATE = "//div[contains(text(), '%s')]";
    public static final String CITY_OPTION_TEMPLATE = "//div[contains(text(), '%s')]";

    public static final By SUBMIT = By.xpath("//button[@id='submit']");
}
