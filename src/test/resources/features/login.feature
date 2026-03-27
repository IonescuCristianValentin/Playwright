Feature: User Login

  Scenario: User logs in
    Given user opens the login page
    When user logs in with valid credentials
    Then user should be logged in