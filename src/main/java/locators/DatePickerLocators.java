package locators;

import org.openqa.selenium.By;

public class DatePickerLocators {
    // Первый DatePicker (только дата)
    public static final By FIRST_DATE_PICKER_INPUT = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[1]/div[2]/div/div/input");

    // Второй DatePicker (дата и время)
    public static final By SECOND_DATE_AND_TIME_PICKER_INPUT = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/div[2]/div/div/input");

    // Общие кнопки
    public static final By PREVIOUS_MONTH_BUTTON = By.xpath("//button[contains(@class,'react-datepicker__navigation--previous')]");
    public static final By NEXT_MONTH_BUTTON = By.xpath("//button[contains(@class,'react-datepicker__navigation--next')]");
    public static final By MONTH_DROPDOWN = By.xpath("//select[contains(@class,'react-datepicker__month-select')]");
    public static final By YEAR_DROPDOWN = By.xpath("//select[contains(@class,'react-datepicker__year-select')]");
    public static final By TIME_LIST = By.xpath("//li[contains(@class, 'react-datepicker__time-list-item')]");
}
