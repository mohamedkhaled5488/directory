package com.knowbefore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CountryDetailsPage extends BasePage {

    // -------------------------------------------------------------------------
    // Locators
    // -------------------------------------------------------------------------

    private final By countryName      = By.cssSelector("h1");
    private final By countryEmoji     = By.cssSelector(".text-7xl");
    private final By regionBadge      = By.xpath("//span[contains(text(),'🌏')]");
    private final By currencyBadge    = By.xpath("//span[contains(text(),'💱')]");
    private final By languageBadge    = By.xpath("//span[contains(text(),'🗣')]");
    private final By topicCountBadge  = By.xpath("//span[contains(text(),'topics covered')]");
    private final By topicCards       = By.cssSelector(".grid .rounded-2xl");
    private final By relatedCountries = By.cssSelector(".grid .rounded-2xl a[href^='/']");
    private final By statsBar         = By.cssSelector(".flex.flex-wrap.gap");
    private final By breadcrumbs      = By.cssSelector("nav.flex a, nav.flex span");

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public CountryDetailsPage(WebDriver driver) {
        super(driver);
    }

    // -------------------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------------------

    /**
     * Returns the H1 country name text.
     */
    public String getCountryName() {
        return getText(countryName);
    }

    /**
     * Returns the emoji displayed for the country (e.g. flag emoji).
     */
    public String getCountryEmoji() {
        return getText(countryEmoji);
    }

    /**
     * Returns the text of the region badge (contains the globe emoji).
     */
    public String getRegion() {
        String raw = getText(regionBadge);
        // Strip leading emoji and whitespace if present
        return raw.replaceAll("^[\\p{So}\\p{Sm}\\s]+", "").trim();
    }

    /**
     * Returns the currency text from its badge.
     */
    public String getCurrency() {
        String raw = getText(currencyBadge);
        return raw.replaceAll("^[\\p{So}\\p{Sm}\\s]+", "").trim();
    }

    /**
     * Returns the primary language text from its badge.
     */
    public String getLanguage() {
        String raw = getText(languageBadge);
        return raw.replaceAll("^[\\p{So}\\p{Sm}\\s]+", "").trim();
    }

    /**
     * Returns the numeric topic count parsed from the badge text.
     * Expected format: "25 topics covered" → returns 25
     */
    public int getTopicCount() {
        String text = getText(topicCountBadge);
        String digits = text.replaceAll("[^0-9]", "");
        if (digits.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(digits);
    }

    /**
     * Clicks a topic card whose text contains the given topic name.
     */
    public void clickTopicCard(String topicName) {
        logger.info("Clicking topic card: {}", topicName);
        List<WebElement> cards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(topicCards));
        for (WebElement card : cards) {
            if (card.getText().trim().toLowerCase().contains(topicName.toLowerCase())) {
                card.click();
                return;
            }
        }
        throw new NoSuchElementException("Topic card not found: " + topicName);
    }

    /**
     * Returns the href attributes of all related-country links.
     */
    public List<String> getRelatedCountries() {
        List<WebElement> links = getElements(relatedCountries);
        List<String> hrefs = new ArrayList<>();
        for (WebElement link : links) {
            hrefs.add(link.getAttribute("href"));
        }
        return hrefs;
    }

    /**
     * Returns true when the stats bar flex container is visible.
     */
    public boolean isStatsBarVisible() {
        return isDisplayed(statsBar);
    }

    /**
     * Returns the concatenated text of all breadcrumb segments.
     */
    public String getBreadcrumbText() {
        List<WebElement> crumbs = getElements(breadcrumbs);
        StringBuilder sb = new StringBuilder();
        for (WebElement crumb : crumbs) {
            if (sb.length() > 0) sb.append(" > ");
            sb.append(crumb.getText().trim());
        }
        return sb.toString();
    }
}
