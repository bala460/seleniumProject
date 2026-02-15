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

    @FindBy(how = How.XPATH, using = "(//a[text()='Courses'])[1]")
    private WebElement courses;

    private final WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    Utils utils = new Utils();

    public boolean verifyWindowsHandle(){
        utils.clickElement(driver, openWindow, Duration.ofSeconds(60));
        WebDriver driverWindowsHandle = utils.switchToWindowHandle(driver, "QAClick");
        return utils.checkElementPresent(driverWindowsHandle, courses, Duration.ofSeconds(60));
    }

    public void performActionOnIframe(){
        utils.scrollToView(driver, iFrame);
        WebDriver driverIframe = utils.switchToIframe(driver, iFrame);
        Assert.assertTrue(utils.checkElementPresent(driverIframe, register, Duration.ofSeconds(60)));
    }

    public void clickRadioButtons(){
        utils.clickElement(driver, radioButton1, Duration.ofSeconds(60));
        utils.clickElement(driver, radioButton2, Duration.ofSeconds(60));
        utils.clickElement(driver, radioButton3, Duration.ofSeconds(60));
    }
}
