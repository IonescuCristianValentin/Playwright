package steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.RegisterPage;
import utils.TestDataWriter;
import utils.UserData;
import base.Hooks;

import java.io.IOException;

public class RegisterSteps {

    private RegisterPage registerPage;
    private final String firstName = "Ionescu";
    private final String lastName = "Cristian";
    private final String password = "DemoWebShop";
    private String uniqueEmail;

    @Given("user is on the registration page")
    public void userIsOnRegistrationPage() {
        registerPage = new RegisterPage(Hooks.page);
        Assert.assertTrue(Hooks.page.title().contains("Demo Web Shop"), "Page title is not as expected");
        registerPage.clickRegisterLink();
    }

    @When("user fills registration form and submits")
    public void userFillsRegistrationFormAndSubmits() throws IOException {
        registerPage.selectMaleGender();
        registerPage.typeFirstName(firstName);
        registerPage.typeLastName(lastName);

        uniqueEmail = "automation" + System.currentTimeMillis() + "@example.com";
        registerPage.typeEmail(uniqueEmail);
        registerPage.typePassword(password);
        registerPage.typeConfirmPassword(password);

        registerPage.clickRegisterButton();
    }

    @Then("user should be registered successfully")
    public void userShouldBeRegisteredSuccessfully() throws IOException {
        Assert.assertTrue(registerPage.getRegistrationResultText().contains("Your registration completed"));
        Assert.assertTrue(registerPage.isRegistrationSuccessUrl());

        UserData user = new UserData(uniqueEmail, password);
        TestDataWriter.saveUserData(user);
    }

    @And("user logs out")
    public void userLogsOut() {
        registerPage.clickContinueButton();
        registerPage.clickLogout();
    }
}