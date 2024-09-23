package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static locators.FrameLocators.*;

public class FramePage {

    public void switchToFrameOne() {
        switchTo().frame($(FRAME_ONE));  // Переход в фрейм 1
        System.out.println($(FRAME_TEXT).getText());  // Вывод текста из фрейма 1
        switchTo().defaultContent();  // Возврат в основной контент
    }

    public void switchToFrameTwo() {
        switchTo().frame($(FRAME_TWO));  // Переход в фрейм 2
        System.out.println($(FRAME_TEXT).getText());  // Вывод текста из фрейма 2
        switchTo().defaultContent();  // Возврат в основной контент
    }
}
