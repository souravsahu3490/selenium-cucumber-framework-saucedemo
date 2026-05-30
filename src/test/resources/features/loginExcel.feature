Feature: SauceDemo Login Feature from Excel.

  @LoginExcel
  Scenario Outline: Verify user login
    Given User is on the login page
    And User reads login data from excel row <rowNum>
    When User logs into application
    Then Login should be successful for excel row <rowNum>

    Examples:
      | rowNum |
      | 1 |
      | 2 |
      | 3 |