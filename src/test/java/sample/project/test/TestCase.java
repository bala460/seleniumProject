package sample.project.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import sample.project.BaseTestClass;
import pages.HomePage;
import utill.ExtentTestManager;

public class TestCase extends BaseTestClass {

    @Test(testName = "TC_01", description = "To validate the click button")
    public void test_01(){
        HomePage homePage = new HomePage(driver);
        homePage.clickRadioButtons();
        ExtentTestManager.getInstance().log("Radio Button's are Clicked");
    }

    @Test(testName = "TC_02", description = "To verify the Iframe in the Web Page")
    public void test_02(){
        HomePage homePage = new HomePage(driver);
        homePage.performActionOnIframe();
        ExtentTestManager.getInstance().log("Iframe verification");
    }

    @Test(testName = "TC_03", description = "To verify the Windows Handle")
    public void test_03(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.verifyWindowsHandle());
        ExtentTestManager.getInstance().log("Windows Handle is verified");
    }
}
