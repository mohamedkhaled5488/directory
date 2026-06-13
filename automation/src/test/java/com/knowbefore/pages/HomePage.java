package com.knowbefore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    // -------------------------------------------------------------------------
    // Locators
    // -------------------------------------------------------------------------

    private final By searchInput        = By.cssSelector("input[placeholder*='Search']");
    private final By searchResults      = By.cssSelector(".absolute .flex.items-start");
    private final By countryCards       = By.cssSelector("section a[href^='/']");
    private final By topicCards         = By.cssSelector(".grid a[href^='/topics/']");
    private final By navLogo            = By.cssSelector("nav a[href='/']");
    private final By navCountries       = By.linkText("Countries");
    private final By navTopics          = By.linkText("Topics");
    private final By heroTitle          = By.cssSelector("h1");
    private final By totalCountriesText = By.xpath("//p[contains(text(),'countries')]");

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // -------------------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------------------

    /**
     * Types a query into the search bar and waits for results to appear.
     */
    public void searchFor(String query) {
        logger.info("Searching for: {}", query);
        waitAndType(searchInput, query);
        try {
            wait.withTimeout(java.time.Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(searchResults));
        } catch (org.openqa.selenium.TimeoutException ignored) {
            // no results is a valid outcome for negative test cases
        }
    }

    /**
     * Returns the visible text of all search result items currently shown.
     */
    public List<String> getSearchResults() {
        List<WebElement> results = getElements(searchResults);
        List<String> texts = new ArrayList<>();
        for (WebElement el : results) {
            texts.add(el.getText().trim());
        }
        return texts;
    }

    /**
     * Clicks the first entry in the search dropdown.
     */
    public void clickFirstSearchResult() {
        logger.info("Clicking first search result");
        List<WebElement> results = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResults));
        if (!results.isEmpty()) {
            results.get(0).click();
        } else {
            throw new NoSuchElementException("No search results are visible");
        }
    }

    /**
     * Clicks a country card whose visible text contains the given name.
     */
    public void clickCountryCard(String countryName) {
        logger.info("Clicking country card: {}", countryName);
        List<WebElement> cards = getElements(countryCards);
        for (WebElement card : cards) {
            if (card.getText().trim().toLowerCase().contains(countryName.toLowerCase())) {
                card.click();
                return;
            }
        }
        throw new NoSuchElementException("Country card not found: " + countryName);
    }

    /**
     * Clicks a topic card whose visible text contains the given topic name.
     */
    public void clickTopicCard(String topicName) {
        logger.info("Clicking topic card: {}", topicName);
        List<WebElement> cards = getElements(topicCards);
        for (WebElement card : cards) {
            if (card.getText().trim().toLowerCase().contains(topicName.toLowerCase())) {
                card.click();
                return;
            }
        }
        throw new NoSuchElementException("Topic card not found: " + topicName);
    }

    /**
     * Navigates to the Countries page via the nav link.
     */
    public void clickNavCountries() {
        logger.info("Clicking nav: Countries");
        waitAndClick(navCountries);
    }

    /**
     * Navigates to the Topics page via the nav link.
     */
    public void clickNavTopics() {
        logger.info("Clicking nav: Topics");
        waitAndClick(navTopics);
    }

    /**
     * Returns the H1 text of the hero section.
     */
    public String getHeroTitle() {
        return getText(heroTitle);
    }

    /**
     * Returns true when the search bar input is visible on the page.
     */
    public boolean isSearchBarVisible() {
        return isDisplayed(searchInput);
    }

    /**
     * Returns the number of country cards rendered on the homepage.
     */
    public int getCountryCardCount() {
        return getElementCount(countryCards);
    }

    /**
     * Clicks the site logo to return to the homepage.
     */
    public void clickNavLogo() {
        logger.info("Clicking nav logo");
        waitAndClick(navLogo);
    }
}
