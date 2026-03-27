Feature: Complete Checkout

  Scenario: Complete the checkout process
    Given user is logged in and has a product in the cart for checkout
    When user accepts terms and proceeds to checkout
    And user completes the checkout process
    Then order should be successfully processed