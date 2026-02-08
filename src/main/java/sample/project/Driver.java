package sample.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    // ThreadLocal used to manage the driver
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Private constructor to prevent the creation of new instances of Driver
    private Driver(){}



    // Public method to access the driver instance (uses lazy instantiation)
    public static WebDriver getInstance() {
        WebDriverManager.chromedriver().setup();
        if (driver.get() == null) {
            driver.set(new ChromeDriver());
        }
        return driver.get();
    }

    /*
    Public method to quit the driver and
    remove the current thread's value for this thread-local variable
     */
    public static void quit() {
        driver.get().quit();
        driver.remove();
    }
}
