package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExamplesOfBugsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ExamplesOfBugsPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }

    /*
     * Locators
     */

    @FindBy(xpath = "//h1 | //h2 | //h3 | //h4")
    private List<WebElement> headings;

    @FindBy(xpath =
        "//*[contains("
        + "translate(normalize-space(.),"
        + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
        + "'abcdefghijklmnopqrstuvwxyz'),"
        + "'social share buttons')]")
    private List<WebElement> socialShareBugElements;

    @FindBy(xpath =
        "//*[contains("
        + "translate(normalize-space(.),"
        + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
        + "'abcdefghijklmnopqrstuvwxyz'),"
        + "'video player')]")
    private List<WebElement> videoPlayerBugElements;

    @FindBy(xpath =
        "//*[contains("
        + "translate(normalize-space(.),"
        + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
        + "'abcdefghijklmnopqrstuvwxyz'),"
        + "'search button')]")
    private List<WebElement> searchButtonBugElements;

    /*
     * Existing page methods
     */

    public String getPageTitle() {

        return driver.getTitle();
    }

    public boolean isPageLoaded() {

        wait.until(ExpectedConditions.urlContains("academybugs.com"));

        return driver.getCurrentUrl().contains("academybugs.com");
    }

    /*
     * Heading tests
     */

    public boolean isHeadingDisplayed() {

        for (WebElement heading : headings) {

            if (heading.isDisplayed()) {
                return true;
            }
        }

        return false;
    }

    public int getHeadingCount() {

        return headings.size();
    }

    /*
     * Bug example tests
     */

    public boolean isSocialShareBugDisplayed() {

        return isAnyElementDisplayed(socialShareBugElements);
    }

    public boolean isVideoPlayerBugDisplayed() {

        return isAnyElementDisplayed(videoPlayerBugElements);
    }

    public boolean isSearchButtonBugDisplayed() {

        return isAnyElementDisplayed(searchButtonBugElements);
    }

    public boolean isSocialShareBugClickable() {

        WebElement clickableElement =
            findClickableParent(socialShareBugElements);

        return clickableElement != null;
    }

    /*
     * Click Social Share bug example
     */

    public void clickSocialShareBug() {

        WebElement clickableElement =
            findClickableParent(socialShareBugElements);

        if (clickableElement == null) {

            throw new IllegalStateException(
                "Social Share bug card was not found or is not clickable."
            );
        }

        scrollToElement(clickableElement);

        try {

            wait.until(
                ExpectedConditions.elementToBeClickable(clickableElement)
            );

            clickableElement.click();

        } catch (Exception e) {

            JavascriptExecutor js =
                (JavascriptExecutor) driver;

            js.executeScript(
                "arguments[0].click();",
                clickableElement
            );
        }
    }

    /*
     * Verify bug popup/details
     */

    public boolean isBugDetailsDisplayed() {

        try {

            wait.until(driver -> {

                List<WebElement> details =
                    driver.findElements(
                        By.xpath(
                            "//*[contains(.,'Action Performed') "
                            + "or contains(.,'Expected Result') "
                            + "or contains(.,'Actual Result') "
                            + "or contains(.,'Bug Type')]"
                        )
                    );

                return isAnyElementDisplayed(details);
            });

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    /*
     * Utility methods
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

    private WebElement findClickableParent(
            List<WebElement> elements) {

        for (WebElement element : elements) {

            try {

                if (!element.isDisplayed()) {
                    continue;
                }

                String tagName =
                    element.getTagName();

                if (tagName.equalsIgnoreCase("a")
                        || tagName.equalsIgnoreCase("button")) {

                    return element;
                }

                List<WebElement> parentLinks =
                    element.findElements(
                        By.xpath(
                            "./ancestor::a[1] "
                            + "| ./ancestor::button[1]"
                        )
                    );

                if (!parentLinks.isEmpty()
                        && parentLinks.get(0).isDisplayed()) {

                    return parentLinks.get(0);
                }

            } catch (Exception e) {

                // Continue checking other matching elements
            }
        }

        return null;
    }

    private void scrollToElement(
            WebElement element) {

        JavascriptExecutor js =
            (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView("
            + "{block:'center'});",
            element
        );
    }
}