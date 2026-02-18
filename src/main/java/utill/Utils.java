package utill;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class Utils {

    public static Utils getInstance() {
        return new Utils();
    }

    public boolean isElementVisible(WebDriver driver, WebElement ele, Duration time){
        if(isElementPresent(driver, ele, time)){
            return ele.isDisplayed();
        }
        ExtentTestManager.getInstance().log(Status.WARNING, ele+" Element is not Displayed");
        return false;
    }
    public boolean isElementPresent(WebDriver driver, WebElement ele, Duration time) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, time);
            webDriverWait.until(ExpectedConditions.visibilityOf(ele));
            ExtentTestManager.getInstance().log(ele.getAccessibleName() + " Element is present");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getInstance().log(Status.WARNING, ele.getAccessibleName() + " Element is not present");
            return false;
        }
    }

    public boolean isElementClickable(WebDriver driver, WebElement ele, Duration time) {
        if (Utils.this.isElementPresent(driver, ele, time)) {
            try {
                WebDriverWait webDriverWait = new WebDriverWait(driver, time);
                webDriverWait.until(ExpectedConditions.elementToBeClickable(ele));
                ExtentTestManager.getInstance().log(ele.getAccessibleName() + " Element is clickable");
                return true;
            } catch (Exception e) {
                ExtentTestManager.getInstance().log(ele.getAccessibleName() + " Element is not clickable");
                return false;
            }
        } else {
            ExtentTestManager.getInstance().log(Status.WARNING, ele.getAccessibleName() + " Element is not present");
            return false;
        }
    }

    public void clickElement(WebDriver driver, WebElement ele, Duration time) {
        if (isElementClickable(driver, ele, time)) {
            ExtentTestManager.getInstance().log(ele.getAccessibleName() + " Element is clicked");
            ele.click();
        }
    }

    public void scrollToView(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        ExtentTestManager.getInstance().log(element.getAccessibleName() + " is scrolled to view");
    }

    public WebDriver switchToIframe(WebDriver driver, WebElement element) {
        ExtentTestManager.getInstance().log(element.getAccessibleName() + " switching to Iframe");
        return driver.switchTo().frame(element);
    }

    public WebDriver switchToDefault(WebDriver driver) {
        ExtentTestManager.getInstance().log("Switching to default content");
        return driver.switchTo().defaultContent();
    }

    public WebDriver switchToWindowHandle(WebDriver driver, String windowHandle) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String s : allWindowHandles) {
            if (Objects.requireNonNull(driver.switchTo().window(s).getTitle()).contains(windowHandle)) {
                ExtentTestManager.getInstance().log("Window switch to " + windowHandle);
                return driver.switchTo().window(s);
            }
        }
        ExtentTestManager.getInstance().log("Failed to switch between the tabs");
        return null;
    }

    public void sendText(WebDriver driver, WebElement element, String text, Duration time) {
        if (isElementPresent(driver, element, time)) {
            element.sendKeys(text);
            ExtentTestManager.getInstance().log(element + " element is filled with " + text + " text");
        } else {
            ExtentTestManager.getInstance().log(Status.WARNING, element + " element is not present, failed to enter " + text + " text");
        }
    }

    public void selectDropDown(WebDriver driver, WebElement element, String value, Duration time){
        if (isElementPresent(driver, element, time)){
            Select select = new Select(element);
            select.selectByValue(value);
            ExtentTestManager.getInstance().log("Dropdown "+element+" with "+value+" value is selected");
        }else {
            ExtentTestManager.getInstance().log(Status.WARNING, "Dropdown element "+element+" is not present");
        }
    }

    public void captureScreenshot(WebDriver driver) {
        try {
            ExtentTestManager.getInstance().log("Taking screenshot: ");
            String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots";
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String screenshotName = "screenshot_" + new Random().nextInt(999) + ".png";
            screenshotPath = screenshotPath + File.separator + screenshotName;
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            ExtentTestManager.getInstance().logWithScreenShot(Status.INFO, "Failure in Test Case", screenshotPath);
        } catch (IOException e) {
            ExtentTestManager.getInstance().log(Status.WARNING, e.getLocalizedMessage());
        }

    }

    public void acceptAlert(WebDriver driver){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean isAlertTextMatching(WebDriver driver, String expectedValue){
        Alert alert = driver.switchTo().alert();
        return alert.getText().equalsIgnoreCase(expectedValue);
    }
}
