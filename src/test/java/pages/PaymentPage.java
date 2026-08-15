package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public PaymentPage(
            WebDriver driver) {

        this.driver = driver;

        wait =
            new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
            );
    }

    private boolean exists(
            By locator) {

        try {

            List<WebElement> elements =
                driver.findElements(
                    locator
                );

            return !elements.isEmpty()
                && elements.get(0)
                           .isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isPaymentPageDisplayed() {

        By paymentHeading =
            By.xpath(
                "//*[contains(" +
                "translate(normalize-space(.)," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'PAYMENT METHOD')]"
            );

        try {

            wait.until(
                ExpectedConditions
                    .visibilityOfElementLocated(
                        paymentHeading
                    )
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isDirectDepositDisplayed() {

        return exists(
            By.xpath(
                "//*[contains(" +
                "translate(normalize-space(.)," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'DIRECT DEPOSIT')]"
            )
        );
    }

    public boolean isAnyRealPaymentMethodAvailable() {

        By cardNumber =
            By.xpath(
                "//input[contains(" +
                "translate(@name," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'CARD')" +
                " or contains(" +
                "translate(@placeholder," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'CARD')]"
            );

        By cvv =
            By.xpath(
                "//input[contains(" +
                "translate(@name," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'CVV')" +
                " or contains(" +
                "translate(@placeholder," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'CVV')" +
                " or contains(" +
                "translate(@placeholder," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'CVC')]"
            );

        By expiry =
            By.xpath(
                "//input[contains(" +
                "translate(@name," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'EXPIR')" +
                " or contains(" +
                "translate(@placeholder," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'EXPIR')]"
            );

        By upi =
            By.xpath(
                "//*[contains(" +
                "translate(normalize-space(.)," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'UPI')]"
            );

        By paypal =
            By.xpath(
                "//*[contains(" +
                "translate(normalize-space(.)," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'PAYPAL')]"
            );

        return exists(cardNumber)
            || exists(cvv)
            || exists(expiry)
            || exists(upi)
            || exists(paypal);
    }

    public boolean isSubmitOrderDisplayed() {

        return exists(
            getSubmitOrderLocator()
        );
    }

    private By getSubmitOrderLocator() {

        return By.xpath(
            "//*[self::button or self::input or self::a]" +
            "[contains(" +
            "translate(normalize-space(.)," +
            "'abcdefghijklmnopqrstuvwxyz'," +
            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
            "'SUBMIT ORDER')" +
            " or contains(" +
            "translate(@value," +
            "'abcdefghijklmnopqrstuvwxyz'," +
            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
            "'SUBMIT ORDER')]"
        );
    }

    public void clickSubmitOrder() {

        wait.until(
            ExpectedConditions
                .elementToBeClickable(
                    getSubmitOrderLocator()
                )
        ).click();

        System.out.println(
            "Submit Order clicked."
        );
    }

    public boolean isTestingSitePopupDisplayed() {

        By testingMessage =
            By.xpath(
                "//*[contains(" +
                "translate(normalize-space(.)," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'TESTING SITE')" +
                " or contains(" +
                "translate(normalize-space(.)," +
                "'abcdefghijklmnopqrstuvwxyz'," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
                "'NO REAL ORDERS')]"
            );

        try {

            wait.until(
                ExpectedConditions
                    .visibilityOfElementLocated(
                        testingMessage
                    )
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}