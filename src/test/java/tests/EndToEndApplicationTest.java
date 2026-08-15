package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;

public class EndToEndApplicationTest extends BaseTest {

    @Test
    public void verifyCompleteCustomerWorkflow() {

        System.out.println(
            "\n===================================="
        );
        System.out.println(
            "STARTING END TO END TEST"
        );
        System.out.println(
            "===================================="
        );


        // STEP 1 - Application

        driver.get(
            "https://academybugs.com/"
        );

        Assert.assertTrue(
            driver.getCurrentUrl()
                  .contains("academybugs.com")
        );

        System.out.println(
            "STEP 1 PASS: Application launched."
        );


        // STEP 2 - Find Bugs

        CartPage cartPage =
            new CartPage(driver);

        cartPage.openFindBugsPage();

        Assert.assertTrue(
            driver.getCurrentUrl()
                  .contains("find-bugs")
        );

        System.out.println(
            "STEP 2 PASS: Find Bugs opened."
        );


        // STEP 3 - Cart

        cartPage.addFirstProductToCart();

        Assert.assertTrue(
            cartPage.isCartPageLoaded()
        );

        System.out.println(
            "STEP 3 PASS: Product added to cart."
        );


        // STEP 4 - Quantity

        cartPage.updateProductQuantity(2);

        Assert.assertEquals(
            cartPage.getProductQuantity(),
            2
        );

        System.out.println(
            "STEP 4 PASS: Quantity updated."
        );


        // STEP 5 - Checkout

        cartPage.clickCheckout();

        System.out.println(
            "STEP 5 PASS: Checkout opened."
        );


        // STEP 6 - Valid Austrian details

        CheckoutPage checkoutPage =
            new CheckoutPage(driver);

        checkoutPage.enterCheckoutDetails(
        	    "Austria",
        	    "Athulya",
        	    "Sasi",
        	    "",
        	    "Hauptplatz 1",
        	    "St. Pölten",
        	    "Lower Austria",
        	    "3100",
        	    "06641234567",
        	    "athulya.test@example.com",
        	    "athulya.test@example.com"
        	);
        System.out.println(
            "STEP 6 PASS: Checkout details entered."
        );


        // STEP 7 - Shipping

        checkoutPage.clickContinueToShipping();

        Assert.assertTrue(
            checkoutPage.isShippingPageDisplayed(),
            "Actual Shipping page did not open."
        );

        System.out.println(
            "STEP 7 PASS: Actual Shipping page opened."
        );


        System.out.println(
            "\n===================================="
        );

        System.out.println(
            "E2E FLOW SUCCESSFUL UP TO SHIPPING"
        );

        System.out.println(
            "Application → Product → Cart "
            + "→ Checkout → Shipping"
        );

        System.out.println(
            "===================================="
        );
    }
}