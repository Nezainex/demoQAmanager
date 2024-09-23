package locators;

import org.openqa.selenium.By;

public class AccordianLocators {
    // Заголовки секций с использованием текста
    public static final By SECTION1_HEADING = By.xpath("//div[@class='card-header' and text()='What is Lorem Ipsum?']");
    public static final By SECTION2_HEADING = By.xpath("//div[@class='card-header' and text()='Where does it come from?']");
    public static final By SECTION3_HEADING = By.xpath("//div[@class='card-header' and text()='Why do we use it?']");

    // Контент секций с использованием их относительного расположения
    public static final By SECTION1_CONTENT = By.xpath("//div[@id='section1Content']");
    public static final By SECTION2_CONTENT = By.xpath("//div[@id='section2Content']");
    public static final By SECTION3_CONTENT = By.xpath("//div[@id='section3Content']");
}
