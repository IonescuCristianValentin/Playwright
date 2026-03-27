package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class CheckoutPage {

    private Page page;

    public CheckoutPage(Page page) {
        this.page = page;
    }

    // locators
    private final String billingCountry = "#BillingNewAddress_CountryId";
    private final String billingCity = "#BillingNewAddress_City";
    private final String billingAddress1 = "#BillingNewAddress_Address1";
    private final String billingZip = "#BillingNewAddress_ZipPostalCode";
    private final String billingPhone = "#BillingNewAddress_PhoneNumber";

    private final String billingContinueBtn = "input.button-1.new-address-next-step-button[onclick='Billing.save()']";
    private final String shippingContinueBtn = "input.button-1.new-address-next-step-button[onclick='Shipping.save()']";
    private final String shippingMethodContinueBtn = "input.button-1.shipping-method-next-step-button[onclick='ShippingMethod.save()']";
    private final String paymentMethodContinueBtn = "input.button-1.payment-method-next-step-button[onclick='PaymentMethod.save()']";
    private final String paymentInfoContinueBtn = "input.button-1.payment-info-next-step-button[onclick='PaymentInfo.save()']";
    private final String confirmOrderBtn = "input.button-1.confirm-order-next-step-button[onclick='ConfirmOrder.save()']";

    private final String orderConfirmation = "div.title strong";

    // --- Billing ---
    public void fillBillingAddress(String country, String city, String address, String zipCode, String phone) {
        // Așteaptă dropdown-ul să fie vizibil
        page.locator(billingCountry)
                .waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));
        page.selectOption(billingCountry, country);

        page.fill(billingCity, city);
        page.fill(billingAddress1, address);
        page.fill(billingZip, zipCode);
        page.fill(billingPhone, phone);

        clickBillingContinue();
    }

    public void clickBillingContinue() {
        page.locator(billingContinueBtn)
                .waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));
        page.locator(billingContinueBtn).scrollIntoViewIfNeeded();
        page.locator(billingContinueBtn).click();
    }

    // --- Shipping ---
    public void continueFromShippingAddress() {
        page.locator(shippingContinueBtn)
                .waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));
        page.locator(shippingContinueBtn).scrollIntoViewIfNeeded();
        page.locator(shippingContinueBtn).click();
    }

    public void continueFromShippingMethod() {
        page.locator(shippingMethodContinueBtn)
                .waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));
        page.locator(shippingMethodContinueBtn).scrollIntoViewIfNeeded();
        page.locator(shippingMethodContinueBtn).click();
    }

    // --- Payment ---
    public void continueFromPaymentMethod() {
        page.locator(paymentMethodContinueBtn)
                .waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));
        page.locator(paymentMethodContinueBtn).scrollIntoViewIfNeeded();
        page.locator(paymentMethodContinueBtn).click();
    }

    public void continueFromPaymentInfo() {
        page.locator(paymentInfoContinueBtn)
                .waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));
        page.locator(paymentInfoContinueBtn).scrollIntoViewIfNeeded();
        page.locator(paymentInfoContinueBtn).click();
    }

    // --- Confirm Order ---
    public void confirmOrder() {
        page.locator(confirmOrderBtn)
                .waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));
        page.locator(confirmOrderBtn).scrollIntoViewIfNeeded();
        page.locator(confirmOrderBtn).click();
    }

    // --- Get Order Confirmation ---
    public String getOrderConfirmationText() {
        return page.textContent(orderConfirmation);
    }
}