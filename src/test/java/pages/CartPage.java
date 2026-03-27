package pages;

import com.microsoft.playwright.Page;

public class CartPage {

    private Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    // locators
    private final String cartLink = ".cart-label";
    private final String termsOfServiceCheckbox = "#termsofservice";
    private final String checkoutButton = "#checkout";

    public void clickCart() {
        page.click(cartLink);
    }

    public void acceptTermsOfService() {
        page.locator(termsOfServiceCheckbox).check();
    }

    public void clickCheckout() {
        page.click(checkoutButton);
    }
}