package tests;

import org.testng.annotations.*;
import pages.ToolTipsPage;
import utils.RetryAnalyzer;
import utils.TestListener;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class ToolTipsTest extends BaseTest {

    ToolTipsPage toolTipsPage = new ToolTipsPage();

    @BeforeMethod
    @Step("Открытие страницы Tool Tips")
    public void setUp() {
        open("https://demoqa.com/tool-tips");
        getWebDriver().manage().window().maximize();
    }

    @Test(description = "Hover over the 'Hover me to see' button")
    @Step("Наведение на кнопку 'Hover me to see'")
    public void hoverOverButton() {
        hoverOnButton();
        // Добавьте проверку появления всплывающей подсказки
    }

    @Step("Наведение на кнопку 'Hover me to see'")
    private void hoverOnButton() {
        toolTipsPage.hoverOnButton();
    }

    @Test(description = "Hover over the text field", retryAnalyzer = RetryAnalyzer.class)
    @Step("Наведение на текстовое поле")
    public void hoverOverTextField() {
        hoverOnTextField();
        // Добавьте проверку появления всплывающей подсказки
    }

    @Step("Наведение на текстовое поле")
    private void hoverOnTextField() {
        toolTipsPage.hoverOnTextField();
    }

    @Test(description = "Hover over the 'Contrary' link", retryAnalyzer = RetryAnalyzer.class)
    @Step("Наведение на ссылку 'Contrary'")
    public void hoverOverContraryLink() {
        hoverOnContraryLink();
        // Добавьте проверку появления всплывающей подсказки
    }

    @Step("Наведение на ссылку 'Contrary'")
    private void hoverOnContraryLink() {
        toolTipsPage.hoverOnContraryLink();
    }

    @Test(description = "Hover over the '1.10.32' link", retryAnalyzer = RetryAnalyzer.class)
    @Step("Наведение на ссылку '1.10.32'")
    public void hoverOverSectionLink() {
        hoverOnSectionLink();
        // Добавьте проверку появления всплывающей подсказки
    }

    @Step("Наведение на ссылку '1.10.32'")
    private void hoverOnSectionLink() {
        toolTipsPage.hoverOnSectionLink();
    }
}
