package utill;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentTestManager {

    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final ExtentReports extent = ExtentManager.getInstance();

    public static ExtentTestManager getInstance() {
        return new ExtentTestManager();
    }

    public synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    public synchronized void flush() {
        extent.flush();
    }


    public synchronized ExtentTest createTest(String name, String description, String category) {
        ExtentTest test = extent.createTest(name, description);

        if (category != null && !category.isEmpty())
            test.assignCategory(category);

        extentTest.set(test);

        return extentTest.get();
    }

    public synchronized ExtentTest createTest(String name, String description) {
        return ExtentTestManager.this.createTest(name, description, null);
    }

    public synchronized ExtentTest createTest(String name) {
        return ExtentTestManager.this.createTest(name, null);
    }

    public synchronized void log(String message) {
        ExtentTestManager.this.getTest().info(message);
    }

    public synchronized void log(Status log, String message) {
        ExtentTestManager.this.getTest().log(log, message);
    }

    public synchronized void pass(String message) {
        ExtentTestManager.this.getTest().pass(message);
    }

    public synchronized void fail(String message) {
        ExtentTestManager.this.getTest().fail(message);
    }

    public synchronized void logWithScreenShot(Status status, String message, String path) {
        try {
            ExtentTestManager.this.getTest().log(status, message).addScreenCaptureFromPath(path, "title");
        } catch (Exception e) {
            ExtentTestManager.this.getTest().log(status, e.getLocalizedMessage());
        }
    }
}