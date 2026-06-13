package com.knowbefore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class NavigationComponent extends BasePage {

    // -------------------------------------------------------------------------
    // Locators
    // -------------------------------------------------------------------------

    private final By navBar           = By.cssSelector("nav");
    private final By logo             = By.cssSelector("nav a[href='/']");
    private final By logoText         = By.cssSelector("nav a[href='/'] span:last-child");
    private final By countriesLink    = By.linkText("Countries");
    private final By topicsLink       = By.linkText("Topics");
    private final By browseAllButton  = By.cssSelector("nav a.bg-teal-600");
    private final By mobileMenuButton = By.cssSelector("button[aria-label='Toggle menu']");
    private final By mobileMenuItems  = By.cssSelector(".border-t a");

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public NavigationComponent(WebDriver driver) {
        super(driver);
    }

    // -------------------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------------------

    /**
     * Clicks the KnowBefore logo to return to the homepage.
     */
    public void clickLogo() {
        logger.info("Clicking site logo");
        waitAndClick(logo);
    }

    /**
     * Clicks the "Countries" navigation link.
     */
    public void clickCountriesLink() {
        logger.info("Clicking nav: Countries");
        waitAndClick(countriesLink);
    }

    /**
     * Clicks the "Topics" navigation link.
     */
    public void clickTopicsLink() {
        logger.info("Clicking nav: Topics");
        waitAndClick(topicsLink);
    }

    /**
     * Clicks the "Browse All" teal CTA button in the navigation bar.
     * Falls back to clicking the Countries link if the button is absent.
     */
    public void clickBrowseAll() {
        logger.info("Clicking Browse All button");
        if (isElementPresent(browseAllButton)) {
            waitAndClick(browseAllButton);
        } else {
            logger.warn("Browse All button not found — falling back to Countries link");
            clickCountriesLink();
        }
    }

    /**
     * Returns the text content of the logo span (should be "KnowBefore").
     */
    public String getLogoText() {
        try {
            return getText(logoText);
        } catch (NoSuchElementException e) {
            // Logo may be image-only on some viewports; return full anchor text
            return getText(logo);
        }
    }

    /**
     * Returns true when the nav bar element is visible on the page.
     */
    public boolean isNavBarVisible() {
        return isDisplayed(navBar);
    }

    /**
     * Opens the mobile hamburger menu by clicking the toggle button.
     * No-op when the button is not present (desktop viewport).
     */
    public void openMobileMenu() {
        logger.info("Opening mobile menu");
        if (isElementPresent(mobileMenuButton)) {
            waitAndClick(mobileMenuButton);
            wait.until(ExpectedConditions.visibilityOfElementLocated(mobileMenuItems));
        } else {
            logger.debug("Mobile menu toggle not present — desktop viewport assumed");
        }
    }

    /**
     * Clicks the mobile menu item whose text contains the given label.
     * Calls openMobileMenu() first if the menu is not already open.
     */
    public void clickMobileMenuItem(String itemLabel) {
        logger.info("Clicking mobile menu item: {}", itemLabel);
        if (!isMenuOpen()) {
            openMobileMenu();
        }
        List<WebElement> items = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(mobileMenuItems));
        for (WebElement item : items) {
            if (item.getText().trim().toLowerCase().contains(itemLabel.toLowerCase())) {
                item.click();
                return;
            }
        }
        throw new NoSuchElementException("Mobile menu item not found: " + itemLabel);
    }

    /**
     * Returns true when at least one mobile menu item link is visible, indicating
     * the dropdown menu is currently open.
     */
    public boolean isMenuOpen() {
        List<WebElement> items = getElements(mobileMenuItems);
        for (WebElement item : items) {
            if (item.isDisplayed()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Asserts that the main navigation links (Countries and Topics) are present
     * and visible. Logs a warning for each missing link rather than throwing.
     *
     * @return true if all expected nav links are visible, false otherwise
     */
    public boolean verifyNavLinks() {
        boolean allPresent = true;

        if (!isDisplayed(logo)) {
            logger.warn("Nav logo is not visible");
            allPresent = false;
        }
        if (!isDisplayed(countriesLink)) {
            logger.warn("'Countries' nav link is not visible");
            allPresent = false;
        }
        if (!isDisplayed(topicsLink)) {
            logger.warn("'Topics' nav link is not visible");
            allPresent = false;
        }

        logger.info("verifyNavLinks result: {}", allPresent);
        return allPresent;
    }

    /**
     * Returns the href attributes of all mobile menu items currently rendered.
     */
    public List<String> getMobileMenuHrefs() {
        List<WebElement> items = getElements(mobileMenuItems);
        List<String> hrefs = new ArrayList<>();
        for (WebElement item : items) {
            String href = item.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                hrefs.add(href);
            }
        }
        return hrefs;
    }
}
