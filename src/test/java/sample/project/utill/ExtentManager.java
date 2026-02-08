package sample.project.utill;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {

    static ExtentReports  extent;

    public static ExtentReports  getInstance() {
        return extent;
    }

    public static synchronized void createInstance(String fileName) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(new File(fileName));

        // Configure the reporter
        sparkReporter.config().setDocumentTitle("UI Test Execution Report");
        sparkReporter.config().setReportName("Automation Test Results");

        // Initialize ExtentReports and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system info (optional but useful)
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

}
