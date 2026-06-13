package com.knowbefore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger;

    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        this.logger = LoggerFactory.getLogger(this.getClass());
        PageFactory.initElements(driver, this);
    }

    // -------------------------------------------------------------------------
    // Core interactions
    // -------------------------------------------------------------------------

    protected void click(By locator) {
        logger.debug("Clicking element: {}", locator);
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        logger.debug("Typing '{}' into element: {}", text, locator);
        driver.findElement(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        String text = driver.findElement(locator).getText();
        logger.debug("Got text '{}' from element: {}", text, locator);
        return text;
    }

    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            logger.debug("Element not found (isDisplayed=false): {}", locator);
            return false;
        }
    }

    protected boolean isEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (NoSuchElementException e) {
            logger.debug("Element not found (isEnabled=false): {}", locator);
            return false;
        }
    }

    protected String getTitle() {
        return driver.getTitle();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected void navigateTo(String url) {
        logger.info("Navigating to: {}", url);
        driver.get(url);
    }

    // -------------------------------------------------------------------------
    // Wait-based interactions
    // -------------------------------------------------------------------------

    protected void waitAndClick(By locator) {
        logger.debug("Waiting and clicking element: {}", locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    protected void waitAndType(By locator, String text) {
        logger.debug("Waiting and typing '{}' into element: {}", text, locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(text);
    }

    // -------------------------------------------------------------------------
    // Element collections
    // -------------------------------------------------------------------------

    protected List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    protected int getElementCount(By locator) {
        return driver.findElements(locator).size();
    }

    // -------------------------------------------------------------------------
    // Input helpers
    // -------------------------------------------------------------------------

    protected void clearAndType(By locator, String text) {
        logger.debug("Clearing and typing '{}' into element: {}", text, locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    protected void selectFromDropdown(By locator, String visibleText) {
        logger.debug("Selecting '{}' from dropdown: {}", visibleText, locator);
        WebElement element = driver.findElement(locator);
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    // -------------------------------------------------------------------------
    // Scroll & hover
    // -------------------------------------------------------------------------

    protected void scrollToElement(By locator) {
        logger.debug("Scrolling to element: {}", locator);
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void hoverOver(By locator) {
        logger.debug("Hovering over element: {}", locator);
        WebElement element = driver.findElement(locator);
        new Actions(driver).moveToElement(element).perform();
    }

    // -------------------------------------------------------------------------
    // Alerts
    // -------------------------------------------------------------------------

    protected void acceptAlert() {
        logger.debug("Accepting alert");
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    protected void dismissAlert() {
        logger.debug("Dismissing alert");
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    // -------------------------------------------------------------------------
    // Frames
    // -------------------------------------------------------------------------

    protected void switchToFrame(By locator) {
        logger.debug("Switching to frame: {}", locator);
        WebElement frame = driver.findElement(locator);
        driver.switchTo().frame(frame);
    }

    protected void switchToDefaultContent() {
        logger.debug("Switching to default content");
        driver.switchTo().defaultContent();
    }

    // -------------------------------------------------------------------------
    // Presence check
    // -------------------------------------------------------------------------

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
