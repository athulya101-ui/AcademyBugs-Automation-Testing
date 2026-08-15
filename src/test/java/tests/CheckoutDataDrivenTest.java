package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import utilities.CheckoutDataProvider;

public class CheckoutDataDrivenTest
        extends BaseTest {

    @Test(
        dataProvider = "checkoutData",
        dataProviderClass =
            CheckoutDataProvider.class
    )
    public void checkoutUsingExcel(

        String testCaseID,
        String country,
        String firstName,
        String lastName,
        String company,
        String address,
        String city,
        String state,
        String zipCode,
        String phone) {

        System.out.println(
            "Running: " + testCaseID
        );

        System.out.println(
            "Excel Data:"
        );

        System.out.println(
            firstName + " "
            + lastName
        );

        System.out.println(address);

        System.out.println(city);

        System.out.println(zipCode);

        System.out.println(phone);

        /*
         * Use existing CartPage
         */

        CartPage cartPage =
            new CartPage(driver);

        cartPage.addProductAndOpenCart();

        /*
         * You need this method
         * inside CartPage:
         *
         * clickCheckout()
         */

        cartPage.clickCheckout();

        CheckoutPage checkoutPage =
            new CheckoutPage(driver);

        checkoutPage.enterCheckoutDetails(
            country,
            firstName,
            lastName,
            company,
            address,
            city,
            state,
            zipCode,
            phone
        );

        System.out.println(
            "Excel data entered successfully."
        );

        checkoutPage
            .clickContinueToShipping();
    }
}