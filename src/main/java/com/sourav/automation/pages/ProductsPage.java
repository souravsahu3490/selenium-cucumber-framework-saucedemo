package com.sourav.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ProductsPage extends BasePage {

    // Web Elements
    @FindBy(className = "inventory_item")
    private List<WebElement> productsList;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = "a.shopping_cart_link")
    private WebElement cartLink;

    @FindBy(css = ".inventory_list .inventory_item:nth-child(1) button")
    private WebElement firstProductAddToCartBtn;

    @FindBy(css = ".inventory_list .inventory_item:nth-child(2) button")
    private WebElement secondProductAddToCartBtn;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    // Constructor
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public void addFirstProductToCart() {
        click(firstProductAddToCartBtn);
    }

    public void addSecondProductToCart() {
        click(secondProductAddToCartBtn);
    }

    public void addProductToCart(int productIndex) {
        if (productIndex > 0 && productIndex <= productsList.size()) {
            WebElement product = productsList.get(productIndex - 1);
            WebElement addBtn = product.findElement(By.tagName("button"));
            click(addBtn);
        }
    }

    public int getCartItemCount() {
        try {
            String count = getText(cartBadge);
            return Integer.parseInt(count);
        } catch (Exception e) {
            return 0;
        }
    }

    public void navigateToCart() {
        click(cartLink);
    }

    public boolean isProductsPageDisplayed() {
        return isElementDisplayed(cartLink);
    }

    public List<WebElement> getAllProducts() {
        return productsList;
    }

    public void sortProducts(String sortOption) {
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        click(sortDropdown);
        WebElement option = driver.findElement(By.xpath("//option[text()='" + sortOption + "']"));
        click(option);
    }

    public void logout() throws InterruptedException {
        Thread.sleep(2000);
        menuButton.click();
        Thread.sleep(2000);
        logoutLink.click();
    }
}