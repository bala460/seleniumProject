package sample.project.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sample.project.BaseTestClass;
import pages.HomePage;
import sample.project.Driver;
import utill.ExtentTestManager;

public class TestCase extends BaseTestClass {

    @Test(testName = "TC_01", description = "To validate the click button")
    public void test_01() {
        HomePage homePage = new HomePage(Driver.getDriver());
        homePage.clickRadioButtons();
        ExtentTestManager.getInstance().log("Radio Button's are Clicked");
    }

    @Test(testName = "TC_02", description = "To verify the Iframe in the Web Page")
    public void test_02() {
        HomePage homePage = new HomePage(Driver.getDriver());
        homePage.performActionOnIframe();
        ExtentTestManager.getInstance().log("Iframe verification");
    }

    @Test(testName = "TC_03", description = "To verify the Windows Handle")
    public void test_03() {
        HomePage homePage = new HomePage(Driver.getDriver());
        Assert.assertTrue(homePage.verifyWindowsHandle());
        ExtentTestManager.getInstance().log("Windows Handle is verified");
    }

    @Test(testName = "TC_04", description = "To verify the auto suggestion box")
    public void test_04() {
        HomePage homePage = new HomePage(Driver.getDriver());
        homePage.fillSuggestionBox("India");
    }

    @DataProvider(name = "dropDown_Values", parallel = true)
    public Object[][] dropDownValues() {
        return new Object[][]{{"option1"}, {"option2"}, {"option3"}};
    }

    @Test(testName = "TC_05", description = "To validate the drop down input value", dataProvider = "dropDown_Values", threadPoolSize = 3)
    public void test_05(String value){
        HomePage homePage = new HomePage(Driver.getDriver());
        homePage.selectDropDownBox(value);
    }

    @Test(testName = "TC_06", description = "To verify the tab handle")
    public void test_06(){
        HomePage homePage = new HomePage(Driver.getDriver());
        Assert.assertTrue(homePage.verifyMultiTabs());
    }

    @Test(testName = "TC_07", description = "To validate the alert button")
    public void test_07(){
        HomePage homePage = new HomePage(Driver.getDriver());
        Assert.assertTrue(homePage.alertHandle("Selenium Project"));
    }

    @Test(testName = "TC_08", description = "To validate the alert button")
    public void test_08(){
        HomePage homePage = new HomePage(Driver.getDriver());
        Assert.assertTrue(homePage.alertHandleToConfirm("Selenium Project"));
    }

    @Test(testName = "TC_09", description = "To validate the text box with hide")
    public void test_09(){
        HomePage homePage = new HomePage(Driver.getDriver());
        Assert.assertFalse(homePage.validateHideTextBox());
    }

    @Test(testName = "TC_10", description = "To validate the text box with show")
    public void test_10(){
        HomePage homePage = new HomePage(Driver.getDriver());
        Assert.assertTrue(homePage.validateShowTextBox());
    }


}
