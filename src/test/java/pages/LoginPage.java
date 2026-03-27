package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage {

    private Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    private final String loginIcon = "a.ico-login";
    private final String emailField = "#Email";
    private final String passwordField = "#Password";
    private final String rememberMeCheckbox = "#RememberMe";
    private final String loginButton = "input.button-1.login-button";

    // --- Login actions ---
    public void clickOnLoginIcon() {
        page.click(loginIcon);
    }

    public void typeEmail(String email) {
        page.locator(emailField).fill(email);
    }

    public void typePassword(String password) {
        page.fill(passwordField, password);
    }

    public void clickOnRememberMe() {
        page.locator(rememberMeCheckbox).check();
    }

    public void clickOnLoginButton() {
        page.locator(loginButton)
                .waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        page.locator(loginButton).click();
    }

    // --- Get logged-in account email ---
    public String getAccountEmailText(String expectedEmail) {
        Locator emailLocator = page.locator("a.account")
                .filter(new Locator.FilterOptions().setHasText(expectedEmail))
                .first();
        emailLocator.waitFor(new Locator.WaitForOptions()
                .setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        return emailLocator.textContent().trim();
    }
}