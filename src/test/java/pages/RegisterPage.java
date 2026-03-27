package pages;

import com.microsoft.playwright.Page;

public class RegisterPage {

    private Page page;

    public RegisterPage(Page page) {
        this.page = page;
    }

    private final String registerLink = ".ico-register";
    private final String genderMale = "#gender-male";
    private final String firstName = "#FirstName";
    private final String lastName = "#LastName";
    private final String email = "#Email";
    private final String password = "#Password";
    private final String confirmPassword = "#ConfirmPassword";
    private final String registerButton = "#register-button";
    private final String continueButton = ".register-continue-button";
    private final String logoutButton = ".ico-logout";

    public void clickRegisterLink() {
        page.click(registerLink);
    }

    public void selectMaleGender() {
        page.check(genderMale);
    }

    public void typeFirstName(String name) {
        page.fill(firstName, name);
    }

    public void typeLastName(String name) {
        page.fill(lastName, name);
    }

    public void typeEmail(String userEmail) {
        page.fill(email, userEmail);
    }

    public void typePassword(String pass) {
        page.fill(password, pass);
    }

    public void typeConfirmPassword(String pass) {
        page.fill(confirmPassword, pass);
    }

    public void clickRegisterButton() {
        page.click(registerButton);
    }

    public void clickContinueButton() {
        page.click(continueButton);
    }

    public void clickLogout() {
        page.click(logoutButton);
    }

    public String getRegistrationResultText() {
        return page.textContent(".result");
    }

    public boolean isRegistrationSuccessUrl() {
        return page.url().contains("registerresult/1");

    }
}
