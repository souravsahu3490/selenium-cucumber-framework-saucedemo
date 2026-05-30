package com.sourav.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    // Web Elements
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(css = ".checkout_info")
    private WebElement checkoutInfoContainer;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public void enterFirstName(String firstName) {
        sendKeys(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        sendKeys(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {
        sendKeys(postalCodeField, postalCode);
    }

    public void fillCheckoutDetails(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    public void clickContinueButton() {
        click(continueButton);
    }

    public void clickCancelButton() {
        click(cancelButton);
    }

    public boolean isCheckoutFormDisplayed() {
        return isElementDisplayed(checkoutInfoContainer);
    }

    public String getFirstNameFieldValue() {
        return getAttribute(firstNameField, "value");
    }

    public String getLastNameFieldValue() {
        return getAttribute(lastNameField, "value");
    }

    public String getPostalCodeFieldValue() {
        return getAttribute(postalCodeField, "value");
    }

    public boolean isFirstNameFieldFilled() {
        return !getFirstNameFieldValue().isEmpty();
    }

    public boolean isContinueButtonEnabled() {
        return isElementDisplayed(continueButton);
    }
}