package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CheckBoxPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class CheckBoxTest extends BaseTest {

    private final CheckBoxPage checkBoxPage = new CheckBoxPage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы CheckBox", () -> open("https://demoqa.com/checkbox"));
        Allure.step("Максимизация окна браузера", () -> getWebDriver().manage().window().maximize());
    }

    @Test(description = "Тестирование expand/collapse", retryAnalyzer = RetryAnalyzer.class)
    public void testExpandAndCollapse() {
        Allure.step("Раскрытие всех чекбоксов", checkBoxPage::expandAll);
        Allure.step("Сворачивание всех чекбоксов", checkBoxPage::collapseAll);
    }

    @Test(description = "Тестирование выбора первого чекбокса", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectCheckbox() {
        Allure.step("Раскрытие всех чекбоксов", checkBoxPage::expandAll);
        Allure.step("Выбор первого чекбокса", () -> checkBoxPage.selectCheckbox(0));
        Allure.step("Проверка результата", () -> {
            String result = checkBoxPage.getResultText();
            assert result.contains("home");
        });
    }
}
