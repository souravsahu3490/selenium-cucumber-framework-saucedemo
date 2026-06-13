package com.sourav.automation.stepdefinitions;

import com.sourav.automation.base.DriverFactory;
import com.sourav.automation.pages.LoginPage;
import com.sourav.automation.utils.ConfigReader;
import com.sourav.automation.utils.LoggerUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class login {
    private final WebDriver driver;
    private final LoginPage loginPage;

    public login() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        LoggerUtils.info("User navigates to login page");
        String baseUrl = ConfigReader.getProperty("base.url");
        driver.navigate().to(baseUrl);

        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle != null && pageTitle.contains("Swag Labs"),
                "Failed: User is not on SauceDemo login page");
        LoggerUtils.info("User is on login page successfully");
    }

    @When("User enters username {string}")
    public void userEntersUsername(String username) {
        LoggerUtils.info("Entering username: " + username);
        loginPage.enterUsername(username);
    }

    @When("User enters password {string}")
    public void userEntersPassword(String password) {
        LoggerUtils.info("Entering password");
        loginPage.enterPassword(password);
    }

    @When("User clicks login button")
    public void userClicksLoginButton() {
        LoggerUtils.info("Clicking login button");
        loginPage.clickLoginButton();
    }

    @Then("User should be logged in successfully")
    public void userShouldBeLoggedInSuccessfully() {
        LoggerUtils.info("Verifying successful login");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory___"),
                "Failed: User was not redirected to inventory page");
        LoggerUtils.info("User logged in successfully");
    }

    @Then("User should see error message")
    public void userShouldSeeErrorMessage() {
        LoggerUtils.info("Verifying error message is displayed");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Failed: Error message is not displayed");
        String errorMsg = loginPage.getErrorMessage();
        LoggerUtils.info("Error message displayed: " + errorMsg);
    }
}