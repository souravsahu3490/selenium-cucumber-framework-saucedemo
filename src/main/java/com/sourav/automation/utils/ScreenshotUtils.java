package com.sourav.automation.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    /**
     * Capture screenshot and attach to Allure report
     */
    public static void captureScreenshot(WebDriver driver, String screenshotName) {

        try {
            LoggerUtils.info("Capturing screenshot for Allure...");
            byte[] screenshotBytes =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.BYTES);

            Allure.getLifecycle().addAttachment(
                    screenshotName,
                    "image/png",
                    "png",
                    screenshotBytes
            );

            LoggerUtils.info(
                    "Screenshot attached successfully to Allure: "
                            + screenshotName
            );

        } catch (Exception e) {
            LoggerUtils.error(
                    "Error capturing screenshot for Allure: "
                            + e.getMessage()
            );
        }
    }

    /**
     * Save screenshot to a file
     */
    public static String saveScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);

            String screenshotPath = ConfigReader.getProperty("screenshot.path", "./screenshots/");
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";
            String filePath = screenshotPath + fileName;

            // Create directory if it doesn't exist
            Files.createDirectories(Paths.get(screenshotPath));

            File destinationFile = new File(filePath);
            org.openqa.selenium.io.FileHandler.copy(screenshotFile, destinationFile);

            LoggerUtils.info("Screenshot saved at: " + filePath);
            return filePath;
        } catch (IOException e) {
            LoggerUtils.error("Error saving screenshot to file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Capture and save screenshot on failure
     */
    public static void captureScreenshotOnFailure(WebDriver driver, String testName) {
        if (ConfigReader.getBooleanProperty("screenshot.on.failure")) {
            try {
                LoggerUtils.info("Capturing failure screenshot for: " + testName);

                // Save to file
                String filePath = saveScreenshot(driver, testName + "_FAILED");

                // Attach to Allure
                captureScreenshot(driver, testName + "_FAILED");

                // Also log the failure to Allure
                Allure.step("Test Failed - Screenshot Captured", () -> {
                    LoggerUtils.info("Failure screenshot captured for test: " + testName + " at " + filePath);
                });

            } catch (Exception e) {
                LoggerUtils.error("Error capturing failure screenshot: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            LoggerUtils.warn("Screenshot on failure is disabled in config");
        }
    }
}