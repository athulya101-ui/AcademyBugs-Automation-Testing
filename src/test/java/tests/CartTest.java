package tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;

public class CartTest extends BaseTest {
    private CartPage cartPage;
    private static final String VALID_GIFT_CARD = System.getProperty("giftCardCode", "");

    @BeforeMethod
    public void initializeCartPage() {
        cartPage = new CartPage(driver);
    }

    @Test(priority = 1, description = "TC-CART-005")
    public void verifyProductQuantityCanBeUpdatedBeyondTwo() {
        cartPage.addProductAndOpenCart();
        cartPage.updateProductQuantity(3);
        Assert.assertEquals(cartPage.getProductQuantity(), 3);
    }

    @Test(priority = 2, description = "TC-CART-012")
    public void verifyGrandTotalCalculation() {
        cartPage.addProductAndOpenCart();
        double expected = cartPage.getCartSubtotal() + cartPage.getShippingCharge();
        Assert.assertEquals(cartPage.getGrandTotal(), expected, 0.01, "BUG FOUND: Grand Total is incorrect.");
    }

    @Test(priority = 3, description = "TC-CART-013 and TC-CART-014")
    public void verifyEmptyCartMessageAfterRemovingProduct() {
        cartPage.addProductAndOpenCart();
        Assert.assertTrue(cartPage.attemptToRemoveFirstProduct(), "BUG FOUND: Product could not be removed.");
        Assert.assertTrue(cartPage.isEmptyCartDisplayed(), "Empty-cart message was not displayed.");
    }

    @Test(priority = 4, description = "TC-CART-016")
    public void verifyContinueShopping() {
        cartPage.addProductAndOpenCart();
        cartPage.clickContinueShopping();
        Assert.assertFalse(driver.getCurrentUrl().contains("my-cart"));
    }

    @Test(priority = 5, description = "TC-CART-018")
    public void verifyInvalidCouponCodeIsRejected() {
        cartPage.addProductAndOpenCart();
        if (!cartPage.areCouponControlsDisplayed()) throw new SkipException("Coupon controls are unavailable.");
        double before = cartPage.getGrandTotal();
        cartPage.applyCoupon("INVALID123");
        Assert.assertEquals(cartPage.getGrandTotal(), before, 0.01);
        Assert.assertFalse(cartPage.getDisplayedMessage().isBlank());
    }

    @Test(priority = 6, description = "TC-CART-020")
    public void verifyValidGiftCardRedemption() {
        cartPage.addProductAndOpenCart();
        if (!cartPage.areGiftCardControlsDisplayed()) throw new SkipException("Gift-card controls are unavailable.");
        if (VALID_GIFT_CARD.isBlank()) throw new SkipException("Run with -DgiftCardCode=YOUR_VALID_CODE");
        double before = cartPage.getGrandTotal();
        cartPage.redeemGiftCard(VALID_GIFT_CARD);
        Assert.assertTrue(cartPage.getGrandTotal() < before);
    }

    @Test(priority = 7, description = "TC-CART-022 and TC-CART-043")
    public void verifyCurrencySelectorUpdatesCartAmounts() {
        cartPage.addProductAndOpenCart();
        if (!cartPage.isCurrencyDropdownDisplayed()) throw new SkipException("Currency selector is unavailable.");
        int quantityBefore = cartPage.getProductQuantity();
        double totalBefore = cartPage.getGrandTotal();
        cartPage.selectCurrency("EUR");
        Assert.assertEquals(cartPage.getSelectedCurrency(), "EUR");
        Assert.assertEquals(cartPage.getProductQuantity(), quantityBefore);
        Assert.assertNotEquals(cartPage.getGrandTotal(), totalBefore, "BUG FOUND: Currency changed but amounts did not update.");
    }

    @Test(priority = 8)
    public void verifyCartDoesNotDisplayUnwantedSymbols() {
        cartPage.addProductAndOpenCart();
        Assert.assertFalse(cartPage.pageContainsUnwantedSymbols());
    }

    @Test(priority = 9, description = "TC-CART-023")
    public void verifyProductSearchFromCartPage() {
        cartPage.addProductAndOpenCart();
        Assert.assertTrue(cartPage.isProductSearchDisplayed());
        cartPage.searchProduct("Shoes");
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("shoes"));
    }

    @Test(priority = 10, description = "TC-CART-024")
    public void verifyHotItemFunctionality() {
        cartPage.addProductAndOpenCart();
        Assert.assertTrue(cartPage.isHotItemDisplayed());
        Assert.assertFalse(cartPage.getHotItemName().isBlank());
        cartPage.clickHotItem();
        Assert.assertFalse(driver.getCurrentUrl().contains("my-cart"));
    }

    @Test(priority = 11)
    public void verifyStoreMenuDisplayed() {
        cartPage.addProductAndOpenCart();
        Assert.assertTrue(cartPage.isStoreMenuDisplayed());
        Assert.assertTrue(cartPage.getStoreMenuLinkCount() > 0);
    }

    @Test(priority = 12)
    public void verifyAllItemsStoreMenuOption() {
        cartPage.addProductAndOpenCart();
        cartPage.clickStoreMenuOption("All Items");
        Assert.assertFalse(driver.getCurrentUrl().contains("my-cart"));
    }

    @Test(priority = 13)
    public void verifyFilterByPriceDisplayed() {
        cartPage.addProductAndOpenCart();
        Assert.assertTrue(cartPage.isFilterByPriceDisplayed());
        Assert.assertTrue(cartPage.getPriceFilterCount() > 0);
    }

    @Test(priority = 14)
    public void verifyFirstPriceFilterFunctionality() {
        cartPage.addProductAndOpenCart();
        Assert.assertFalse(cartPage.clickFirstPriceFilter().isBlank());
    }

    @Test(priority = 15)
    public void verifyCreateAccountControlsDisplayed() {
        cartPage.addProductAndOpenCart();
        Assert.assertTrue(cartPage.areCreateAccountControlsDisplayed());
    }

    @Test(priority = 16)
    public void verifyCreateAccountWithValidData() {
        cartPage.addProductAndOpenCart();
        if (!cartPage.areCreateAccountControlsDisplayed()) throw new SkipException("Create Account controls are unavailable.");
        String email = "athulya" + System.currentTimeMillis() + "@testmail.com";
        cartPage.createAccount(email, "Test@12345");
        Assert.assertFalse(cartPage.getAccountResponse().isBlank());
    }

    @Test(priority = 17)
    public void verifyCreateAccountWithBlankFields() {
        cartPage.addProductAndOpenCart();
        if (!cartPage.areCreateAccountControlsDisplayed()) throw new SkipException("Create Account controls are unavailable.");
        cartPage.createAccount("", "");
        Assert.assertFalse(cartPage.getAccountResponse().isBlank());
    }
}
