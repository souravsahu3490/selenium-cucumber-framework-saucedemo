package com.sourav.automation.stepdefinitions;

import com.sourav.automation.base.DriverFactory;
import com.sourav.automation.pages.*;
import com.sourav.automation.utils.LoggerUtils;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class loginExamples {
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final ProductsPage productsPage;


    public loginExamples() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = new LoginPage(driver);
        this.productsPage = new ProductsPage(driver);
    }

    @Then("Login result should be {string} for user type {string}")
    public void verifyLoginResult(String expectedResult, String userType) {
        LoggerUtils.info("Verifying login result for user type: " + userType + ", Expected: " + expectedResult);

        if ("success".equalsIgnoreCase(expectedResult)) {
            Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Failed: Login was not successful for " + userType);
            LoggerUtils.info("✅ Login successful for user type: " + userType);
        } else if ("fail".equalsIgnoreCase(expectedResult)) {
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertNotNull(errorMsg, "Failed: Error message not displayed for " + userType);
            LoggerUtils.error("❌ Login failed as expected for " + userType + ": " + errorMsg);
        }
    }
}