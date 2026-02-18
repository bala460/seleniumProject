package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utill.Utils;


import java.time.Duration;

public class HomePage {

    @FindBy(how = How.XPATH, using = "//input[@value='radio2']")
    private WebElement radioButton2;

    @FindBy(how = How.ID, using = "name")
    private WebElement name;

    @FindBy(how = How.ID, using = "alertbtn")
    private WebElement alertBtn;

    @FindBy(how = How.XPATH, using = "//input[@value='radio1']")
    private WebElement radioButton1;

    @FindBy(how = How.XPATH, using = "//input[@value='radio3']")
    private WebElement radioButton3;

    @FindBy(how = How.ID, using = "courses-iframe")
    private WebElement iFrame;

    @FindBy(how = How.XPATH, using = "//a[text()='Register']")
    private WebElement register;

    @FindBy(how = How.ID, using = "openwindow")
    private WebElement openWindow;

    @FindBy(how = How.ID, using = "opentab")
    private WebElement openTab;

    @FindBy(how = How.XPATH, using = "(//a[text()='Courses'])[1]")
    private WebElement courses;

    @FindBy(how = How.ID, using = "autocomplete")
    private WebElement autocomplete;

    @FindBy(how = How.ID, using = "dropdown-class-example")
    private WebElement dropDown;

    @FindBy(how = How.ID, using = "confirmbtn")
    private WebElement confirmBtn;

    @FindBy(how = How.ID, using = "hide-textbox")
    private WebElement hideTextBox;

    @FindBy(how = How.ID, using = "show-textbox")
    private WebElement showTextBox;

    @FindBy(how = How.ID, using = "displayed-text")
    private WebElement displayedText;

    @FindBy(how = How.ID, using = "mousehover")
    private WebElement mouseHover;

    @FindBy(how = How.XPATH, using = "//a[text()='Top']")
    private WebElement top;

    @FindBy(how = How.XPATH, using = "Reload")
    private WebElement reload;

    private final WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    Utils utils = new Utils();

    public boolean verifyWindowsHandle(){
        utils.clickElement(driver, openWindow, Duration.ofSeconds(60));
        WebDriver driverWindowsHandle = utils.switchToWindowHandle(driver, "QAClick");
        return utils.isElementPresent(driverWindowsHandle, courses, Duration.ofSeconds(60));
    }

    public boolean verifyMultiTabs(){
        utils.clickElement(driver, openTab, Duration.ofSeconds(60));
        WebDriver driverTab = utils.switchToWindowHandle(driver, "QAClick");
        return utils.isElementPresent(driverTab, courses, Duration.ofSeconds(60));
    }

    public void performActionOnIframe(){
        utils.scrollToView(driver, iFrame);
        WebDriver driverIframe = utils.switchToIframe(driver, iFrame);
        Assert.assertTrue(utils.isElementPresent(driverIframe, register, Duration.ofSeconds(60)));
    }

    public void clickRadioButtons(){
        utils.clickElement(driver, radioButton1, Duration.ofSeconds(60));
        utils.clickElement(driver, radioButton2, Duration.ofSeconds(60));
        utils.clickElement(driver, radioButton3, Duration.ofSeconds(60));
    }

    public void fillSuggestionBox(String text){
        utils.sendText(driver, autocomplete, text, Duration.ofSeconds(60));
    }

    public void selectDropDownBox(String value){
        utils.selectDropDown(driver, dropDown, value, Duration.ofSeconds(60));
    }

    public boolean alertHandle(String text){
        utils.sendText(driver, name, text, Duration.ofSeconds(60));
        String alertMessage = "Hello %s, share this practice page and share your knowledge";
        utils.clickElement(driver, alertBtn, Duration.ofSeconds(60));
        return utils.isAlertTextMatching(driver, String.format(alertMessage, text));
    }

    public boolean alertHandleToConfirm(String text){
        utils.sendText(driver, name, text, Duration.ofSeconds(60));
        String alertMessage = "Hello %s, Are you sure you want to confirm?";
        utils.clickElement(driver, confirmBtn, Duration.ofSeconds(60));
        boolean result = utils.isAlertTextMatching(driver, String.format(alertMessage, text));
        utils.acceptAlert(driver);
        return result;
    }

    public boolean validateHideTextBox(){
        utils.clickElement(driver, hideTextBox, Duration.ofSeconds(60));
        return utils.isElementPresent(driver, displayedText, Duration.ofSeconds(9));
    }

    public boolean validateShowTextBox(){
        utils.clickElement(driver, hideTextBox, Duration.ofSeconds(60));
        utils.clickElement(driver, showTextBox, Duration.ofSeconds(60));
        return utils.isElementPresent(driver, displayedText, Duration.ofSeconds(10));
    }

    public boolean validateMouseHover(){
        utils.scrollToView(driver, mouseHover);
        utils.clickElement(driver, mouseHover, Duration.ofSeconds(30));
        return utils.isElementPresent(driver, top, Duration.ofSeconds(60)) && utils.isElementPresent(driver, reload, Duration.ofSeconds(60));
    }

}
