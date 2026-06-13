package com.knowbefore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CountriesPage extends BasePage {

    // -------------------------------------------------------------------------
    // Locators
    // -------------------------------------------------------------------------

    private final By pageHeading     = By.cssSelector("h1");
    private final By countryCards    = By.xpath(
            "//a[starts-with(@href,'/') "
            + "and not(contains(@href,'/topics')) "
            + "and not(contains(@href,'/regions')) "
            + "and not(contains(@href,'/compare'))]");
    private final By regionFilterTabs = By.cssSelector("button[class*='rounded']");
    private final By allTab          = By.xpath("//button[text()='All']");
    private final By europeTab       = By.xpath("//button[text()='Europe']");
    private final By asiaTab         = By.xpath("//button[text()='Asia']");
    private final By americasTab     = By.xpath("//button[text()='Americas']");
    private final By middleEastTab   = By.xpath("//button[text()='Middle East']");
    private final By africaTab       = By.xpath("//button[text()='Africa & Oceania']");

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public CountriesPage(WebDriver driver) {
        super(driver);
    }

    // -------------------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------------------

    /**
     * Returns the H1 text of the Countries page.
     */
    public String getPageHeading() {
        return getText(pageHeading);
    }

    /**
     * Clicks the region filter tab whose label exactly matches the given text.
     * Accepts: "All", "Europe", "Asia", "Americas", "Middle East", "Africa & Oceania"
     */
    public void clickRegionTab(String tabLabel) {
        logger.info("Clicking region tab: {}", tabLabel);
        By tabLocator = By.xpath("//button[text()='" + tabLabel + "']");
        waitAndClick(tabLocator);
        // Brief pause to allow the grid to re-render after filter change
        try { Thread.sleep(400); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    /**
     * Returns the number of country cards currently displayed.
     */
    public int getDisplayedCountryCount() {
        return getElementCount(countryCards);
    }

    /**
     * Clicks the country card whose text contains the given name.
     */
    public void clickCountryByName(String countryName) {
        logger.info("Clicking country: {}", countryName);
        List<WebElement> cards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(countryCards));
        for (WebElement card : cards) {
            if (card.getText().trim().toLowerCase().contains(countryName.toLowerCase())) {
                card.click();
                return;
            }
        }
        throw new NoSuchElementException("Country not found: " + countryName);
    }

    /**
     * Returns the visible text labels of all country cards currently displayed.
     */
    public List<String> getAllCountryNames() {
        List<WebElement> cards = getElements(countryCards);
        List<String> names = new ArrayList<>();
        for (WebElement card : cards) {
            String text = card.getText().trim();
            if (!text.isEmpty()) {
                names.add(text);
            }
        }
        return names;
    }

    /**
     * Returns true if a country card with the given name is currently visible.
     */
    public boolean isCountryVisible(String countryName) {
        List<WebElement> cards = getElements(countryCards);
        for (WebElement card : cards) {
            if (card.getText().trim().toLowerCase().contains(countryName.toLowerCase())) {
                return card.isDisplayed();
            }
        }
        return false;
    }

    /** Clicks the "All" region filter tab. */
    public void clickAllTab() {
        logger.info("Clicking 'All' tab");
        waitAndClick(allTab);
    }

    /** Clicks the "Europe" region filter tab. */
    public void clickEuropeTab() {
        logger.info("Clicking 'Europe' tab");
        waitAndClick(europeTab);
    }

    /** Clicks the "Asia" region filter tab. */
    public void clickAsiaTab() {
        logger.info("Clicking 'Asia' tab");
        waitAndClick(asiaTab);
    }

    /** Clicks the "Americas" region filter tab. */
    public void clickAmericasTab() {
        logger.info("Clicking 'Americas' tab");
        waitAndClick(americasTab);
    }

    /** Clicks the "Middle East" region filter tab. */
    public void clickMiddleEastTab() {
        logger.info("Clicking 'Middle East' tab");
        waitAndClick(middleEastTab);
    }

    /** Clicks the "Africa & Oceania" region filter tab. */
    public void clickAfricaTab() {
        logger.info("Clicking 'Africa & Oceania' tab");
        waitAndClick(africaTab);
    }
}
