package com.sourav.automation.stepdefinitions;

import com.sourav.automation.base.DriverFactory;
import com.sourav.automation.pages.LoginPage;
import com.sourav.automation.pages.ProductsPage;
import com.sourav.automation.utils.DBUtils;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class loginDBAllRows {
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final ProductsPage productsPage;

    public loginDBAllRows() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = new LoginPage(driver);
        this.productsPage = new ProductsPage(driver);
    }

    @Given("User logs in with DB table all rows")
    public void userLogsInWithDBTableAllData() throws InterruptedException {
        String query = "SELECT Username,Password FROM LoginData";
        List<Map<String, String>> testData = DBUtils.executeQuery(query);

        for (Map<String, String> row : testData) {
            String username = row.get("Username");
            String password = row.get("Password");
            System.out.println(username + " : " + password);

            // Perform login
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();

            // validation
            if (productsPage.isProductsPageDisplayed()) {
                System.out.println("Login Success");
            } else {
                System.out.println("Login Failed");
            }

            // logout before next iteration
            productsPage.logout();
        }
    }
}


