package sample.project;


import com.aventstack.extentreports.Status;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utill.ExtentManager;
import utill.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;

public class BaseTestClass {

    public WebDriver driver;

    @BeforeSuite
    public void startReporter() {
        if (ExtentManager.getInstance() == null) {
            ExtentManager.createInstance(System.getProperty("user.dir") + "/test-output/extentReport.html");
        }
    }

    @BeforeMethod
    public void setUp(Method method) {
        driver = Driver.getInstance();
        ExtentTestManager.getInstance().createTest(method.getAnnotation(Test.class).testName(), method.getAnnotation(Test.class).description());
        ExtentTestManager.getInstance().log("Starting test " + method.getAnnotation(Test.class).description());
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().fullscreen();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                captureScreenshot();
                ExtentTestManager.getInstance().fail(result.getMethod().getMethodName());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                ExtentTestManager.getInstance().pass(result.getMethod().getMethodName());
            } else {
                ExtentTestManager.getInstance().log(Status.SKIP, result.getMethod().getMethodName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Driver.quit();
        }
    }

    @AfterSuite
    public void tearDown() {
        ExtentTestManager.getInstance().flush();
    }

    public void captureScreenshot() {
        try {
            ExtentTestManager.getInstance().log("Taking screenshot for failed assert");
            String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots";
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String screenshotName = "screenshot_" + new Random().nextInt(999) + ".png";
            screenshotPath = screenshotPath + File.separator + screenshotName;
            Files.copy(screenshot, new File(screenshotPath));
            ExtentTestManager.getInstance().logWithScreenShot(Status.INFO, "Failure in Test Case", screenshotPath);
        } catch (IOException e) {
            ExtentTestManager.getInstance().log(Status.WARNING, e.getLocalizedMessage());
        }

    }
}
