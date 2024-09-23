package pages;

import static com.codeborne.selenide.Selenide.$;
import static locators.AccordianLocators.*;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class AccordianPage {

    @Step("Кликнуть на заголовок раздела 1")
    public void clickSection1Heading() {
        $(SECTION1_HEADING).click();
    }

    @Step("Проверить, что содержимое раздела 1 видно")
    public void verifySection1ContentVisible() {
        $(SECTION1_CONTENT).shouldBe(Condition.visible);
    }

    @Step("Проверить, что содержимое раздела 1 скрыто")
    public void verifySection1ContentHidden() {
        $(SECTION1_CONTENT).shouldNotBe(Condition.visible);
    }

    @Step("Кликнуть на заголовок раздела 2")
    public void clickSection2Heading() {
        $(SECTION2_HEADING).click();
    }

    @Step("Проверить, что содержимое раздела 2 видно")
    public void verifySection2ContentVisible() {
        $(SECTION2_CONTENT).shouldBe(Condition.visible);
    }

    @Step("Проверить, что содержимое раздела 2 скрыто")
    public void verifySection2ContentHidden() {
        $(SECTION2_CONTENT).shouldNotBe(Condition.visible);
    }

    @Step("Кликнуть на заголовок раздела 3")
    public void clickSection3Heading() {
        $(SECTION3_HEADING).click();
    }

    @Step("Проверить, что содержимое раздела 3 видно")
    public void verifySection3ContentVisible() {
        $(SECTION3_CONTENT).shouldBe(Condition.visible);
    }

    @Step("Проверить, что содержимое раздела 3 скрыто")
    public void verifySection3ContentHidden() {
        $(SECTION3_CONTENT).shouldNotBe(Condition.visible);
    }
}
