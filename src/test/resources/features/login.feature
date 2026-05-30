@Login @RegressionTest
Feature: User Login Functionality

  Background:
    Given User is on the login page

  @SmokeTest @Positive
  Scenario: Successful login with valid credentials
    When User enters username "standard_user"
    And User enters password "secret_sauce"
    And User clicks login button
    Then User should be logged in successfully

  @Negative
  Scenario: Login fails with invalid email
    When User enters username "invalidemail"
    And User enters password "anypassword"
    And User clicks login button
    Then User should see error message

  @Negative
  Scenario: Login fails with invalid password
    When User enters username "validuser@example.com"
    And User enters password "wrongpassword"
    And User clicks login button
    Then User should see error message

  @Negative
  Scenario: Login fails with empty credentials
    When User clicks login button
    Then User should see error message