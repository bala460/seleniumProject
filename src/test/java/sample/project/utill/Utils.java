package sample.project.utill;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;
import java.util.Set;

public class Utils {

    public static Utils getInstance() {
        return new Utils();
    }

    public boolean checkElementPresent(WebDriver driver, WebElement ele, Duration time) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, time);
            webDriverWait.until(ExpectedConditions.visibilityOf(ele));
            ExtentTestManager.log(ele.getAccessibleName()+" Element is present");
            return true;
        } catch (Exception e) {
            ExtentTestManager.log(Status.WARNING, ele.getAccessibleName()+" Element is not present");
            return false;
        }
    }

    public boolean checkElementClickable(WebDriver driver, WebElement ele, Duration time) {
        if (Utils.this.checkElementPresent(driver, ele, time)) {
            try {
                WebDriverWait webDriverWait = new WebDriverWait(driver, time);
                webDriverWait.until(ExpectedConditions.elementToBeClickable(ele));
                ExtentTestManager.log(ele.getAccessibleName()+" Element is clickable");
                return true;
            } catch (Exception e) {
                ExtentTestManager.log(ele.getAccessibleName()+" Element is not clickable");
                return false;
            }
        }else {
            ExtentTestManager.log(Status.WARNING, ele.getAccessibleName()+" Element is not present");
            return false;
        }
    }

    public void clickElement(WebDriver driver, WebElement ele, Duration time){
        if(checkElementClickable(driver, ele, time)){
            ExtentTestManager.log(ele.getAccessibleName()+" Element is clicked");
            ele.click();
        }
    }

    public void scrollToView(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        ExtentTestManager.log(element.getAccessibleName()+" is scrolled to view");
    }

    public WebDriver switchToIframe(WebDriver driver, WebElement element){
        ExtentTestManager.log(element.getAccessibleName()+" switching to Iframe");
        return driver.switchTo().frame(element);
    }

    public WebDriver switchToDefault(WebDriver driver){
        ExtentTestManager.log("Switching to default content");
        return driver.switchTo().defaultContent();
    }

    public WebDriver switchToWindowHandle(WebDriver driver, String windowHandle){
        Set<String> allWindowHandles = driver.getWindowHandles();
        for(String s : allWindowHandles){
            if(Objects.requireNonNull(driver.switchTo().window(s).getTitle()).contains(windowHandle)){
                ExtentTestManager.log("Window switch to "+windowHandle);
                return driver.switchTo().window(s);
            }
        }
        ExtentTestManager.log("Failed to switch between the tabs");
        return null;
    }
}
