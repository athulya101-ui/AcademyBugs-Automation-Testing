package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        System.out.println("===== STARTING BROWSER =====");

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);

        driver.manage()
              .timeouts()
              .pageLoadTimeout(Duration.ofSeconds(60));

        driver.manage()
              .timeouts()
              .scriptTimeout(Duration.ofSeconds(30));

        System.out.println("Browser started successfully.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        System.out.println("===== CLOSING BROWSER =====");

        if (driver != null) {

            driver.quit();
            driver = null;
        }
    }
}