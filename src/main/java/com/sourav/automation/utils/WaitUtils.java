package com.sourav.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class WaitUtils {
    private static final int DEFAULT_TIMEOUT = 20;

    /**
     * Wait for element to be visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement element) {
        try {
            LoggerUtils.info("Waiting for element to be visible");
            return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            LoggerUtils.error("Element not visible within timeout: " + e.getMessage());
            throw new RuntimeException("Element not visible", e);
        }
    }

    /**
     * Wait for element to be clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
        try {
            LoggerUtils.info("Waiting for element to be clickable");
            return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            LoggerUtils.error("Element not clickable within timeout: " + e.getMessage());
            throw new RuntimeException("Element not clickable", e);
        }
    }

    /**
     * Wait for page to load completely
     */
    public static void waitForPageLoad(WebDriver driver) {
        try {
            LoggerUtils.info("Waiting for page to load");
            new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState").equals("complete"));
        } catch (Exception e) {
            LoggerUtils.error("Page load timeout: " + e.getMessage());
        }
    }

    /**
     * Wait for element to be present in DOM
     */
    public static WebElement waitForElementPresence(WebDriver driver, org.openqa.selenium.By locator) {
        try {
            LoggerUtils.info("Waiting for element to be present in DOM");
            return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            LoggerUtils.error("Element not found in DOM: " + e.getMessage());
            throw new RuntimeException("Element not found", e);
        }
    }

    /**
     * Wait for text to be present in element
     */
    public static boolean waitForTextInElement(WebDriver driver, WebElement element, String text) {
        try {
            LoggerUtils.info("Waiting for text '" + text + "' in element");
            return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            LoggerUtils.error("Text not found in element: " + e.getMessage());
            return false;
        }
    }

    /**
     * Custom explicit wait with custom timeout
     */
    public static WebElement waitForElementWithCustomTimeout(WebDriver driver, WebElement element, int timeoutInSeconds) {
        try {
            LoggerUtils.info("Waiting for element with custom timeout: " + timeoutInSeconds + " seconds");
            return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            LoggerUtils.error("Element not visible within custom timeout: " + e.getMessage());
            throw new RuntimeException("Element not visible", e);
        }
    }

    /**
     * Thread sleep (not recommended but sometimes necessary)
     */
    public static void sleep(long millis) {
        try {
            LoggerUtils.info("Sleeping for " + millis + " milliseconds");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LoggerUtils.error("Sleep interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}