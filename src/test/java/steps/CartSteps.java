package steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.AddToCartPage;
import pages.CartPage;
import pages.LoginPage;
import utils.TestDataWriter;
import utils.UserData;
import base.Hooks;

import java.io.IOException;

public class CartSteps {

    private LoginPage loginPage;
    private AddToCartPage addToCartPage;
    private CartPage cartPage;
    private UserData user;

    @Given("user is logged in and has a product in the cart")
    public void userIsLoggedInAndHasProductInCart() throws IOException {
        loginPage = new LoginPage(Hooks.page);
        addToCartPage = new AddToCartPage(Hooks.page);
        cartPage = new CartPage(Hooks.page);

        user = TestDataWriter.readUserData();

        // Login
        loginPage.clickOnLoginIcon();
        loginPage.typeEmail(user.getEmail());
        loginPage.typePassword(user.getPassword());
        loginPage.clickOnRememberMe();
        loginPage.clickOnLoginButton();

        // Add product to cart
        addToCartPage.navigateToDesktops();
        addToCartPage.selectProduct();
        addToCartPage.addToCart();
    }

    @When("user goes to the cart page and accepts terms")
    public void userGoesToCartAndAcceptsTerms() {
        addToCartPage.goToCart();
        cartPage.clickCart();
        cartPage.acceptTermsOfService();
    }

    @And("user proceeds to checkout")
    public void userProceedsToCheckout() {
        cartPage.clickCheckout();
    }

    @Then("checkout page should be opened")
    public void checkoutPageShouldBeOpened() {
        Assert.assertTrue(Hooks.page.url().contains("onepagecheckout"),
                "Checkout page not opened");
    }
}