Feature: SauceDemo Login Feature from Database.

  @LoginDB
  Scenario: Verify user login
    Given User is on the login page
    And User logs in with DB table all rows