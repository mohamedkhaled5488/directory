package com.knowbefore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class TopicOverviewPage extends BasePage {

    // -------------------------------------------------------------------------
    // Locators
    // -------------------------------------------------------------------------

    private final By pageHeading       = By.cssSelector("h1");
    private final By topicDescription  = By.cssSelector("p.text-gray-500, p.text-gray-600");
    private final By countryRows       = By.cssSelector("a[href*='/'][class*='rounded']");
    private final By quickAnswerCells  = By.cssSelector("p.text-sm");
    private final By warningBadges     = By.cssSelector(".badge-red, span[class*='red']");
    private final By cautionBadges     = By.cssSelector(".badge-yellow, span[class*='yellow']");
    private final By normalBadges      = By.cssSelector(".badge-green, span[class*='green']");

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public TopicOverviewPage(WebDriver driver) {
        super(driver);
    }

    // -------------------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------------------

    /**
     * Returns the H1 heading (topic name) of the overview page.
     */
    public String getPageHeading() {
        return getText(pageHeading);
    }

    /**
     * Returns the number of country rows currently shown in the table.
     */
    public int getCountryRowCount() {
        return getElementCount(countryRows);
    }

    /**
     * Finds the country row matching the given name and returns the quick-answer
     * text from the adjacent cell.
     * Returns empty string when the country is not found.
     */
    public String getQuickAnswerForCountry(String countryName) {
        logger.info("Getting quick answer for country: {}", countryName);
        List<WebElement> rows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(countryRows));
        for (WebElement row : rows) {
            if (row.getText().trim().toLowerCase().contains(countryName.toLowerCase())) {
                try {
                    WebElement cell = row.findElement(By.cssSelector("p.text-sm"));
                    return cell.getText().trim();
                } catch (NoSuchElementException e) {
                    // Fall back to whole row text after stripping the country name
                    String full = row.getText().trim();
                    return full.replaceFirst("(?i)" + countryName, "").trim();
                }
            }
        }
        logger.warn("Country not found in overview table: {}", countryName);
        return "";
    }

    /**
     * Returns the count of warning (red) badges in the table.
     */
    public int getWarningCount() {
        return getElementCount(warningBadges);
    }

    /**
     * Returns the count of caution (yellow) badges in the table.
     */
    public int getCautionCount() {
        return getElementCount(cautionBadges);
    }

    /**
     * Clicks the country row whose text contains the given country name.
     */
    public void clickCountryRow(String countryName) {
        logger.info("Clicking country row: {}", countryName);
        List<WebElement> rows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(countryRows));
        for (WebElement row : rows) {
            if (row.getText().trim().toLowerCase().contains(countryName.toLowerCase())) {
                row.click();
                return;
            }
        }
        throw new NoSuchElementException("Country row not found: " + countryName);
    }

    /**
     * Returns the topic description paragraph text.
     * Returns empty string when the description is absent.
     */
    public String getTopicDescription() {
        if (isElementPresent(topicDescription)) {
            return getText(topicDescription);
        }
        return "";
    }

    /**
     * Returns a list of all country names shown in the overview table.
     */
    public List<String> getAllCountryNames() {
        List<WebElement> rows = getElements(countryRows);
        List<String> names = new ArrayList<>();
        for (WebElement row : rows) {
            String text = row.getText().trim();
            if (!text.isEmpty()) {
                names.add(text);
            }
        }
        return names;
    }
}
