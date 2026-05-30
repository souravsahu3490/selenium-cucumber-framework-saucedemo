@LoginExamples
Feature: Data-Driven Login

  @DataDrivenLogin
  Scenario Outline: Login with different user credentials
    Given User is on the login page
    When User enters username "<Username>"
    And User enters password "<Password>"
    And User clicks login button
    Then Login result should be "<ExpectedResult>" for user type "<UserType>"

    Examples:
      | Username        | Password       | UserType    | ExpectedResult |
      | standard_user   | secret_sauce   | valid       | success        |
      | problem_user    | secret_sauce   | valid       | success        |
      | invalid_user    | secret_sauce   | invalid     | fail           |
      | standard_user   | wrong_password | invalid_pwd | fail           |
      | locked_out_user | secret_sauce   | locked      | fail           |