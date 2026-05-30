package com.sourav.automation.stepdefinitions;

import com.sourav.automation.base.DriverFactory;
import com.sourav.automation.pages.LoginPage;
import com.sourav.automation.pages.ProductsPage;
import com.sourav.automation.utils.ExcelUtils;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class loginExcelAllRows {
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final ProductsPage productsPage;

    public loginExcelAllRows() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = new LoginPage(driver);
        this.productsPage = new ProductsPage(driver);
    }

    @Given("User logs in with all excel data")
    public void userLogsInWithAllExcelData() throws InterruptedException {
        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/testdata/LoginDataAllRows.xlsx";
        Object[][] data = ExcelUtils.getExcelData(filePath, "LoginData");
        for (Object[] row : data) {
            String username = row[0].toString();
            String password = row[1].toString();
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
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


