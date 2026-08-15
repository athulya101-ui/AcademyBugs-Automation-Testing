package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.TypesOfBugsPage;

public class TypesOfBugsTest extends BaseTest {

    private TypesOfBugsPage typesPage;

    @BeforeMethod
    public void openTypesOfBugsPage() {

        driver.get(
            "https://academybugs.com/types/"
        );

        typesPage =
            new TypesOfBugsPage(driver);
    }

    @Test(priority = 1)
    public void verifyTypesPageLoaded() {

        Assert.assertTrue(
            typesPage.isPageLoaded(),
            "Types of Bugs page did not load."
        );
    }

    @Test(priority = 2)
    public void verifyTypesPageTitle() {

        String actualTitle =
            typesPage.getPageTitle();

        System.out.println(
            "Types page title: " + actualTitle
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
            actualTitle.contains("Types of Bugs")
                || actualTitle.contains("AcademyBugs"),
            "Incorrect page title: " + actualTitle
        );
    }

    @Test(priority = 3)
    public void verifyTypesPageUrl() {

        Assert.assertTrue(
            typesPage.getCurrentUrl()
                     .contains("/types"),
            "Incorrect Types page URL."
        );
    }

    @Test(priority = 4)
    public void verifyTypesPageHeadingDisplayed() {

        Assert.assertTrue(
            typesPage.isPageHeadingDisplayed(),
            "Types of Bugs heading is not displayed."
        );
    }

    @Test(priority = 5)
    public void verifyFunctionalBugDisplayed() {

        Assert.assertTrue(
            typesPage.isFunctionalBugDisplayed(),
            "Functional bug category is not displayed."
        );
    }

    @Test(priority = 6)
    public void verifyVisualBugDisplayed() {

        Assert.assertTrue(
            typesPage.isVisualBugDisplayed(),
            "Visual bug category is not displayed."
        );
    }

    @Test(priority = 7)
    public void verifyContentBugDisplayed() {

        Assert.assertTrue(
            typesPage.isContentBugDisplayed(),
            "Content bug category is not displayed."
        );
    }

    @Test(priority = 8)
    public void verifyPerformanceBugDisplayed() {

        Assert.assertTrue(
            typesPage.isPerformanceBugDisplayed(),
            "Performance bug category is not displayed."
        );
    }

    @Test(priority = 9)
    public void verifyCrashBugDisplayed() {

        Assert.assertTrue(
            typesPage.isCrashBugDisplayed(),
            "Crash bug category is not displayed."
        );
    }

    @Test(priority = 10)
    public void verifyAllBugTypesDisplayed() {

        Assert.assertTrue(
            typesPage.areAllBugTypesDisplayed(),
            "One or more bug categories are missing."
        );
    }

    @Test(priority = 11)
    public void verifyFunctionalDescriptionDisplayed() {

        Assert.assertTrue(
            typesPage.isFunctionalDescriptionDisplayed(),
            "Functional bug description is missing."
        );
    }

    @Test(priority = 12)
    public void verifyVisualDescriptionDisplayed() {

        Assert.assertTrue(
            typesPage.isVisualDescriptionDisplayed(),
            "Visual bug description is missing."
        );
    }

    @Test(priority = 13)
    public void verifyContentDescriptionDisplayed() {

        Assert.assertTrue(
            typesPage.isContentDescriptionDisplayed(),
            "Content bug description is missing."
        );
    }

    @Test(priority = 14)
    public void verifyPerformanceDescriptionDisplayed() {

        Assert.assertTrue(
            typesPage.isPerformanceDescriptionDisplayed(),
            "Performance bug description is missing."
        );
    }

    @Test(priority = 15)
    public void verifyCrashDescriptionDisplayed() {

        Assert.assertTrue(
            typesPage.isCrashDescriptionDisplayed(),
            "Crash bug description is missing."
        );
    }

    @Test(priority = 16)
    public void verifyAllDescriptionsDisplayed() {

        Assert.assertTrue(
            typesPage.areAllDescriptionsDisplayed(),
            "One or more bug descriptions are missing."
        );
    }
}