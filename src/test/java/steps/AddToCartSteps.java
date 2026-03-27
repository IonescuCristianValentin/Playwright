package steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.AddToCartPage;
import pages.LoginPage;
import utils.TestDataWriter;
import utils.UserData;
import base.Hooks;

import java.io.IOException;

public class AddToCartSteps {

    private LoginPage loginPage;
    private AddToCartPage addToCartPage;
    private UserData user;

    @Given("user is logged in")
    public void userIsLoggedIn() throws IOException {
        loginPage = new LoginPage(Hooks.page);
        addToCartPage = new AddToCartPage(Hooks.page);

        user = TestDataWriter.readUserData();

        loginPage.clickOnLoginIcon();
        loginPage.typeEmail(user.getEmail());
        loginPage.typePassword(user.getPassword());
        loginPage.clickOnRememberMe();
        loginPage.clickOnLoginButton();
    }

    @When("user adds a product to the cart")
    public void userAddsProductToCart() {
        addToCartPage.navigateToDesktops();
        addToCartPage.selectProduct();
        addToCartPage.addToCart();
    }

    @Then("the product should be added successfully")
    public void productShouldBeAddedSuccessfully() {
        Assert.assertTrue(Hooks.page.textContent(addToCartPage.notification)
                        .contains("The product has been added to your shopping cart"),
                "Product not added to cart");
    }

    @And("user goes to the cart page")
    public void userGoesToCartPage() {
        addToCartPage.goToCart();
        Assert.assertTrue(Hooks.page.url().contains("cart"), "Cart page not opened");
    }
}