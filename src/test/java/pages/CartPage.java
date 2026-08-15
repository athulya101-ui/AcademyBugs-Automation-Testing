package pages;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String FIND_BUGS_URL =
        "https://academybugs.com/find-bugs/";

    private final By addToCartLinks =
        By.xpath(
            "//a[contains(@href,'ec_action=addtocart')]"
        );

    private final By quantityFields =
        By.cssSelector(
            "input[id^='ec_quantity_']"
        );

    private final By checkoutButton = By.xpath(
    	    "//*[self::a or self::button or self::input]" +
    	    "[contains(" +
    	    "translate(normalize-space(.)," +
    	    "'abcdefghijklmnopqrstuvwxyz'," +
    	    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
    	    "'CHECKOUT')" +
    	    " or contains(" +
    	    "translate(@value," +
    	    "'abcdefghijklmnopqrstuvwxyz'," +
    	    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ')," +
    	    "'CHECKOUT')]"
    	);

    public CartPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
            new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
            );
    }

    public void openFindBugsPage() {

        driver.get(FIND_BUGS_URL);

        closeTourOverlay();

        wait.until(
            ExpectedConditions
                .urlContains("find-bugs")
        );

        System.out.println(
            "Find Bugs page opened."
        );
    }

    public void addFirstProductToCart() {

        List<WebElement> products =
            wait.until(
                ExpectedConditions
                    .presenceOfAllElementsLocatedBy(
                        addToCartLinks
                    )
            );

        if (products.isEmpty()) {

            throw new NoSuchElementException(
                "No Add to Cart product found."
            );
        }

        String cartURL =
            products.get(0)
                    .getAttribute("href");

        System.out.println(
            "Add to Cart URL: "
            + cartURL
        );

        driver.get(cartURL);

        wait.until(
            ExpectedConditions
                .urlContains("my-cart")
        );

        closeTourOverlay();

        System.out.println(
            "Product added to cart."
        );
    }

    public boolean isCartPageLoaded() {

        try {

            wait.until(
                ExpectedConditions
                    .urlContains("my-cart")
            );

            return true;

        } catch (TimeoutException e) {

            return false;
        }
    }

    public int getProductQuantity() {

        WebElement field =
            wait.until(
                ExpectedConditions
                    .visibilityOfElementLocated(
                        quantityFields
                    )
            );

        return Integer.parseInt(
            field.getAttribute("value")
        );
    }

    public void updateProductQuantity(
            int newQuantity) {

        WebElement quantity =
            wait.until(
                ExpectedConditions
                    .visibilityOfElementLocated(
                        quantityFields
                    )
            );

        quantity.click();

        quantity.sendKeys(
            Keys.chord(
                Keys.CONTROL,
                "a"
            )
        );

        quantity.sendKeys(
            String.valueOf(
                newQuantity
            )
        );

        ((JavascriptExecutor) driver)
            .executeScript(
                "arguments[0].dispatchEvent(" +
                "new Event('input',{bubbles:true}));" +
                "arguments[0].dispatchEvent(" +
                "new Event('change',{bubbles:true}));" +
                "arguments[0].blur();",
                quantity
            );

        wait.until(d -> {

            try {

                String value =
                    d.findElement(
                        quantityFields
                    ).getAttribute(
                        "value"
                    );

                return value.equals(
                    String.valueOf(
                        newQuantity
                    )
                );

            } catch (
                StaleElementReferenceException e) {

                return false;
            }
        });

        System.out.println(
            "Quantity updated to: "
            + newQuantity
        );
    }

    private String getPageText() {

        return wait.until(
            ExpectedConditions
                .visibilityOfElementLocated(
                    By.tagName("body")
                )
        ).getText();
    }

    private double getAmount(
            String label) {

        String pageText =
            getPageText();

        String regex =
            "(?i)"
            + Pattern.quote(label)
            + "\\s*[\\$€£¥]?\\s*"
            + "([0-9]+(?:[,.][0-9]{2})?)";

        Matcher matcher =
            Pattern.compile(regex)
                   .matcher(pageText);

        if (!matcher.find()) {

            throw new NoSuchElementException(
                "Amount not found for: "
                + label
            );
        }

        return Double.parseDouble(
            matcher.group(1)
                   .replace(",", ".")
        );
    }

    public double getCartSubtotal() {

        return getAmount(
            "Cart Subtotal"
        );
    }

    public double getShippingCharge() {

        return getAmount(
            "Shipping"
        );
    }

    public double getGrandTotal() {

        return getAmount(
            "Grand Total"
        );
    }

    public void clickCheckout() {

        System.out.println(
            "Trying to open Checkout Details..."
        );

        WebElement button =
            wait.until(
                ExpectedConditions
                    .elementToBeClickable(
                        checkoutButton
                    )
            );

        try {

            button.click();

        } catch (ElementClickInterceptedException e) {

            ((JavascriptExecutor) driver)
                .executeScript(
                    "arguments[0].click();",
                    button
                );
        }

        System.out.println(
            "Checkout button clicked."
        );

        /*
         * Wait for actual checkout form.
         */
        By checkoutForm = By.xpath(
            "//input[" +
            "contains(translate(@id," +
            "'abcdefghijklmnopqrstuvwxyz'," +
            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'FIRST')" +
            " or contains(translate(@name," +
            "'abcdefghijklmnopqrstuvwxyz'," +
            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'FIRST')" +
            "]"
        );

        try {

            wait.until(
                ExpectedConditions
                    .visibilityOfElementLocated(
                        checkoutForm
                    )
            );

            System.out.println(
                "Checkout Details form opened successfully."
            );

            System.out.println(
                "Checkout URL: "
                + driver.getCurrentUrl()
            );

        } catch (TimeoutException e) {

            System.out.println(
                "ERROR: Checkout Details form did not appear."
            );

            System.out.println(
                "Current URL: "
                + driver.getCurrentUrl()
            );

            System.out.println(
                "Page title: "
                + driver.getTitle()
            );

            throw e;
        }
    }

    private void closeTourOverlay() {

        try {

            List<WebElement> overlays =
                driver.findElements(
                    By.id(
                        "TourTipDisabledArea"
                    )
                );

            for (
                WebElement overlay :
                overlays
            ) {

                ((JavascriptExecutor) driver)
                    .executeScript(
                        "arguments[0].style.display='none';",
                        overlay
                    );
            }

        } catch (Exception ignored) {
        }
    }
}