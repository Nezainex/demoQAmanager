package pages;

import static com.codeborne.selenide.Selenide.$;
import static locators.AccordianLocators.*;
import com.codeborne.selenide.Condition;

public class AccordianPage {

    public void clickSection1Heading() {
        $(SECTION1_HEADING).click();
    }

    public void verifySection1ContentVisible() {
        $(SECTION1_CONTENT).shouldBe(Condition.visible);
    }

    public void verifySection1ContentHidden() {
        $(SECTION1_CONTENT).shouldNotBe(Condition.visible);
    }

    public void clickSection2Heading() {
        $(SECTION2_HEADING).click();
    }

    public void verifySection2ContentVisible() {
        $(SECTION2_CONTENT).shouldBe(Condition.visible);
    }

    public void verifySection2ContentHidden() {
        $(SECTION2_CONTENT).shouldNotBe(Condition.visible);
    }

    public void clickSection3Heading() {
        $(SECTION3_HEADING).click();
    }

    public void verifySection3ContentVisible() {
        $(SECTION3_CONTENT).shouldBe(Condition.visible);
    }

    public void verifySection3ContentHidden() {
        $(SECTION3_CONTENT).shouldNotBe(Condition.visible);
    }
}
