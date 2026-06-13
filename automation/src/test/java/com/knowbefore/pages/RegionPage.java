package com.knowbefore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class RegionPage extends BasePage {

    // -------------------------------------------------------------------------
    // Locators
    // -------------------------------------------------------------------------

    private final By pageH1             = By.cssSelector("h1");
    private final By regionDescription  = By.cssSelector("p.text-teal-200");
    private final By countryCards       = By.cssSelector(".grid a[href^='/']");
    private final By otherRegionsLinks  = By.cssSelector("a[href^='/regions/']");
    private final By countryCount       = By.xpath("//p[contains(text(),'countries')]");
    private final By breadcrumbs        = By.cssSelector("nav a");

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public RegionPage(WebDriver driver) {
        super(driver);
    }

    // -------------------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------------------

    /**
     * Returns the H1 region name heading.
     */
    public String getRegionName() {
        return getText(pageH1);
    }

    /**
     * Returns the numeric country count parsed from the summary paragraph.
     * Expected format: "12 countries" → returns 12.
     * Returns 0 if the element is absent or contains no digits.
     */
    public int getCountryCount() {
        if (isElementPresent(countryCount)) {
            String text = getText(countryCount);
            String digits = text.replaceAll("[^0-9]", "");
            return digits.isEmpty() ? 0 : Integer.parseInt(digits);
        }
        return getElementCount(countryCards);
    }

    /**
     * Returns the visible text labels of all country cards in the region grid.
     */
    public List<String> getAllCountryNames() {
        List<WebElement> cards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(countryCards));
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
     * Clicks the country card whose text contains the given country name.
     */
    public void clickCountryCard(String countryName) {
        logger.info("Clicking country card: {}", countryName);
        List<WebElement> cards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(countryCards));
        for (WebElement card : cards) {
            if (card.getText().trim().toLowerCase().contains(countryName.toLowerCase())) {
                card.click();
                return;
            }
        }
        throw new NoSuchElementException("Country card not found: " + countryName);
    }

    /**
     * Clicks the "other regions" link whose text contains the given region name.
     */
    public void clickOtherRegion(String regionName) {
        logger.info("Clicking other region link: {}", regionName);
        List<WebElement> links = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(otherRegionsLinks));
        for (WebElement link : links) {
            if (link.getText().trim().toLowerCase().contains(regionName.toLowerCase())) {
                link.click();
                return;
            }
        }
        throw new NoSuchElementException("Other region link not found: " + regionName);
    }

    /**
     * Returns the href attributes of all "other regions" links.
     */
    public List<String> getOtherRegionLinks() {
        List<WebElement> links = getElements(otherRegionsLinks);
        List<String> hrefs = new ArrayList<>();
        for (WebElement link : links) {
            hrefs.add(link.getAttribute("href"));
        }
        return hrefs;
    }

    /**
     * Returns true when a country card with the given name is visible.
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

    /**
     * Returns the region description paragraph text (shown in the hero).
     * Returns empty string when the element is absent.
     */
    public String getRegionDescription() {
        if (isElementPresent(regionDescription)) {
            return getText(regionDescription);
        }
        return "";
    }

    /**
     * Returns the visible text of each breadcrumb link.
     */
    public List<String> getBreadcrumbs() {
        List<WebElement> crumbs = getElements(breadcrumbs);
        List<String> texts = new ArrayList<>();
        for (WebElement crumb : crumbs) {
            texts.add(crumb.getText().trim());
        }
        return texts;
    }
}
