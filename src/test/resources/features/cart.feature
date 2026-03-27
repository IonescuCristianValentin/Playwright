Feature: Cart Page and Checkout

  Scenario: Go to cart and proceed to checkout
    Given user is logged in and has a product in the cart
    When user goes to the cart page and accepts terms
    And user proceeds to checkout
    Then checkout page should be opened