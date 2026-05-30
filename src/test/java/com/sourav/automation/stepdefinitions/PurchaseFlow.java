package com.sourav.automation.stepdefinitions;

import com.sourav.automation.base.DriverFactory;
import com.sourav.automation.pages.*;
import com.sourav.automation.utils.LoggerUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PurchaseFlow {
    private final WebDriver driver;
    private final ProductsPage productsPage;
    private final CartPage cartPage;
    private final CheckoutPage checkoutPage;
    private final CheckoutOverviewPage checkoutOverviewPage;
    private final CheckoutCompletePage checkoutCompletePage;

    public PurchaseFlow() {
        this.driver = DriverFactory.getDriver();
        this.productsPage = new ProductsPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
        this.checkoutOverviewPage = new CheckoutOverviewPage(driver);
        this.checkoutCompletePage = new CheckoutCompletePage(driver);
    }

    // ================ Products Page Steps ================

    @Given("User is on the products page")
    public void userIsOnProductsPage() {
        LoggerUtils.info("Verifying user is on products page");
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "Failed: User is not on products page");
        LoggerUtils.info("User is on products page successfully");
    }

    @When("User adds the first product to cart")
    public void userAddsFirstProductToCart() {
        LoggerUtils.info("Adding first product to cart");
        productsPage.addFirstProductToCart();
        LoggerUtils.info("First product added to cart successfully");
    }

    @When("User adds the second product to cart")
    public void userAddsSecondProductToCart() {
        LoggerUtils.info("Adding second product to cart");
        productsPage.addSecondProductToCart();
        LoggerUtils.info("Second product added to cart successfully");
    }

    @Then("Product count in cart should be {int}")
    public void verifyProductCountInCart(int expectedCount) {
        LoggerUtils.info("Verifying cart item count is " + expectedCount);
        int actualCount = productsPage.getCartItemCount();
        Assert.assertEquals(actualCount, expectedCount,
                "Cart item count mismatch. Expected: " + expectedCount + ", Actual: " + actualCount);
        LoggerUtils.info("Cart count verified successfully");
    }

    // ================ Cart Page Steps ================

    @When("User navigates to cart")
    public void userNavigatesToCart() {
        LoggerUtils.info("Navigating to cart");
        productsPage.navigateToCart();
        LoggerUtils.info("Navigated to cart successfully");
    }

    @Then("User should see {int} items in cart")
    public void userShouldSeeItemsInCart(int expectedCount) {
        LoggerUtils.info("Verifying cart has " + expectedCount + " items");
        int actualCount = cartPage.getCartItemCount();
        Assert.assertEquals(actualCount, expectedCount,
                "Cart item count mismatch. Expected: " + expectedCount + ", Actual: " + actualCount);
        LoggerUtils.info("Cart item count verified successfully");
    }

    @Then("User should see product names in cart")
    public void userShouldSeeProductNamesInCart() {
        LoggerUtils.info("Verifying product names are displayed in cart");
        var productNames = cartPage.getProductNamesInCart();
        Assert.assertFalse(productNames.isEmpty(),
                "Failed: No product names found in cart");
        LoggerUtils.info("Product names in cart: " + productNames);
    }

    @Then("User should see product prices in cart")
    public void userShouldSeeProductPricesInCart() {
        LoggerUtils.info("Verifying product prices are displayed in cart");
        var productPrices = cartPage.getProductPricesInCart();
        Assert.assertFalse(productPrices.isEmpty(),
                "Failed: No product prices found in cart");
        LoggerUtils.info("Product prices in cart: " + productPrices);
    }

    @When("User removes the first product from cart")
    public void userRemovesFirstProductFromCart() {
        LoggerUtils.info("Removing first product from cart");
        cartPage.removeProductFromCart(1);
        LoggerUtils.info("First product removed from cart successfully");
    }

    // ================ Checkout Page Steps ================

    @When("User clicks checkout button")
    public void userClicksCheckoutButton() {
        LoggerUtils.info("Clicking checkout button");
        cartPage.clickCheckoutButton();
        LoggerUtils.info("Checkout button clicked successfully");
    }

    @When("User fills checkout details with first name {string}, last name {string}, postal code {string}")
    public void userFillsCheckoutDetails(String firstName, String lastName, String postalCode) {
        LoggerUtils.info("Filling checkout details - FirstName: " + firstName + ", LastName: " + lastName + ", PostalCode: " + postalCode);
        checkoutPage.fillCheckoutDetails(firstName, lastName, postalCode);
        LoggerUtils.info("Checkout details filled successfully");
    }

    @When("User clicks continue button")
    public void userClicksContinueButton() {
        LoggerUtils.info("Clicking continue button");
        checkoutPage.clickContinueButton();
        LoggerUtils.info("Continue button clicked successfully");
    }

    // ================ Checkout Overview Page Steps ================

    @Then("User should see order summary")
    public void userShouldSeeOrderSummary() {
        LoggerUtils.info("Verifying order summary is displayed");
        Assert.assertTrue(checkoutOverviewPage.isOrderSummaryDisplayed(),
                "Failed: Order summary is not displayed");
        LoggerUtils.info("Order summary verified successfully");
    }

    @Then("User should see order summary with {int} item")
    public void userShouldSeeOrderSummaryWithItems(int expectedCount) {
        LoggerUtils.info("Verifying order summary with " + expectedCount + " item(s)");
        Assert.assertTrue(checkoutOverviewPage.isOrderSummaryDisplayed(),
                "Failed: Order summary is not displayed");
        int actualCount = checkoutOverviewPage.getOrderItemCount();
        Assert.assertEquals(actualCount, expectedCount,
                "Order item count mismatch. Expected: " + expectedCount + ", Actual: " + actualCount);
        LoggerUtils.info("Order summary with " + expectedCount + " item(s) verified successfully");
    }

    // ================ Checkout Complete Page Steps ================

    @When("User clicks finish button")
    public void userClicksFinishButton() {
        LoggerUtils.info("Clicking finish button");
        checkoutOverviewPage.clickFinishButton();
        LoggerUtils.info("Finish button clicked successfully");
    }

    @Then("User should see order completion message")
    public void userShouldSeeOrderCompletionMessage() {
        LoggerUtils.info("Verifying order completion page");
        Assert.assertTrue(checkoutCompletePage.isOrderCompletionPageDisplayed(),
                "Failed: Order completion page is not displayed");
        Assert.assertTrue(checkoutCompletePage.isSuccessMessageDisplayed(),
                "Failed: Success message is not displayed");
        String completionMessage = checkoutCompletePage.getCompletionMessage();
        LoggerUtils.info("Order completion message: " + completionMessage);
    }
}