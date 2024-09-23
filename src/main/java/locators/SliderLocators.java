package locators;

import org.openqa.selenium.By;

public class SliderLocators {
    // Обновляем локаторы на XPath
    public static final By SLIDER = By.xpath("//input[@class='range-slider range-slider--primary']");
    public static final By SLIDER_VALUE = By.xpath("//input[@id='sliderValue']");
}
