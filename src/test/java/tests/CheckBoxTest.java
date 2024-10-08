package tests;

import org.testng.annotations.*;
import pages.CheckBoxPage;
import utils.RetryAnalyzer;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class CheckBoxTest extends BaseTest {

    private final CheckBoxPage checkBoxPage = new CheckBoxPage();

    @BeforeMethod
    public void setUp() {
        open("https://demoqa.com/checkbox");
        getWebDriver().manage().window().maximize();
    }

    @Test (description = "Тестирование testExpandAndCollapse", retryAnalyzer = RetryAnalyzer.class)
    public void testExpandAndCollapse() {
        checkBoxPage.expandAll();
        checkBoxPage.collapseAll();
    }

    @Test (description = "Тестирование выбора первого чекбокса", retryAnalyzer = RetryAnalyzer.class)
    public void testSelectCheckbox() {
        checkBoxPage.expandAll();
        checkBoxPage.selectCheckbox(0); // Выбираем первый чекбокс
        String result = checkBoxPage.getResultText();
        assert result.contains("home");
    }
}
