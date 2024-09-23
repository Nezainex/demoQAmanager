package pages;

import static com.codeborne.selenide.Selenide.$;
import static locators.LinksLocators.*;
import io.qameta.allure.Step;

public class LinksPage {

    @Step("Кликнуть на простую ссылку")
    public void clickSimpleLink() {
        $(SIMPLE_LINK).click();
    }

    @Step("Кликнуть на динамическую ссылку")
    public void clickDynamicLink() {
        $(DYNAMIC_LINK).click();
    }

    @Step("Кликнуть на ссылку 'Created'")
    public void clickCreatedLink() {
        $(CREATED_LINK).click();
    }

    @Step("Кликнуть на ссылку 'No Content'")
    public void clickNoContentLink() {
        $(NO_CONTENT_LINK).click();
    }

    @Step("Кликнуть на ссылку 'Moved'")
    public void clickMovedLink() {
        $(MOVED_LINK).click();
    }

    @Step("Кликнуть на ссылку 'Bad Request'")
    public void clickBadRequestLink() {
        $(BAD_REQUEST_LINK).click();
    }

    @Step("Кликнуть на ссылку 'Unauthorized'")
    public void clickUnauthorizedLink() {
        $(UNAUTHORIZED_LINK).click();
    }

    @Step("Кликнуть на ссылку 'Forbidden'")
    public void clickForbiddenLink() {
        $(FORBIDDEN_LINK).click();
    }

    @Step("Кликнуть на ссылку 'Not Found'")
    public void clickNotFoundLink() {
        $(NOT_FOUND_LINK).click();
    }
}
