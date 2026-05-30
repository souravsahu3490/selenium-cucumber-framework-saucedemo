package com.sourav.automation.stepdefinitions;

import com.sourav.automation.base.DriverFactory;
import com.sourav.automation.pages.LoginPage;
import com.sourav.automation.pages.ProductsPage;
import com.sourav.automation.utils.ExcelUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class loginExcel {

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final ProductsPage productsPage;
    String username;
    String password;
    String filePath = System.getProperty("user.dir")
            + "/src/test/resources/testdata/LoginData.xlsx";

    public loginExcel() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = new LoginPage(driver);
        this.productsPage = new ProductsPage(driver);
    }

    @Given("User reads login data from excel row {int}")
    public void userReadsLoginDataFromExcelRow(int rowNum) {
        username = ExcelUtils.getCellData(
                filePath,
                "LoginData",
                rowNum,
                0
        );
        password = ExcelUtils.getCellData(
                filePath,
                "LoginData",
                rowNum,
                1
        );
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }

    @When("User logs into application")
    public void userLogsIntoApplication() {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Then("Login should be successful for excel row {int}")
    public void loginShouldBeSuccessful(int rowNum) {
        boolean result = productsPage.isProductsPageDisplayed();
        if (result) {
            ExcelUtils.setCellData(
                    filePath,
                    "LoginData",
                    rowNum,
                    2,
                    "PASS"
            );
        } else {
            ExcelUtils.setCellData(
                    filePath,
                    "LoginData",
                    rowNum,
                    2,
                    "FAIL"
            );
        }
    }
}