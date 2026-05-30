package com.sourav.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    // Web Elements
    @FindBy(css = ".cart_item")
    private List<WebElement> orderItems;

    @FindBy(css = ".summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(css = ".summary_tax_label")
    private WebElement taxLabel;

    @FindBy(css = ".summary_total_label")
    private WebElement totalLabel;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(css = ".checkout_summary_container")
    private WebElement summaryContainer;

    // Constructor
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public int getOrderItemCount() {
        return orderItems.size();
    }

    public String getSubtotal() {
        return getText(subtotalLabel);
    }

    public String getTax() {
        return getText(taxLabel);
    }

    public String getTotal() {
        return getText(totalLabel);
    }

    public void clickFinishButton() {
        click(finishButton);
    }

    public void clickCancelButton() {
        click(cancelButton);
    }

    public boolean isOrderSummaryDisplayed() {
        return isElementDisplayed(summaryContainer);
    }

    public boolean isFinishButtonDisplayed() {
        return isElementDisplayed(finishButton);
    }

    public String extractTotalPrice() {
        String totalText = getTotal();
        // Extract price value from "Total: $XX.XX"
        return totalText.replaceAll("[^0-9.]", "");
    }
}