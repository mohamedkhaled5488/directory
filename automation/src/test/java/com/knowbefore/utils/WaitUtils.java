package com.knowbefore.utils;

import com.knowbefore.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private static final Logger log = LogManager.getLogger(WaitUtils.class);
    private static final int DEFAULT_TIMEOUT = 20;
    private static final int POLLING_INTERVAL = 500;

    private WaitUtils() {}

    private static WebDriverWait getWait(int timeoutSeconds) {
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeoutSeconds));
    }

    private static WebDriverWait getWait() {
        return getWait(DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementVisible(By locator) {
        log.debug("Waiting for element visible: {}", locator);
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementVisible(By locator, int timeoutSeconds) {
        log.debug("Waiting {}s for element visible: {}", timeoutSeconds, locator);
        return getWait(timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementVisible(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementClickable(By locator) {
        log.debug("Waiting for element clickable: {}", locator);
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForElementClickable(By locator, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForElementClickable(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean waitForElementInvisible(By locator) {
        log.debug("Waiting for element invisible: {}", locator);
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean waitForElementInvisible(By locator, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementPresent(By locator) {
        log.debug("Waiting for element present: {}", locator);
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static List<WebElement> waitForAllElementsVisible(By locator) {
        log.debug("Waiting for all elements visible: {}", locator);
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static List<WebElement> waitForAllElementsPresent(By locator) {
        return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static boolean waitForTitleContains(String title) {
        log.debug("Waiting for title to contain: {}", title);
        return getWait().until(ExpectedConditions.titleContains(title));
    }

    public static boolean waitForTitleIs(String title) {
        return getWait().until(ExpectedConditions.titleIs(title));
    }

    public static boolean waitForUrlContains(String urlFragment) {
        log.debug("Waiting for URL to contain: {}", urlFragment);
        return getWait().until(ExpectedConditions.urlContains(urlFragment));
    }

    public static boolean waitForUrlToBe(String url) {
        return getWait().until(ExpectedConditions.urlToBe(url));
    }

    public static WebElement waitForTextPresent(By locator, String text) {
        log.debug("Waiting for text '{}' in element: {}", text, locator);
        getWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        return DriverFactory.getDriver().findElement(locator);
    }

    public static boolean waitForAttributeContains(By locator, String attribute, String value) {
        return getWait().until(ExpectedConditions.attributeContains(locator, attribute, value));
    }

    public static boolean waitForElementToBeSelected(By locator) {
        return getWait().until(ExpectedConditions.elementToBeSelected(locator));
    }

    public static void waitForPageLoad() {
        log.debug("Waiting for page load complete");
        getWait().until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete");
        });
    }

    public static WebElement fluentWait(By locator, int timeoutSeconds, int pollingMs) {
        log.debug("Fluent wait for: {} timeout={}s polling={}ms", locator, timeoutSeconds, pollingMs);
        FluentWait<WebDriver> wait = new FluentWait<>(DriverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMs))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return wait.until(driver -> driver.findElement(locator));
    }

    public static boolean isElementPresent(By locator) {
        try {
            DriverFactory.getDriver().findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementVisible(By locator) {
        try {
            return DriverFactory.getDriver().findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void hardWait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
