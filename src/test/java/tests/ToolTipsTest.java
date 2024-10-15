package tests;

import org.testng.annotations.*;
import pages.ToolTipsPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ToolTipsTest extends BaseTest {

    ToolTipsPage toolTipsPage = new ToolTipsPage();

    @BeforeMethod
    public void setUp() {
        Allure.step("Открытие страницы Tool Tips", () -> {
            open("https://demoqa.com/tool-tips");
            getWebDriver().manage().window().maximize();
        });
    }

    @Test(description = "Hover over the 'Hover me to see' button")
    public void hoverOverButton() {
        Allure.step("Наведение на кнопку 'Hover me to see'", () -> {
            toolTipsPage.hoverOnButton();
            // Добавьте проверку появления всплывающей подсказки
        });
    }

    @Test(description = "Hover over the text field", retryAnalyzer = RetryAnalyzer.class)
    public void hoverOverTextField() {
        Allure.step("Наведение на текстовое поле", () -> {
            toolTipsPage.hoverOnTextField();
            // Добавьте проверку появления всплывающей подсказки
        });
    }

    @Test(description = "Hover over the 'Contrary' link", retryAnalyzer = RetryAnalyzer.class)
    public void hoverOverContraryLink() {
        Allure.step("Наведение на ссылку 'Contrary'", () -> {
            toolTipsPage.hoverOnContraryLink();
            // Добавьте проверку появления всплывающей подсказки
        });
    }

    @Test(description = "Hover over the '1.10.32' link", retryAnalyzer = RetryAnalyzer.class)
    public void hoverOverSectionLink() {
        Allure.step("Наведение на ссылку '1.10.32'", () -> {
            toolTipsPage.hoverOnSectionLink();
            // Добавьте проверку появления всплывающей подсказки
        });
    }
}
