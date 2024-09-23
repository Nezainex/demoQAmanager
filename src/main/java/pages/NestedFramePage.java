package pages;

import locators.NestedFrameLocators;

import static com.codeborne.selenide.Selenide.*;

public class NestedFramePage {

    public void switchToParentFrame() {
        // Переходим на родительский фрейм (первый iframe)
        switchTo().frame($(NestedFrameLocators.PARENT_FRAME));
        // Получаем текст родительского фрейма и выводим его
        System.out.println("Parent Frame Text: " + $(NestedFrameLocators.PARENT_FRAME_BODY).getText());
        // Возвращаемся в основной контент
        switchTo().defaultContent();
    }

    public void switchToChildFrame() {
        // Переходим на родительский фрейм
        switchTo().frame($(NestedFrameLocators.PARENT_FRAME));
        // Переходим во вложенный фрейм, используя локатор
        switchTo().frame($(NestedFrameLocators.CHILD_IFRAME));
        // Получаем текст дочернего фрейма и выводим его
        System.out.println("Child Frame Text: " + $(NestedFrameLocators.CHILD_FRAME_BODY).getText());
        // Возвращаемся в основной контент
        switchTo().defaultContent();
    }

    public int getIFrameCountInParentFrame() {
        switchTo().frame($(NestedFrameLocators.PARENT_FRAME));
        int iframeCount = $$("iframe").size();
        switchTo().defaultContent();
        return iframeCount;
    }

    public int getIFrameCountInMainPage() {
        return $$("iframe").size();
    }
}
