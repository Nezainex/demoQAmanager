package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import locators.ModalDialogLocators;

public class ModalDialogPage {

    private static final String SMALL_MODAL_EXPECTED_TEXT = "This is a small modal. It has very less content";
    private static final String LARGE_MODAL_EXPECTED_TEXT = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

    // Открытие Small Modal
    public void openSmallModal() {
        $(ModalDialogLocators.SMALL_MODAL_BUTTON).shouldBe(visible).click();
        // Проверяем, что Small Modal открылся
        $x("//div[@id='example-modal-sizes-title-sm']").shouldBe(visible);
        // Проверяем, что появился элемент modal-backdrop
        $(".modal-backdrop").shouldBe(visible);
    }

    // Закрытие Small Modal через кнопку "Close"
    public void closeSmallModalWithButton() {
        $(ModalDialogLocators.CLOSE_SMALL_MODAL_BUTTON).shouldBe(visible).click();
        // Проверяем, что Small Modal закрылся
        $x("//div[@id='example-modal-sizes-title-sm']").shouldBe(hidden);
        // Проверяем, что элемент modal-backdrop исчез
        $(".modal-backdrop").shouldBe(hidden);
    }

    // Проверка текста в Small Modal
    public void verifySmallModalText() {
        $x("//div[@class='modal-body']").shouldHave(text(SMALL_MODAL_EXPECTED_TEXT));
    }

    // Открытие Large Modal
    public void openLargeModal() {
        $(ModalDialogLocators.LARGE_MODAL_BUTTON).shouldBe(visible).click();
        // Проверяем, что Large Modal открылся
        $x("//div[@id='example-modal-sizes-title-lg']").shouldBe(visible);
        // Проверяем, что появился элемент modal-backdrop
        $(".modal-backdrop").shouldBe(visible);
    }

    // Закрытие Large Modal через кнопку "Close"
    public void closeLargeModalWithButton() {
        $(ModalDialogLocators.CLOSE_LARGE_MODAL_BUTTON).shouldBe(visible).click();
        // Проверяем, что Large Modal закрылся
        $x("//div[@id='example-modal-sizes-title-lg']").shouldBe(hidden);
        // Проверяем, что элемент modal-backdrop исчез
        $(".modal-backdrop").shouldBe(hidden);
    }

    // Проверка текста в Large Modal
    public void verifyLargeModalText() {
        $x("//div[@class='modal-body']").shouldHave(text(LARGE_MODAL_EXPECTED_TEXT));
    }
}
