package com.sourav.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    // Web Elements - Fixed selectors for SauceDemo cart page
    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    // Product names inside cart items (corrected selector)
    @FindBy(css = ".cart_item .inventory_item_name")
    private List<WebElement> productNames;

    // Product prices inside cart items
    @FindBy(css = ".cart_item .inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    // Remove buttons for each cart item
    @FindBy(css = ".cart_item .item_pricebar button")
    private List<WebElement> removeButtons;

    // Constructor
    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public int getCartItemCount() {
        return cartItems.size();
    }

    public List<String> getProductNamesInCart() {
        return extractText(productNames);
    }

    public List<String> getProductPricesInCart() {
        return extractText(productPrices);
    }

    public void removeProductFromCart(int itemIndex) {
        if (itemIndex > 0 && itemIndex <= removeButtons.size()) {
            click(removeButtons.get(itemIndex - 1));
        }
    }

    public void clickCheckoutButton() {
        click(checkoutButton);
    }

    public boolean isCheckoutButtonDisplayed() {
        return isElementDisplayed(checkoutButton);
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    public String getProductPrice(String productName) {
        List<String> names = getProductNamesInCart();
        List<String> prices = getProductPricesInCart();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(productName)) {
                return prices.get(i);
            }
        }
        return null;
    }

    public boolean isProductInCart(String productName) {
        return getProductNamesInCart().stream()
                .anyMatch(name -> name.equalsIgnoreCase(productName));
    }

    /**
     * Helper method to extract text from a list of WebElements
     */
    private List<String> extractText(List<WebElement> elements) {
        if (elements == null || elements.isEmpty()) {
            return new ArrayList<>();
        }
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}