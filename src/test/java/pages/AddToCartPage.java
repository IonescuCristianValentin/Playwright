package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddToCartPage {

    private Page page;

    public AddToCartPage(Page page) {
        this.page = page;
    }

    // locators
    private final String computersMenu = "a:has-text('Computers')";
    private final String desktopSubmenu = "a:has-text('Desktops')";
    private final String productTitle = "h2.product-title:has-text('Build your own expensive computer')";
    private final String addToCartButton = "#add-to-cart-button-74"; // folosește ID direct
    public final String notification = "#bar-notification";
    private final String cartLink = ".cart-label";

    public void navigateToDesktops() {
        page.hover(computersMenu);
        page.click(desktopSubmenu);
    }

    public void selectProduct() {
        page.click(productTitle);
    }

    public void addToCart() {
        page.locator(addToCartButton)
                .waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                        .setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        page.locator(addToCartButton).click();

        page.waitForSelector(notification, new com.microsoft.playwright.Page.WaitForSelectorOptions()
                .setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
    }

    public void goToCart() {
        page.waitForSelector(notification, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
        page.click(cartLink);
    }
}