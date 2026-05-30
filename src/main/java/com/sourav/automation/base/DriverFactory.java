package com.sourav.automation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.sourav.automation.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    private static final String BROWSER_CHROME = "chrome";
    private static final String BROWSER_FIREFOX = "firefox";
    private static final String BROWSER_EDGE = "edge";

    public static WebDriver initializeDriver(String browserName) {

        LoggerUtils.info("Initializing WebDriver with browser: " + browserName);

        String browser = Objects.nonNull(browserName)
                && !browserName.isEmpty()
                ? browserName.toLowerCase()
                : BROWSER_CHROME;

        WebDriver webDriver;

        switch (browser) {
            case BROWSER_CHROME:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(getChromeOptions());
                break;

            case BROWSER_FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver(getFirefoxOptions());
                break;

            case BROWSER_EDGE:
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver(getEdgeOptions());
                break;

            default:
                throw new RuntimeException(
                        "Invalid browser name: " + browser
                );
        }

        webDriver.manage().window().maximize();
        driver.set(webDriver);
        LoggerUtils.info("WebDriver initialized successfully");
        return getDriver();
    }

    public static WebDriver getDriver() {

        if (driver.get() == null) {
            throw new RuntimeException(
                    "WebDriver is not initialized."
            );
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                LoggerUtils.info("Closing WebDriver");
                driver.get().quit();
            } catch (Exception e) {
                LoggerUtils.error(
                        "Error while closing WebDriver: "
                                + e.getMessage()
                );
            } finally {
                driver.remove();
            }
        }
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        // Basic useful arguments
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-translate");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        // incognito reduces chance of saved credentials interfering
        options.addArguments("--incognito");

        // Disable features that may trigger password UI
        options.addArguments("--disable-features=PasswordLeakDetection,AutofillServerCommunication,PasswordGeneration");

        // Experimental prefs to disable Chrome password manager
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("password_manager_enabled", false);
        // block notifications (0=allow, 1=block? but set to 2 to be safe)
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        // Hide "Chrome is being controlled by automated test software" and automation extensions
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        // Use a temporary clean profile so no saved credentials / Google sign-in triggers UI
        try {
            Path tmpProfile = Files.createTempDirectory("chrome-temp-profile-");
            // ensure the path is used as user-data-dir
            options.addArguments("user-data-dir=" + tmpProfile.toAbsolutePath().toString());
        } catch (Exception e) {
            // Log and continue; using a temp profile is best-effort
            System.err.println("Could not create temp chrome profile: " + e.getMessage());
        }
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox");
        // options.addArguments("--headless");  // Uncomment for headless mode
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        return options;
    }
}