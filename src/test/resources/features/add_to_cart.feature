Feature: Add Product to Cart

  Scenario: Add a product to the shopping cart
    Given user is logged in
    When user adds a product to the cart
    Then the product should be added successfully
    And user goes to the cart page