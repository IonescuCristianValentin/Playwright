Feature: User Registration

  Scenario: Register a new user successfully
    Given user is on the registration page
    When user fills registration form and submits
    Then user should be registered successfully
    And user logs out