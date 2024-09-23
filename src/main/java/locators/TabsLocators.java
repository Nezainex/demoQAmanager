package locators;

import org.openqa.selenium.By;

public class TabsLocators {
    // Вкладки
    public static final By TAB_WHAT = By.xpath("//a[@id='demo-tab-what']");
    public static final By TAB_ORIGIN = By.xpath("//a[@id='demo-tab-origin']");
    public static final By TAB_USE = By.xpath("//a[@id='demo-tab-use']");
    public static final By TAB_MORE = By.xpath("//a[@id='demo-tab-more']");

    // Панели вкладок
    public static final By TAB_PANEL_WHAT = By.xpath("//div[@id='demo-tabpane-what']");
    public static final By TAB_PANEL_ORIGIN = By.xpath("//div[@id='demo-tabpane-origin']");
    public static final By TAB_PANEL_USE = By.xpath("//div[@id='demo-tabpane-use']");
    public static final By TAB_PANEL_MORE = By.xpath("//div[@id='demo-tabpane-more']");
}
