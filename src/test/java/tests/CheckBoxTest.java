package tests;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CheckBoxPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class CheckBoxTest extends BaseTest {

    private final CheckBoxPage checkBoxPage = new CheckBoxPage();

    @BeforeMethod
    @Step("Открытие страницы CheckBox и настройка окна")
    public void setUp() {
        open("https://demoqa.com/checkbox");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Тестирование expand/collapse", retryAnalyzer = RetryAnalyzer.class)
    @Step("Раскрытие и сворачивание всех чекбоксов")
    public void testExpandAndCollapse() {
        checkBoxPage.expandAll();
        checkBoxPage.collapseAll();
    }

    @Test(description = "Тестирование выбора первого чекбокса", retryAnalyzer = RetryAnalyzer.class)
    @Step("Выбор первого чекбокса")
    public void testSelectCheckbox() {
        checkBoxPage.expandAll();
        checkBoxPage.selectCheckbox(0);
        String result = checkBoxPage.getResultText();
        assert result.contains("home");
    }
}
