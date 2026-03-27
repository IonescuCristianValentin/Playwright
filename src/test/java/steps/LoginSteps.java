package steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.LoginPage;
import utils.TestDataWriter;
import utils.UserData;
import base.Hooks;

import java.io.IOException;

public class LoginSteps {

    LoginPage loginPage;
    String email;

    @Given("user opens the login page")
    public void openLoginPage() {
        loginPage = new LoginPage(Hooks.page);
        loginPage.clickOnLoginIcon();
    }

    @When("user logs in with valid credentials")
    public void login() throws IOException {
        UserData user = TestDataWriter.readUserData();
        email = user.getEmail();

        loginPage.typeEmail(email);
        loginPage.typePassword(user.getPassword());
        loginPage.clickOnRememberMe();
        loginPage.clickOnLoginButton();
    }

    @Then("user should be logged in")
    public void userShouldBeLoggedIn() {
        String actualEmail = loginPage.getAccountEmailText(email);
        Assert.assertEquals(actualEmail, email, "Email from UI does not match test data");
    }
}