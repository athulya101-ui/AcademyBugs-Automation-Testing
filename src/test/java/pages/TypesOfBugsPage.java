package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TypesOfBugsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public TypesOfBugsPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        );

        PageFactory.initElements(driver, this);
    }

    /*
     * Page elements
     */

    @FindBy(xpath =
        "//h1[contains(normalize-space(.),'Types of Bugs')] "
        + "| //h2[contains(normalize-space(.),'Types of Bugs')] "
        + "| //h3[contains(normalize-space(.),'Types of Bugs')]")
    private List<WebElement> pageHeadings;

    @FindBy(xpath =
        "//*[normalize-space()='Functional']")
    private List<WebElement> functionalBugElements;

    @FindBy(xpath =
        "//*[normalize-space()='Visual']")
    private List<WebElement> visualBugElements;

    @FindBy(xpath =
        "//*[normalize-space()='Content']")
    private List<WebElement> contentBugElements;

    @FindBy(xpath =
        "//*[normalize-space()='Performance']")
    private List<WebElement> performanceBugElements;

    @FindBy(xpath =
        "//*[normalize-space()='Crash']")
    private List<WebElement> crashBugElements;

    @FindBy(xpath =
        "//*[contains(normalize-space(.),"
        + "'Workflow failures producing an unexpected')]")
    private List<WebElement> functionalDescriptions;

    @FindBy(xpath =
        "//*[contains(normalize-space(.),"
        + "'Visual issues affect the layout')]")
    private List<WebElement> visualDescriptions;

    @FindBy(xpath =
        "//*[contains(normalize-space(.),"
        + "'Content issues affect the text')]")
    private List<WebElement> contentDescriptions;

    @FindBy(xpath =
        "//*[contains(normalize-space(.),"
        + "'Problematic slowness')]")
    private List<WebElement> performanceDescriptions;

    @FindBy(xpath =
        "//*[contains(normalize-space(.),"
        + "'Application quits or closes unexpectedly')]")
    private List<WebElement> crashDescriptions;

    /*
     * Basic page methods
     */

    public String getPageTitle() {

        return driver.getTitle();
    }

    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }

    public boolean isPageLoaded() {

        try {

            wait.until(
                ExpectedConditions.urlContains("/types")
            );

            return driver.getCurrentUrl()
                         .contains("/types");

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isPageHeadingDisplayed() {

        return isAnyElementDisplayed(pageHeadings);
    }

    /*
     * Bug category verification methods
     */

    public boolean isFunctionalBugDisplayed() {

        return isAnyElementDisplayed(
            functionalBugElements
        );
    }

    public boolean isVisualBugDisplayed() {

        return isAnyElementDisplayed(
            visualBugElements
        );
    }

    public boolean isContentBugDisplayed() {

        return isAnyElementDisplayed(
            contentBugElements
        );
    }

    public boolean isPerformanceBugDisplayed() {

        return isAnyElementDisplayed(
            performanceBugElements
        );
    }

    public boolean isCrashBugDisplayed() {

        return isAnyElementDisplayed(
            crashBugElements
        );
    }

    public boolean areAllBugTypesDisplayed() {

        return isFunctionalBugDisplayed()
            && isVisualBugDisplayed()
            && isContentBugDisplayed()
            && isPerformanceBugDisplayed()
            && isCrashBugDisplayed();
    }

    /*
     * Description verification methods
     */

    public boolean isFunctionalDescriptionDisplayed() {

        return isAnyElementDisplayed(
            functionalDescriptions
        );
    }

    public boolean isVisualDescriptionDisplayed() {

        return isAnyElementDisplayed(
            visualDescriptions
        );
    }

    public boolean isContentDescriptionDisplayed() {

        return isAnyElementDisplayed(
            contentDescriptions
        );
    }

    public boolean isPerformanceDescriptionDisplayed() {

        return isAnyElementDisplayed(
            performanceDescriptions
        );
    }

    public boolean isCrashDescriptionDisplayed() {

        return isAnyElementDisplayed(
            crashDescriptions
        );
    }

    public boolean areAllDescriptionsDisplayed() {

        return isFunctionalDescriptionDisplayed()
            && isVisualDescriptionDisplayed()
            && isContentDescriptionDisplayed()
            && isPerformanceDescriptionDisplayed()
            && isCrashDescriptionDisplayed();
    }

    /*
     * Common utility method
     */

    private boolean isAnyElementDisplayed(
            List<WebElement> elements) {

        for (WebElement element : elements) {

            try {

                if (element.isDisplayed()) {

                    return true;
                }

            } catch (Exception e) {

                // Continue checking other matching elements
            }
        }

        return false;
    }
}