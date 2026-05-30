package com.sourav.automation.stepdefinitions;

import com.sourav.automation.base.DriverFactory;
import com.sourav.automation.utils.ConfigReader;
import com.sourav.automation.utils.LoggerUtils;
import com.sourav.automation.utils.ScreenshotUtils;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        try {
            LoggerUtils.info("========================================");
            LoggerUtils.info("Starting Scenario: " + scenario.getName());
            LoggerUtils.info("========================================");

            String browser = ConfigReader.getProperty("browser");
            driver = DriverFactory.initializeDriver(browser);

            LoggerUtils.info("WebDriver initialized for scenario: " + scenario.getName());
        } catch (Exception e) {
            LoggerUtils.error("Error in setUp hook: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to setUp WebDriver", e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            // IMPORTANT: Check failure status first
            if (scenario.isFailed()) {
                LoggerUtils.error("❌ Scenario FAILED: " + scenario.getName());
                // Capture screenshot on failure (even if driver might be in bad state)
                try {
                    if (driver != null) {
                        ScreenshotUtils.captureScreenshotOnFailure(driver, scenario.getName());
                        LoggerUtils.info("Screenshot captured for failed scenario");
                    }
                } catch (Exception screenshotException) {
                    LoggerUtils.error("Could not capture screenshot on failure: " + screenshotException.getMessage());
                }
            } else {
                LoggerUtils.info("✅ Scenario PASSED: " + scenario.getName());
            }

        } catch (Exception e) {
            LoggerUtils.error("Error in tearDown hook before driver quit: " + e.getMessage());
        } finally {
            // ALWAYS try to close the driver, even if screenshot capture fails
            try {
                if (driver != null) {
                    LoggerUtils.info("Closing WebDriver for scenario: " + scenario.getName());
                    DriverFactory.quitDriver();
                    LoggerUtils.info("WebDriver closed successfully");
                }
            } catch (Exception e) {
                LoggerUtils.error("Error closing WebDriver in finally block: " + e.getMessage());
            } finally {
                LoggerUtils.info("========================================");
            }
        }
    }
}