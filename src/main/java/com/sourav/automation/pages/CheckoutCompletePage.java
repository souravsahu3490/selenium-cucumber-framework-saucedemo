package com.sourav.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {

    // Web Elements
    @FindBy(css = ".complete-header")
    private WebElement completeHeader;

    @FindBy(css = ".complete-text")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    @FindBy(css = ".pony_express")
    private WebElement ponyExpressImage;

    // Constructor
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public String getCompletionHeader() {
        return getText(completeHeader);
    }

    public String getCompletionMessage() {
        return getText(completeText);
    }

    public void clickBackToProductsButton() {
        click(backToProductsButton);
    }

    public boolean isOrderCompletionPageDisplayed() {
        return isElementDisplayed(completeHeader);
    }

    public boolean isSuccessMessageDisplayed() {
        String header = getCompletionHeader();
        return header != null && header.contains("Thank you");
    }

    public boolean isBackButtonDisplayed() {
        return isElementDisplayed(backToProductsButton);
    }
}