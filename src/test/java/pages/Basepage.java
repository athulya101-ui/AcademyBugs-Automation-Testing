package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Basepage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By examplesOfBugsLink = By.xpath(
        "//a[contains(normalize-space(.),'Examples of Bugs')]"
    );

    private By typesOfBugsLink = By.xpath(
        "//a[contains(normalize-space(.),'Types of Bugs')]"
    );

    private By findBugsLink = By.xpath(
        "//a[contains(normalize-space(.),'Find Bugs')]"
    );

    private By reportBugsLink = By.xpath(
        "//a[contains(normalize-space(.),'Report Bugs')]"
    );

    private By logo = By.xpath(
        "//a[contains(@class,'custom-logo-link')]"
        + " | //a[contains(@href,'academybugs.com')]"
        + "[contains(normalize-space(.),'AcademyBugs')]"
    );

    private By termsLink = By.xpath(
        "//a[contains(normalize-space(.),'Terms')]"
    );

    private By privacyPolicyLink = By.xpath(
        "//a[contains(normalize-space(.),'Privacy Policy')]"
    );

    public Basepage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(20)
        );
    }

    public void waitForPageToLoad() {

        wait.until(currentDriver -> {

            JavascriptExecutor js =
                (JavascriptExecutor) currentDriver;

            return js.executeScript(
                "return document.readyState"
            ).equals("complete");
        });

        wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.tagName("body")
            )
        );
    }

    public void closeCookiePopup() {

        List<By> cookieButtons = List.of(

            By.xpath(
                "//button[contains(normalize-space(.),'Accept cookies')]"
            ),

            By.xpath(
                "//a[contains(normalize-space(.),'Accept cookies')]"
            ),

            By.xpath(
                "//*[contains(@class,'cookie')]"
                + "//button[contains(normalize-space(.),'Accept')]"
            )
        );

        for (By locator : cookieButtons) {

            try {

                List<WebElement> buttons =
                    driver.findElements(locator);

                for (WebElement button : buttons) {

                    if (button.isDisplayed()) {

                        ((JavascriptExecutor) driver)
                            .executeScript(
                                "arguments[0].click();",
                                button
                            );

                        return;
                    }
                }

            } catch (Exception e) {

                // Continue checking other cookie buttons
            }
        }
    }

    public void closeTourTip() {

        try {

            List<WebElement> closeButtons =
                driver.findElements(
                    By.xpath(
                        "//*[contains(@id,'TourTip')]"
                        + "//*[self::button or self::a]"
                    )
                );

            for (WebElement button : closeButtons) {

                if (button.isDisplayed()) {

                    ((JavascriptExecutor) driver)
                        .executeScript(
                            "arguments[0].click();",
                            button
                        );

                    return;
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Tour tip close button not available."
            );
        }

        try {

            JavascriptExecutor js =
                (JavascriptExecutor) driver;

            js.executeScript(
                "var elements = document.querySelectorAll("
                + "'[id*=\"TourTip\"],"
                + "[class*=\"TourTip\"],"
                + "[class*=\"tour-tip\"],"
                + "[class*=\"tour\"]');"
                + "elements.forEach(function(element){"
                + "element.style.display='none';"
                + "});"
            );

        } catch (Exception e) {

            System.out.println(
                "Tour tip not displayed."
            );
        }
    }

    private WebElement waitForClickable(
            By locator) {

        waitForPageToLoad();

        closeCookiePopup();
        closeTourTip();

        WebElement element = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                locator
            )
        );

        scrollToElement(element);

        return wait.until(
            ExpectedConditions.elementToBeClickable(
                locator
            )
        );
    }

    private void clickElement(By locator) {

        WebElement element =
            waitForClickable(locator);

        try {

            element.click();

        } catch (Exception e) {

            ((JavascriptExecutor) driver)
                .executeScript(
                    "arguments[0].click();",
                    element
                );
        }

        waitForPageToLoad();
    }

    private void scrollToElement(
            WebElement element) {

        ((JavascriptExecutor) driver)
            .executeScript(
                "arguments[0].scrollIntoView("
                + "{block:'center'});",
                element
            );
    }

    public void clickExamples() {

        clickElement(examplesOfBugsLink);
    }

    public void clickTypes() {

        clickElement(typesOfBugsLink);
    }

    public void clickFindBugs() {

        clickElement(findBugsLink);
    }

    public void clickReportBugs() {

        clickElement(reportBugsLink);
    }

    public void clickLogo() {

        clickElement(logo);
    }

    public void clickTerms() {

        clickElement(termsLink);
    }

    public void clickPrivacyPolicy() {

        clickElement(privacyPolicyLink);
    }

    public boolean isExamplesLinkDisplayed() {

        return isElementDisplayed(
            examplesOfBugsLink
        );
    }

    public boolean isTypesLinkDisplayed() {

        return isElementDisplayed(
            typesOfBugsLink
        );
    }

    public boolean isFindBugsLinkDisplayed() {

        return isElementDisplayed(
            findBugsLink
        );
    }

    public boolean isReportBugsLinkDisplayed() {

        return isElementDisplayed(
            reportBugsLink
        );
    }

    public boolean isLogoDisplayed() {

        return isElementDisplayed(logo);
    }

    public boolean isTermsDisplayed() {

        return isElementDisplayed(termsLink);
    }

    public boolean isPrivacyPolicyDisplayed() {

        return isElementDisplayed(
            privacyPolicyLink
        );
    }

    private boolean isElementDisplayed(
            By locator) {

        try {

            waitForPageToLoad();

            WebElement element = wait.until(
                ExpectedConditions
                    .visibilityOfElementLocated(
                        locator
                    )
            );

            return element.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }
}