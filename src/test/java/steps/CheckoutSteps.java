package steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.AddToCartPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import utils.TestDataWriter;
import utils.UserData;
import base.Hooks;

import java.io.IOException;

public class CheckoutSteps {

    private LoginPage loginPage;
    private AddToCartPage addToCartPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private UserData user;

    @Given("user is logged in and has a product in the cart for checkout")
    public void userIsLoggedInAndHasProductInCart() throws IOException {
        loginPage = new LoginPage(Hooks.page);
        addToCartPage = new AddToCartPage(Hooks.page);
        cartPage = new CartPage(Hooks.page);
        checkoutPage = new CheckoutPage(Hooks.page);

        user = TestDataWriter.readUserData();

        // --- Login ---
        loginPage.clickOnLoginIcon();
        loginPage.typeEmail(user.getEmail());
        loginPage.typePassword(user.getPassword());
        loginPage.clickOnRememberMe();
        loginPage.clickOnLoginButton();

        // --- Add product to cart ---
        addToCartPage.navigateToDesktops();
        addToCartPage.selectProduct();
        addToCartPage.addToCart();
        addToCartPage.goToCart();
    }

    @When("user accepts terms and proceeds to checkout")
    public void userAcceptsTermsAndProceeds() {
        cartPage.acceptTermsOfService();
        cartPage.clickCheckout();
    }

    @And("user completes the checkout process")
    public void userCompletesCheckout() {
        checkoutPage.fillBillingAddress(
                "United States",
                "Birmingham",
                "1234 Elm Street",
                "35203",
                "205-555-1234"
        );
        checkoutPage.continueFromShippingAddress();
        checkoutPage.continueFromShippingMethod();
        checkoutPage.continueFromPaymentMethod();
        checkoutPage.continueFromPaymentInfo();
        checkoutPage.confirmOrder();
    }

    @Then("order should be successfully processed")
    public void orderShouldBeSuccessfullyProcessed() {
        Assert.assertTrue(
                checkoutPage.getOrderConfirmationText()
                        .contains("Your order has been successfully processed!"),
                "Order not confirmed"
        );
    }
}