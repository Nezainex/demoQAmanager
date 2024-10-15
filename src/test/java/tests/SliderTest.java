package tests;

import org.testng.annotations.*;
import pages.SliderPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class SliderTest extends BaseTest {

    private final SliderPage sliderPage = new SliderPage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Slider и настройка браузера", () -> {
            open("https://demoqa.com/slider");
            getWebDriver().manage().window().maximize();
        });
    }

    @Test(description = "Тестирование перемещения слайдера до значения 50", retryAnalyzer = RetryAnalyzer.class)
    public void testMoveSliderToValue() {
        Allure.step("Тестирование перемещения слайдера до значения 50", () -> {
            sliderPage.moveSliderToValue(50);  // Перемещаем слайдер до значения 50
        });
    }
}
