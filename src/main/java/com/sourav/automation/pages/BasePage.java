package com.sourav.automation.pages;

import com.sourav.automation.utils.LoggerUtils;
import com.sourav.automation.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;

import static com.sourav.automation.utils.WaitUtils.waitForElementToBeVisible;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Click on an element with explicit wait
     */
    public void click(WebElement element) {
        try {
            WaitUtils.waitForElementToBeClickable(driver, element);
            element.click();
            LoggerUtils.info("Clicked on element: " + element);
        } catch (Exception e) {
            LoggerUtils.error("Error clicking element: " + e.getMessage());
            throw new RuntimeException("Failed to click element", e);
        }
    }

    /**
     * Clear and send text to an element
     */
    public void sendKeys(WebElement element, String text) {
        try {
            waitForElementToBeVisible(driver, element);
            element.clear();
            element.sendKeys(text);
            LoggerUtils.info("Entered text: " + text + " in element: " + element);
        } catch (Exception e) {
            LoggerUtils.error("Error sending keys to element: " + e.getMessage());
            throw new RuntimeException("Failed to send keys", e);
        }
    }

    /**
     * Get text from an element
     */
    public String getText(WebElement element) {
        try {
            waitForElementToBeVisible(driver, element);
            String text = element.getText();
            LoggerUtils.info("Element text retrieved: " + text);
            return text;
        } catch (Exception e) {
            LoggerUtils.error("Error getting text from element: " + e.getMessage());
            throw new RuntimeException("Failed to get element text", e);
        }
    }

    /**
     * Navigate to a URL
     */
    public void navigateTo(String url) {
        try {
            driver.navigate().to(url);
            LoggerUtils.info("Navigated to URL: " + url);
        } catch (Exception e) {
            LoggerUtils.error("Error navigating to URL: " + e.getMessage());
            throw new RuntimeException("Failed to navigate to URL", e);
        }
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        String title = driver.getTitle();
        LoggerUtils.info("Page title: " + title);
        return title;
    }

    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        LoggerUtils.info("Current URL: " + url);
        return url;
    }

    /**
     * Check if element is displayed
     */
    public boolean isElementDisplayed(WebElement element) {
        try {
            boolean isDisplayed = element.isDisplayed();
            LoggerUtils.info("Element displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            LoggerUtils.error("Element not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if element is enabled
     */
    public boolean isElementEnabled(WebElement element) {
        try {
            boolean isEnabled = element.isEnabled();
            LoggerUtils.info("Element enabled: " + isEnabled);
            return isEnabled;
        } catch (Exception e) {
            LoggerUtils.error("Element not enabled: " + e.getMessage());
            return false;
        }
    }

    /**
     * Execute JavaScript
     */
    public Object executeScript(String script, Object... args) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            LoggerUtils.info("Executing JavaScript: " + script);
            return jsExecutor.executeScript(script, args);
        } catch (Exception e) {
            LoggerUtils.error("Error executing JavaScript: " + e.getMessage());
            throw new RuntimeException("Failed to execute JavaScript", e);
        }
    }

    /**
     * Refresh page
     */
    public void refreshPage() {
        try {
            driver.navigate().refresh();
            LoggerUtils.info("Page refreshed");
        } catch (Exception e) {
            LoggerUtils.error("Error refreshing page: " + e.getMessage());
        }
    }

    /**
     * Wait for page load
     */
    public void waitForPageLoad() {
        try {
            WaitUtils.waitForPageLoad(driver);
            LoggerUtils.info("Page loaded successfully");
        } catch (Exception e) {
            LoggerUtils.error("Error waiting for page load: " + e.getMessage());
        }
    }

    /**
     * Get attribute value of an element
     */
    public String getAttribute(WebElement element, String attributeName) {
        waitForElementToBeVisible(driver, element);
        return element.getAttribute(attributeName);
    }
}