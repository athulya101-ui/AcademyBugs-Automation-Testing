package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public CheckoutPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }


    // =====================================================
    // COUNTRY
    // =====================================================

    private final By countrySelect =
            By.xpath(
                "//select[" +
                "not(contains(@id,'currency'))" +
                " and (" +
                "contains(translate(@id," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'COUNTRY')" +
                " or contains(translate(@name," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'COUNTRY')" +
                ")" +
                "]"
            );


    // =====================================================
    // FIRST NAME
    // =====================================================

    private final By firstName =
            By.xpath(
                "//input[" +
                "contains(translate(@id," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'FIRST')" +
                " or contains(translate(@name," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'FIRST')" +
                "]"
            );


    // =====================================================
    // LAST NAME
    // =====================================================

    private final By lastName =
            By.xpath(
                "//input[" +
                "contains(translate(@id," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'LAST')" +
                " or contains(translate(@name," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'LAST')" +
                "]"
            );


    // =====================================================
    // COMPANY
    // =====================================================

    private final By company =
            By.xpath(
                "//input[" +
                "contains(translate(@id," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'COMPANY')" +
                " or contains(translate(@name," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'COMPANY')" +
                "]"
            );


    // =====================================================
    // ADDRESS
    // =====================================================

    private final By address =
            By.xpath(
                "//input[" +
                "contains(translate(@id," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ADDRESS')" +
                " or contains(translate(@name," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ADDRESS')" +
                "]"
            );


    // =====================================================
    // CITY
    // =====================================================

    private final By city =
            By.xpath(
                "//input[" +
                "contains(translate(@id," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CITY')" +
                " or contains(translate(@name," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CITY')" +
                "]"
            );


    // =====================================================
    // STATE
    // =====================================================

    private final By state =
            By.xpath(
                "//input[" +
                "contains(translate(@id," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'STATE')" +
                " or contains(translate(@name," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'STATE')" +
                " or contains(translate(@id," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'REGION')" +
                "]"
            );


    // =====================================================
    // ZIP CODE
    // =====================================================

    private final By zipCode =
            By.xpath(
                "//input[" +
                "contains(translate(@id," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ZIP')" +
                " or contains(translate(@name," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ZIP')" +
                " or contains(translate(@id," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'POSTAL')" +
                "]"
            );


    // =====================================================
    // PHONE
    // =====================================================

    private final By phone =
            By.xpath(
                "//input[" +
                "contains(translate(@id," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'PHONE')" +
                " or contains(translate(@name," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'PHONE')" +
                " or @type='tel'" +
                "]"
            );


    // =====================================================
    // EXACT CHECKOUT EMAIL LOCATORS
    // =====================================================

    private final By email =
            By.id("ec_contact_email");

    private final By retypeEmail =
            By.id("ec_contact_email_retype");


    // =====================================================
    // HELPER - VISIBLE ELEMENT
    // =====================================================

    private WebElement visible(By locator) {

        return wait.until(
            ExpectedConditions
                .visibilityOfElementLocated(locator)
        );
    }


    // =====================================================
    // HELPER - ENTER TEXT
    // =====================================================

    private void enter(
            By locator,
            String value,
            String fieldName) {

        WebElement field =
                visible(locator);

        field.click();

        field.sendKeys(
            Keys.chord(
                Keys.CONTROL,
                "a"
            )
        );

        field.sendKeys(value);

        System.out.println(
            fieldName
            + " entered: "
            + value
        );
    }


    // =====================================================
    // SELECT COUNTRY
    // =====================================================

    public void selectCountry(
            String country) {

        System.out.println(
            "Selecting country..."
        );

        List<WebElement> selects =
                driver.findElements(
                    countrySelect
                );

        if (selects.isEmpty()) {

            throw new NoSuchElementException(
                "Country dropdown not found."
            );
        }

        WebElement countryElement =
                selects.get(0);

        wait.until(
            ExpectedConditions
                .visibilityOf(
                    countryElement
                )
        );

        Select dropdown =
                new Select(
                    countryElement
                );

        dropdown.selectByVisibleText(
            country
        );

        System.out.println(
            "Country selected: "
            + country
        );
    }


    // =====================================================
    // ENTER EMAIL + RETYPE EMAIL
    // =====================================================

    private void enterEmailFields(
            String emailValue,
            String retypeEmailValue) {

        System.out.println(
            "Trying to enter Email fields..."
        );


        // -------------------------------------------------
        // EMAIL
        // -------------------------------------------------

        WebElement emailField =
                wait.until(
                    ExpectedConditions
                        .visibilityOfElementLocated(
                            email
                        )
                );

        emailField.click();

        emailField.sendKeys(
            Keys.chord(
                Keys.CONTROL,
                "a"
            )
        );

        emailField.sendKeys(
            emailValue
        );

        System.out.println(
            "Email entered: "
            + emailValue
        );


        // -------------------------------------------------
        // RETYPE EMAIL
        // -------------------------------------------------

        WebElement retypeField =
                wait.until(
                    ExpectedConditions
                        .visibilityOfElementLocated(
                            retypeEmail
                        )
                );

        retypeField.click();

        retypeField.sendKeys(
            Keys.chord(
                Keys.CONTROL,
                "a"
            )
        );

        retypeField.sendKeys(
            retypeEmailValue
        );

        System.out.println(
            "Retype Email entered: "
            + retypeEmailValue
        );


        // -------------------------------------------------
        // VERIFY EMAIL VALUES
        // -------------------------------------------------

        String actualEmail =
                emailField.getAttribute(
                    "value"
                );

        String actualRetypeEmail =
                retypeField.getAttribute(
                    "value"
                );


        if (!emailValue.equals(actualEmail)) {

            throw new AssertionError(
                "Email was not entered correctly. "
                + "Expected: "
                + emailValue
                + " Actual: "
                + actualEmail
            );
        }


        if (!retypeEmailValue.equals(
                actualRetypeEmail)) {

            throw new AssertionError(
                "Retype Email was not entered correctly. "
                + "Expected: "
                + retypeEmailValue
                + " Actual: "
                + actualRetypeEmail
            );
        }


        if (!actualEmail.equals(
                actualRetypeEmail)) {

            throw new AssertionError(
                "Email and Retype Email "
                + "do not match."
            );
        }


        System.out.println(
            "Email fields verified successfully."
        );
    }


    // =====================================================
    // ENTER COMPLETE CHECKOUT DETAILS
    // =====================================================

    public void enterCheckoutDetails(

            String countryValue,
            String first,
            String last,
            String companyValue,
            String addressValue,
            String cityValue,
            String stateValue,
            String zipValue,
            String phoneValue,
            String emailValue,
            String retypeEmailValue) {


        System.out.println(
            "Starting checkout data entry..."
        );


        // -------------------------------------------------
        // COUNTRY
        // -------------------------------------------------

        selectCountry(
            countryValue
        );


        // -------------------------------------------------
        // FIRST NAME
        // -------------------------------------------------

        enter(
            firstName,
            first,
            "First Name"
        );


        // -------------------------------------------------
        // LAST NAME
        // -------------------------------------------------

        enter(
            lastName,
            last,
            "Last Name"
        );


        // -------------------------------------------------
        // COMPANY - OPTIONAL
        // -------------------------------------------------

        if (companyValue != null
                && !companyValue.isBlank()) {

            try {

                enter(
                    company,
                    companyValue,
                    "Company"
                );

            } catch (Exception e) {

                System.out.println(
                    "Company field unavailable - skipped."
                );
            }

        } else {

            System.out.println(
                "Company blank - skipped."
            );
        }


        // -------------------------------------------------
        // ADDRESS
        // -------------------------------------------------

        enter(
            address,
            addressValue,
            "Address"
        );


        // -------------------------------------------------
        // CITY
        // -------------------------------------------------

        enter(
            city,
            cityValue,
            "City"
        );


        // -------------------------------------------------
        // STATE
        // -------------------------------------------------

        if (stateValue != null
                && !stateValue.isBlank()) {

            try {

                enter(
                    state,
                    stateValue,
                    "State"
                );

            } catch (Exception e) {

                System.out.println(
                    "State field unavailable - skipped."
                );
            }
        }


        // -------------------------------------------------
        // ZIP CODE
        // -------------------------------------------------

        enter(
            zipCode,
            zipValue,
            "Zip Code"
        );


        // -------------------------------------------------
        // PHONE
        // -------------------------------------------------

        enter(
            phone,
            phoneValue,
            "Phone"
        );


        // -------------------------------------------------
        // EMAIL + RETYPE EMAIL
        // -------------------------------------------------

        enterEmailFields(
            emailValue,
            retypeEmailValue
        );


        System.out.println(
            "ALL CHECKOUT DATA ENTERED."
        );
    }


    // =====================================================
    // CLICK CONTINUE TO SHIPPING
    // =====================================================

    public void clickContinueToShipping() {

        System.out.println(
            "Trying to continue to Shipping..."
        );


        By continueShipping =
                By.xpath(
                    "//input[contains(" +
                    "translate(@value," +
                    "'abcdefghijklmnopqrstuvwxyz'," +
                    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                    "'CONTINUE TO SHIPPING')]" +
                    " | " +
                    "//button[contains(" +
                    "translate(normalize-space(.)," +
                    "'abcdefghijklmnopqrstuvwxyz'," +
                    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                    "'CONTINUE TO SHIPPING')]"
                );


        WebElement button =
                wait.until(
                    ExpectedConditions
                        .elementToBeClickable(
                            continueShipping
                        )
                );


        String oldUrl =
                driver.getCurrentUrl();


        System.out.println(
            "Before Shipping URL: "
            + oldUrl
        );


        // Scroll to button

        ((JavascriptExecutor) driver)
            .executeScript(
                "arguments[0].scrollIntoView(" +
                "{block:'center'});",
                button
            );


        // Try normal click

        try {

            button.click();

        } catch (Exception e) {

            System.out.println(
                "Normal click failed. "
                + "Trying JavaScript click..."
            );

            ((JavascriptExecutor) driver)
                .executeScript(
                    "arguments[0].click();",
                    button
                );
        }


        System.out.println(
            "Continue to Shipping clicked."
        );


        // =================================================
        // WAIT FOR CHECKOUT PAGE TO CHANGE
        // =================================================

        try {

            wait.until(d -> {

                String currentUrl =
                        d.getCurrentUrl()
                         .toLowerCase();

                /*
                 * Successful transition when
                 * checkout_info disappears.
                 */

                return !currentUrl.contains(
                    "checkout_info"
                );
            });


            System.out.println(
                "Checkout successfully moved forward."
            );

            System.out.println(
                "Previous URL: "
                + oldUrl
            );

            System.out.println(
                "Current URL: "
                + driver.getCurrentUrl()
            );


        } catch (Exception e) {

            System.out.println(
                "FAILED TO LEAVE CHECKOUT DETAILS."
            );

            System.out.println(
                "Current URL: "
                + driver.getCurrentUrl()
            );


            printCheckoutErrors();


            throw new AssertionError(
                "Checkout validation prevented "
                + "navigation to the next stage."
            );
        }
    }


    // =====================================================
    // PRINT CHECKOUT ERRORS
    // =====================================================

    private void printCheckoutErrors() {

        System.out.println(
            "Checking checkout validation messages..."
        );


        // -------------------------------------------------
        // Known validation messages
        // -------------------------------------------------

        List<WebElement> validationMessages =
                driver.findElements(
                    By.xpath(
                        "//*[contains(" +
                        "normalize-space(.)," +
                        "'Please correct the errors')" +
                        " or contains(" +
                        "normalize-space(.)," +
                        "'Please enter a valid')" +
                        " or contains(" +
                        "normalize-space(.)," +
                        "'required field')" +
                        " or contains(" +
                        "normalize-space(.)," +
                        "'is required')]"
                    )
                );


        boolean foundError =
                false;


        for (WebElement message :
                validationMessages) {

            try {

                if (message.isDisplayed()) {

                    String text =
                            message
                                .getText()
                                .trim();


                    /*
                     * Avoid printing the entire page.
                     */
                    if (!text.isEmpty()
                            && text.length() < 300) {

                        System.out.println(
                            "VALIDATION: "
                            + text
                        );

                        foundError =
                                true;
                    }
                }

            } catch (Exception ignored) {
            }
        }


        // -------------------------------------------------
        // Print entered checkout values
        // -------------------------------------------------

        System.out.println(
            "----- CHECKOUT FIELD VALUES -----"
        );


        printFieldValue(
            firstName,
            "First Name"
        );

        printFieldValue(
            lastName,
            "Last Name"
        );

        printFieldValue(
            address,
            "Address"
        );

        printFieldValue(
            city,
            "City"
        );

        printFieldValue(
            state,
            "State"
        );

        printFieldValue(
            zipCode,
            "ZIP"
        );

        printFieldValue(
            phone,
            "Phone"
        );

        printFieldValue(
            email,
            "Email"
        );

        printFieldValue(
            retypeEmail,
            "Retype Email"
        );


        if (!foundError) {

            System.out.println(
                "No specific visible validation "
                + "message was detected."
            );
        }
    }


    // =====================================================
    // DEBUG FIELD VALUE
    // =====================================================

    private void printFieldValue(
            By locator,
            String fieldName) {

        try {

            List<WebElement> elements =
                    driver.findElements(
                        locator
                    );

            if (!elements.isEmpty()) {

                String value =
                        elements
                            .get(0)
                            .getAttribute(
                                "value"
                            );

                System.out.println(
                    fieldName
                    + " = "
                    + value
                );

            } else {

                System.out.println(
                    fieldName
                    + " = FIELD NOT FOUND"
                );
            }

        } catch (Exception e) {

            System.out.println(
                fieldName
                + " = Unable to read"
            );
        }
    }


    // =====================================================
    // VERIFY NEXT CHECKOUT STAGE
    // =====================================================

    public boolean isShippingPageDisplayed() {

        String currentUrl =
                driver.getCurrentUrl()
                      .toLowerCase();


        System.out.println(
            "Checking next checkout stage..."
        );

        System.out.println(
            "Current URL: "
            + currentUrl
        );


        // -------------------------------------------------
        // STILL ON CHECKOUT DETAILS
        // -------------------------------------------------

        if (currentUrl.contains(
                "checkout_info"
            )) {

            System.out.println(
                "Still on Checkout Details."
            );

            return false;
        }


        // -------------------------------------------------
        // GET PAGE TEXT
        // -------------------------------------------------

        String bodyText =
                driver.findElement(
                    By.tagName("body")
                )
                .getText()
                .toLowerCase();


        // -------------------------------------------------
        // SHIPPING PAGE
        // -------------------------------------------------

        if (currentUrl.contains(
                "shipping"
            )) {

            System.out.println(
                "Shipping page detected."
            );

            return true;
        }


        // -------------------------------------------------
        // PAYMENT PAGE
        // AcademyBugs may move directly toward payment.
        // -------------------------------------------------

        if (currentUrl.contains(
                "payment"
            )
            || bodyText.contains(
                "submit payment"
            )
            || bodyText.contains(
                "payment method"
            )
            || bodyText.contains(
                "submit order"
            )) {

            System.out.println(
                "Payment stage detected."
            );

            return true;
        }


        // -------------------------------------------------
        // GENERIC NEXT CHECKOUT STAGE
        // -------------------------------------------------

        if (!currentUrl.contains(
                "checkout_info"
            )) {

            System.out.println(
                "Checkout moved to next stage."
            );

            return true;
        }


        System.out.println(
            "Next checkout stage not detected."
        );

        return false;
    }
}