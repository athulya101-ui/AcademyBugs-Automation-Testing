package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.Basepage;

public class CommonFeaturesTest extends BaseTest {

    private Basepage page;

    @BeforeMethod
    public void openHomePage() {

        driver.get(
            "https://academybugs.com/"
        );

        page = new Basepage(driver);

        page.waitForPageToLoad();
        page.closeCookiePopup();
        page.closeTourTip();
    }

    @Test(priority = 1)
    public void verifyCommonNavigation() {

        Assert.assertTrue(
            page.isExamplesLinkDisplayed(),
            "Examples of Bugs link is not displayed."
        );

        page.clickExamples();

        Assert.assertTrue(
            driver.getCurrentUrl()
                  .contains("academybugs.com"),
            "Examples of Bugs page did not open."
        );

        driver.get(
            "https://academybugs.com/"
        );

        page = new Basepage(driver);

        page.clickTypes();

        Assert.assertTrue(
            driver.getCurrentUrl()
                  .contains("/types"),
            "Types of Bugs page did not open."
        );

        driver.get(
            "https://academybugs.com/"
        );

        page = new Basepage(driver);

        page.clickFindBugs();

        Assert.assertTrue(
            driver.getCurrentUrl()
                  .contains("/find-bugs"),
            "Find Bugs page did not open."
        );

        driver.get(
            "https://academybugs.com/"
        );

        page = new Basepage(driver);

        page.clickReportBugs();

        Assert.assertTrue(
            driver.getCurrentUrl()
                  .contains("/report"),
            "Report Bugs page did not open."
        );
    }

    @Test(priority = 2)
    public void verifyLogoDisplayed() {

        Assert.assertTrue(
            page.isLogoDisplayed(),
            "Website logo is not displayed."
        );
    }

    @Test(priority = 3)
    public void verifyLogoRedirectsToHomePage() {

        page.clickLogo();

        Assert.assertTrue(
            driver.getCurrentUrl()
                  .equals("https://academybugs.com/")
                || driver.getCurrentUrl()
                         .equals("https://academybugs.com"),
            "Logo did not redirect to the home page."
        );
    }

    @Test(priority = 4)
    public void verifyTermsLinkDisplayed() {

        Assert.assertTrue(
            page.isTermsDisplayed(),
            "Terms link is not displayed."
        );
    }

    @Test(priority = 5)
    public void verifyPrivacyPolicyDisplayed() {

        Assert.assertTrue(
            page.isPrivacyPolicyDisplayed(),
            "Privacy Policy link is not displayed."
        );
    }

    @Test(priority = 6)
    public void verifyTermsLinkNavigation() {

        page.clickTerms();

        Assert.assertTrue(
            driver.getCurrentUrl()
                  .toLowerCase()
                  .contains("terms"),
            "Terms page did not open."
        );
    }

    @Test(priority = 7)
    public void verifyPrivacyPolicyNavigation() {

        page.clickPrivacyPolicy();

        Assert.assertTrue(
            driver.getCurrentUrl()
                  .toLowerCase()
                  .contains("privacy"),
            "Privacy Policy page did not open."
        );
    }
}