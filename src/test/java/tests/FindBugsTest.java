package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.FindBugsPage;

public class FindBugsTest extends BaseTest {

    private FindBugsPage findBugsPage;

    @BeforeMethod
    public void openFindBugsPage() {

        driver.get(
            "https://academybugs.com/find-bugs/"
        );

        findBugsPage =
            new FindBugsPage(driver);
    }

    @Test(priority = 1)
    public void verifyFindBugsPageLoaded() {

        Assert.assertTrue(
            findBugsPage.isPageLoaded(),
            "Find Bugs page did not load."
        );
    }

    @Test(priority = 2)
    public void verifyFindBugsPageTitle() {

        String actualTitle =
            findBugsPage.getPageTitle();

        System.out.println(
            "Find Bugs page title: "
            + actualTitle
        );

        Assert.assertNotNull(
            actualTitle,
            "Page title is null."
        );

        Assert.assertFalse(
            actualTitle.isBlank(),
            "Page title is empty."
        );

        Assert.assertTrue(
            actualTitle.contains("Find Bugs")
                || actualTitle.contains(
                    "AcademyBugs"
                ),
            "Incorrect page title: "
                + actualTitle
        );
    }

    @Test(priority = 3)
    public void verifyFindBugsPageUrl() {

        String actualUrl =
            findBugsPage.getCurrentUrl();

        System.out.println(
            "Find Bugs URL: "
            + actualUrl
        );

        Assert.assertTrue(
            actualUrl.contains("/find-bugs"),
            "Incorrect Find Bugs page URL: "
                + actualUrl
        );
    }

    @Test(priority = 4)
    public void verifyFindBugsHeadingDisplayed() {

        Assert.assertTrue(
            findBugsPage
                .isPageHeadingDisplayed(),
            "Find Bugs page heading is not displayed."
        );
    }

    @Test(priority = 5)
    public void verifyPageDescriptionDisplayed() {

        Assert.assertTrue(
            findBugsPage
                .isPageDescriptionDisplayed(),
            "Find Bugs page description is not displayed."
        );
    }

    @Test(priority = 6)
    public void verifyViewLabelDisplayed() {

        Assert.assertTrue(
            findBugsPage
                .isViewLabelDisplayed(),
            "View label is not displayed."
        );
    }

    @Test(priority = 7)
    public void verifyView10TextPresent() {

        Assert.assertTrue(
            findBugsPage
                .isView10TextPresent(),
            "View value 10 is not present."
        );
    }

    @Test(priority = 8)
    public void verifyView25TextPresent() {

        Assert.assertTrue(
            findBugsPage
                .isView25TextPresent(),
            "View value 25 is not present."
        );
    }

    @Test(priority = 9)
    public void verifyView50TextPresent() {

        Assert.assertTrue(
            findBugsPage
                .isView50TextPresent(),
            "View value 50 is not present."
        );
    }

    @Test(priority = 10)
    public void verifyAllViewValuesPresent() {

        Assert.assertTrue(
            findBugsPage
                .areAllViewValuesPresent(),
            "One or more View values are missing."
        );
    }

    @Test(priority = 11)
    public void verifyProductsDisplayed() {

        int productCount =
            findBugsPage
                .getProductNameCount();

        System.out.println(
            "Displayed product count: "
            + productCount
        );

        Assert.assertTrue(
            productCount > 0,
            "No product names are displayed."
        );
    }

    @Test(priority = 12)
    public void verifyFirstProductNameDisplayed() {

        String firstProductName =
            findBugsPage
                .getFirstProductName();

        System.out.println(
            "First product name: "
            + firstProductName
        );

        Assert.assertFalse(
            firstProductName.isBlank(),
            "First product name is empty."
        );

        Assert.assertNotEquals(
            firstProductName,
            "AcademyBugs.com",
            "Website name was incorrectly identified as a product."
        );

        Assert.assertNotEquals(
            firstProductName,
            "Find Bugs",
            "Page heading was incorrectly identified as a product."
        );
    }

    @Test(priority = 13)
    public void verifyProductPricesDisplayed() {

        int priceCount =
            findBugsPage
                .getProductPriceCount();

        System.out.println(
            "Displayed price count: "
            + priceCount
        );

        Assert.assertTrue(
            priceCount > 0,
            "Product prices are not displayed."
        );
    }

    @Test(priority = 14)
    public void verifyResultInformationDisplayed() {

        Assert.assertTrue(
            findBugsPage
                .isResultInformationDisplayed(),
            "Result information is not displayed."
        );
    }

    @Test(priority = 15)
    public void verifyResultInformationContainsShowing() {

        String resultText =
            findBugsPage
                .getResultInformationText();

        System.out.println(
            "Result information: "
            + resultText
        );

        Assert.assertFalse(
            resultText.isBlank(),
            "Result information is empty."
        );

        Assert.assertTrue(
            resultText.contains("Showing"),
            "Result information does not contain Showing."
        );
    }

    @Test(priority = 16)
    public void verifyAddToCartButtonsDisplayed() {

        int buttonCount =
            findBugsPage
                .getAddToCartButtonCount();

        System.out.println(
            "Add to Cart button count: "
            + buttonCount
        );

        Assert.assertTrue(
            buttonCount > 0,
            "Add to Cart buttons are not displayed."
        );
    }

    @Test(priority = 17)
    public void verifySelectOptionsButtonsDisplayed() {

        int buttonCount =
            findBugsPage
                .getSelectOptionsButtonCount();

        System.out.println(
            "Select Options button count: "
            + buttonCount
        );

        Assert.assertTrue(
            buttonCount > 0,
            "Select Options buttons are not displayed."
        );
    }

    @Test(priority = 18)
    public void verifyFirstProductCanBeAddedToCart() {

        findBugsPage
            .clickFirstAddToCartButton();

        Assert.assertTrue(
            findBugsPage
                .isCartSuccessMessageDisplayed(),
            "Cart success message was not displayed."
        );
    }
}