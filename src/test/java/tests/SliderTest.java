package tests;

import org.testng.annotations.*;
import pages.SliderPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SliderTest extends BaseTest {

    private final SliderPage sliderPage = new SliderPage();

    @BeforeMethod
    @Step("Открытие страницы Slider и настройка браузера")
    public void setUp() {
        open("https://demoqa.com/slider");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Тестирование перемещения слайдера до значения 50", retryAnalyzer = RetryAnalyzer.class)
    @Step("Тестирование перемещения слайдера до значения 50")
    public void testMoveSliderToValue() {
        sliderPage.moveSliderToValue(50);  // Перемещаем слайдер до значения 50
    }
}
