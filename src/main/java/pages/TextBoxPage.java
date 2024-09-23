package pages;

import static com.codeborne.selenide.Selenide.*;
import static locators.TextBoxLocators.*;

public class TextBoxPage {

    public void fillFullName(String name) {
        $(FULL_NAME).setValue(name);
    }

    public void fillEmail(String email) {
        $(EMAIL).setValue(email);
    }

    public void fillCurrentAddress(String address) {
        $(CURRENT_ADDRESS).setValue(address);
    }

    public void fillPermanentAddress(String address) {
        $(PERMANENT_ADDRESS).setValue(address);
    }

    public void submitForm() {
        $(SUBMIT).click();
    }
}
