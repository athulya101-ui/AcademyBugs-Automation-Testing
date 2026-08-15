package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ExamplesOfBugsPage;

public class ExamplesOfBugsTest extends BaseTest {

    private ExamplesOfBugsPage examplesPage;

    @BeforeMethod
    public void openExamplesOfBugsPage() {

        driver.get("https://academybugs.com/");

        examplesPage =
            new ExamplesOfBugsPage(driver);
    }

    @Test(priority = 1)
    public void verifyExamplesPageLoaded() {

        Assert.assertTrue(
            examplesPage.isPageLoaded(),
            "Examples of Bugs page did not load."
        );
    }

    @Test(priority = 2)
    public void verifyExamplesPageTitle() {

        String actualTitle =
            examplesPage.getPageTitle();

        System.out.println(
            "Page title: " + actualTitle
        );

        Assert.assertFalse(
            actualTitle == null
                || actualTitle.isBlank(),
            "Examples of Bugs page title is empty."
        );

        Assert.assertTrue(
            actualTitle.contains("AcademyBugs"),
            "Incorrect page title: " + actualTitle
        );
    }

    @Test(priority = 3)
    public void verifyPageHeadingDisplayed() {

        Assert.assertTrue(
            examplesPage.isHeadingDisplayed(),
            "No page heading is displayed."
        );
    }

    @Test(priority = 4)
    public void verifyBugExampleHeadingsDisplayed() {

        int headingCount =
            examplesPage.getHeadingCount();

        System.out.println(
            "Number of headings: " + headingCount
        );

        Assert.assertTrue(
            headingCount > 0,
            "No bug-example headings were found."
        );
    }

    @Test(priority = 5)
    public void verifySocialShareBugDisplayed() {

        Assert.assertTrue(
            examplesPage.isSocialShareBugDisplayed(),
            "Social Share bug example is not displayed."
        );
    }

    @Test(priority = 6)
    public void verifyVideoPlayerBugDisplayed() {

        Assert.assertTrue(
            examplesPage.isVideoPlayerBugDisplayed(),
            "Video Player bug example is not displayed."
        );
    }

    @Test(priority = 7)
    public void verifySearchButtonBugDisplayed() {

        Assert.assertTrue(
            examplesPage.isSearchButtonBugDisplayed(),
            "Search Button bug example is not displayed."
        );
    }

    @Test(priority = 8)
    public void verifySocialShareBugIsClickable() {

        Assert.assertTrue(
            examplesPage.isSocialShareBugClickable(),
            "Social Share bug card is not clickable."
        );
    }

    @Test(priority = 9)
    public void verifySocialShareBugCanBeOpened() {

        examplesPage.clickSocialShareBug();

        Assert.assertTrue(
            examplesPage.isBugDetailsDisplayed(),
            "Bug details popup was not displayed."
        );
    }
}