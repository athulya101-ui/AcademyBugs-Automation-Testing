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

public class FindBugsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public FindBugsPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        );

        PageFactory.initElements(driver, this);
    }

    /*
     * Page heading
     */

    @FindBy(xpath =
        "//h1[contains(normalize-space(.),'Find Bugs')] "
        + "| //h2[contains(normalize-space(.),'Find Bugs')] "
        + "| //h3[contains(normalize-space(.),'Find Bugs')]")
    private List<WebElement> pageHeadings;

    /*
     * Page description
     */

    @FindBy(xpath =
        "//*[contains(normalize-space(.),"
        + "'Explore a practice test site')]")
    private List<WebElement> pageDescriptions;

    /*
     * View label
     */

    @FindBy(xpath =
        "//*[normalize-space()='View']")
    private List<WebElement> viewLabels;

    /*
     * Result information
     */

    @FindBy(xpath =
        "//*[contains(normalize-space(.),'Showing') "
        + "and contains(normalize-space(.),'results')]")
    private List<WebElement> resultInformation;

    /*
     * Corrected product-name locator
     *
     * This finds headings only inside product containers.
     */

    @FindBy(xpath =
        "//*[self::h2 or self::h3 or self::h4]"
        + "[ancestor::*[contains("
        + "translate(@class,"
        + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
        + "'abcdefghijklmnopqrstuvwxyz'),"
        + "'product')]]")
    private List<WebElement> productNames;

    /*
     * Product prices
     */

    @FindBy(xpath =
        "//*[contains("
        + "translate(@class,"
        + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
        + "'abcdefghijklmnopqrstuvwxyz'),"
        + "'price')]")
    private List<WebElement> productPrices;

    /*
     * Add to Cart buttons
     */

    @FindBy(xpath =
        "//button[contains("
        + "translate(normalize-space(.),"
        + "'abcdefghijklmnopqrstuvwxyz',"
        + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),"
        + "'ADD TO CART')] "
        + "| //a[contains("
        + "translate(normalize-space(.),"
        + "'abcdefghijklmnopqrstuvwxyz',"
        + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),"
        + "'ADD TO CART')]")
    private List<WebElement> addToCartButtons;

    /*
     * Select Options buttons
     */

    @FindBy(xpath =
        "//button[contains("
        + "translate(normalize-space(.),"
        + "'abcdefghijklmnopqrstuvwxyz',"
        + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),"
        + "'SELECT OPTIONS')] "
        + "| //a[contains("
        + "translate(normalize-space(.),"
        + "'abcdefghijklmnopqrstuvwxyz',"
        + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),"
        + "'SELECT OPTIONS')]")
    private List<WebElement> selectOptionsButtons;

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
                ExpectedConditions.urlContains(
                    "/find-bugs"
                )
            );

            return driver.getCurrentUrl()
                         .contains("/find-bugs");

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isPageHeadingDisplayed() {

        return isAnyElementDisplayed(
            pageHeadings
        );
    }

    public boolean isPageDescriptionDisplayed() {

        return isAnyElementDisplayed(
            pageDescriptions
        );
    }

    /*
     * View section methods
     */

    public boolean isViewLabelDisplayed() {

        return isAnyElementDisplayed(
            viewLabels
        );
    }

    public boolean isView10TextPresent() {

        return isTextDisplayedOnPage("10");
    }

    public boolean isView25TextPresent() {

        return isTextDisplayedOnPage("25");
    }

    public boolean isView50TextPresent() {

        return isTextDisplayedOnPage("50");
    }

    public boolean areAllViewValuesPresent() {

        return isView10TextPresent()
            && isView25TextPresent()
            && isView50TextPresent();
    }

    /*
     * Product methods
     */

    public int getProductNameCount() {

        return countValidProductNames();
    }

    public boolean areProductsDisplayed() {

        return getProductNameCount() > 0;
    }

    public String getFirstProductName() {

        for (WebElement product : productNames) {

            try {

                if (!product.isDisplayed()) {
                    continue;
                }

                String name =
                    product.getText().trim();

                if (isValidProductName(name)) {

                    return name;
                }

            } catch (Exception e) {

                // Continue checking remaining elements
            }
        }

        return "";
    }

    public boolean isFirstProductNameNotEmpty() {

        return !getFirstProductName()
            .isBlank();
    }

    public int getProductPriceCount() {

        return countDisplayedElements(
            productPrices
        );
    }

    public boolean areProductPricesDisplayed() {

        return getProductPriceCount() > 0;
    }

    /*
     * Result information methods
     */

    public boolean isResultInformationDisplayed() {

        return isAnyElementDisplayed(
            resultInformation
        );
    }

    public String getResultInformationText() {

        for (WebElement element : resultInformation) {

            try {

                if (element.isDisplayed()) {

                    return element
                        .getText()
                        .trim();
                }

            } catch (Exception e) {

                // Continue checking
            }
        }

        return "";
    }

    public boolean doesResultInformationContainShowing() {

        return getResultInformationText()
            .contains("Showing");
    }

    /*
     * Add to Cart button methods
     */

    public int getAddToCartButtonCount() {

        return countDisplayedElements(
            addToCartButtons
        );
    }

    public boolean areAddToCartButtonsDisplayed() {

        return getAddToCartButtonCount() > 0;
    }

    public void clickFirstAddToCartButton() {

        WebElement button =
            getFirstDisplayedElement(
                addToCartButtons
            );

        if (button == null) {

            throw new IllegalStateException(
                "No Add to Cart button was found."
            );
        }

        scrollToElement(button);

        try {

            wait.until(
                ExpectedConditions
                    .elementToBeClickable(button)
            );

            button.click();

        } catch (Exception e) {

            JavascriptExecutor js =
                (JavascriptExecutor) driver;

            js.executeScript(
                "arguments[0].click();",
                button
            );
        }
    }

    public boolean isCartSuccessMessageDisplayed() {

        try {

            return wait.until(driver -> {

                List<WebElement> messages =
                    driver.findElements(
                        By.xpath(
                            "//*[contains("
                            + "translate(normalize-space(.),"
                            + "'abcdefghijklmnopqrstuvwxyz',"
                            + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),"
                            + "'ADDED TO YOUR CART')] "
                            + "| //*[contains("
                            + "translate(normalize-space(.),"
                            + "'abcdefghijklmnopqrstuvwxyz',"
                            + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),"
                            + "'SUCCESSFULLY ADDED')] "
                            + "| //*[contains("
                            + "translate(normalize-space(.),"
                            + "'abcdefghijklmnopqrstuvwxyz',"
                            + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),"
                            + "'SHOPPING CART')]"
                        )
                    );

                return isAnyElementDisplayed(
                    messages
                );
            });

        } catch (Exception e) {

            return false;
        }
    }

    /*
     * Select Options methods
     */

    public int getSelectOptionsButtonCount() {

        return countDisplayedElements(
            selectOptionsButtons
        );
    }

    public boolean areSelectOptionsButtonsDisplayed() {

        return getSelectOptionsButtonCount() > 0;
    }

    /*
     * Utility methods
     */

    private boolean isTextDisplayedOnPage(
            String text) {

        try {

            WebElement body =
                driver.findElement(
                    By.tagName("body")
                );

            String pageText =
                body.getText();

            return pageText.contains(text);

        } catch (Exception e) {

            return false;
        }
    }

    private boolean isValidProductName(
            String name) {

        if (name == null || name.isBlank()) {

            return false;
        }

        return !name.equalsIgnoreCase(
                    "AcademyBugs.com"
               )
            && !name.equalsIgnoreCase(
                    "Find Bugs"
               )
            && !name.equalsIgnoreCase(
                    "Examples of Bugs"
               )
            && !name.equalsIgnoreCase(
                    "Types of Bugs"
               )
            && !name.equalsIgnoreCase(
                    "Report Bugs"
               );
    }

    private int countValidProductNames() {

        int count = 0;

        for (WebElement product : productNames) {

            try {

                if (product.isDisplayed()) {

                    String name =
                        product.getText().trim();

                    if (isValidProductName(name)) {

                        count++;
                    }
                }

            } catch (Exception e) {

                // Continue checking
            }
        }

        return count;
    }

    private WebElement getFirstDisplayedElement(
            List<WebElement> elements) {

        for (WebElement element : elements) {

            try {

                if (element.isDisplayed()) {

                    return element;
                }

            } catch (Exception e) {

                // Continue checking
            }
        }

        return null;
    }

    private boolean isAnyElementDisplayed(
            List<WebElement> elements) {

        return getFirstDisplayedElement(
            elements
        ) != null;
    }

    private int countDisplayedElements(
            List<WebElement> elements) {

        int count = 0;

        for (WebElement element : elements) {

            try {

                if (element.isDisplayed()) {

                    count++;
                }

            } catch (Exception e) {

                // Continue checking
            }
        }

        return count;
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