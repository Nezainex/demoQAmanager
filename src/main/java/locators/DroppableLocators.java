package locators;

import org.openqa.selenium.By;

public class DroppableLocators {

    // Tabs
    public static final By SIMPLE_TAB = By.xpath("//a[@id='droppableExample-tab-simple']");
    public static final By ACCEPT_TAB = By.xpath("//a[@id='droppableExample-tab-accept']");
    public static final By PREVENT_PROPAGATION_TAB = By.xpath("//a[@id='droppableExample-tab-preventPropogation']");
    public static final By REVERT_DRAGGABLE_TAB = By.xpath("//a[@id='droppableExample-tab-revertable']");

    // Simple
    public static final By DRAG_ME_SIMPLE = By.xpath("//div[@id='draggable']");
    public static final By DROP_HERE_SIMPLE = By.xpath("//div[@id='droppable']");

    // Accept
    public static final By ACCEPTABLE = By.xpath("//div[@id='acceptable']");
    public static final By NOT_ACCEPTABLE = By.xpath("//div[@id='notAcceptable']");
    public static final By DROP_HERE_ACCEPT = By.xpath("//div[@id='acceptDropContainer']//div[@id='droppable']");

    // Prevent Propagation
    public static final By DRAG_BOX = By.xpath("//div[@id='dragBox']");
    public static final By OUTER_NOT_GREEDY_DROPBOX = By.xpath("//div[@id='notGreedyDropBox']");
    public static final By INNER_NOT_GREEDY_DROPBOX = By.xpath("//div[@id='notGreedyInnerDropBox']");
    public static final By OUTER_GREEDY_DROPBOX = By.xpath("//div[@id='greedyDropBox']");
    public static final By INNER_GREEDY_DROPBOX = By.xpath("//div[@id='greedyDropBoxInner']");

    // Revert Draggable
    public static final By WILL_REVERT = By.xpath("//div[@id='revertable']");
    public static final By NOT_REVERT = By.xpath("//div[@id='notRevertable']");
    public static final By DROP_HERE_REVERT = By.xpath("//div[@id='revertableDropContainer']//div[@id='droppable']");
}
