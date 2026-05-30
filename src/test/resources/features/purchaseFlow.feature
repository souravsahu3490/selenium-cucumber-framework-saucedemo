@PurchaseFlow @RegressionTest
Feature: Complete Purchase Flow on SauceDemo

  Background:
    Given User is on the login page
    When User enters username "standard_user"
    And User enters password "secret_sauce"
    And User clicks login button
    Then User should be logged in successfully

  @SmokeTest @Positive @Purchase
  Scenario: Complete purchase flow with two products
    Given User is on the products page
    When User adds the first product to cart
    And User adds the second product to cart
    Then Product count in cart should be 2
    When User navigates to cart
    Then User should see 2 items in cart
    When User clicks checkout button
    And User fills checkout details with first name "John", last name "Doe", postal code "12345"
    And User clicks continue button
    Then User should see order summary
    When User clicks finish button
    Then User should see order completion message

  @Positive @try
  Scenario: Verify product details before checkout
    Given User is on the products page
    When User adds the first product to cart
    And User adds the second product to cart
    And User navigates to cart
    Then User should see product names in cart
    And User should see product prices in cart

  @Positive
  Scenario: Remove product from cart and continue checkout
    Given User is on the products page
    When User adds the first product to cart
    And User adds the second product to cart
    And User navigates to cart
    And User removes the first product from cart
    Then Product count in cart should be 1
    When User clicks checkout button
    And User fills checkout details with first name "Jane", last name "Smith", postal code "67890"
    And User clicks continue button
    Then User should see order summary with 1 item
    When User clicks finish button
    Then User should see order completion message