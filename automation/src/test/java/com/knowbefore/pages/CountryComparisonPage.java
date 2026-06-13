package com.knowbefore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CountryComparisonPage extends BasePage {

    // -------------------------------------------------------------------------
    // Locators
    // -------------------------------------------------------------------------

    private final By pageH1                  = By.cssSelector("h1");
    private final By country1Card            = By.cssSelector(".grid a:nth-child(1)");
    private final By country2Card            = By.cssSelector(".grid a:nth-child(2)");
    private final By comparisonRows          = By.cssSelector(".rounded-2xl.border.overflow-hidden");
    private final By topicHeaders            = By.cssSelector(".bg-gray-50 h2");
    private final By country1Cells           = By.cssSelector(".grid > a:first-child p.text-sm");
    private final By country2Cells           = By.cssSelector(".grid > a:last-child p.text-sm");
    private final By warningBadges           = By.cssSelector(".badge-red");
    private final By moreComparisonsSection  = By.xpath("//h2[contains(text(),'More Comparisons')]");
    private final By moreComparisonLinks     = By.cssSelector(".mt-12 a");
    private final By breadcrumbs             = By.cssSelector("nav a");

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public CountryComparisonPage(WebDriver driver) {
        super(driver);
    }

    // -------------------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------------------

    /**
     * Returns the H1 comparison page title (e.g. "France vs Germany").
     */
    public String getComparisonTitle() {
        return getText(pageH1);
    }

    /**
     * Returns the name of the first country shown in the comparison header.
     */
    public String getCountry1Name() {
        return getText(country1Card);
    }

    /**
     * Returns the name of the second country shown in the comparison header.
     */
    public String getCountry2Name() {
        return getText(country2Card);
    }

    /**
     * Returns the number of comparison topic rows rendered on the page.
     */
    public int getTopicCount() {
        return getElementCount(comparisonRows);
    }

    /**
     * Finds the comparison row for the given topic and returns the quick-answer
     * text for the specified country (1-based index: 1 or 2).
     *
     * @param country "1" or "2" (or the actual country name — matched by column order)
     * @param topic   the topic heading text to find
     */
    public String getQuickAnswerForTopic(String country, String topic) {
        logger.info("Getting quick answer for country='{}', topic='{}'", country, topic);
        List<WebElement> rows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(comparisonRows));
        for (WebElement row : rows) {
            try {
                WebElement header = row.findElement(By.cssSelector("h2"));
                if (header.getText().trim().toLowerCase().contains(topic.toLowerCase())) {
                    List<WebElement> cells = row.findElements(By.cssSelector("p.text-sm"));
                    // Column 0 = country1, column 1 = country2
                    int colIndex = country.equals("2") ? 1 : 0;
                    if (cells.size() > colIndex) {
                        return cells.get(colIndex).getText().trim();
                    }
                }
            } catch (NoSuchElementException e) {
                // Row has no h2 header — skip
            }
        }
        return "";
    }

    /**
     * Returns true when a comparison row with the given topic heading is present.
     */
    public boolean isTopicVisible(String topicName) {
        List<WebElement> headers = getElements(topicHeaders);
        for (WebElement header : headers) {
            if (header.getText().trim().toLowerCase().contains(topicName.toLowerCase())) {
                return header.isDisplayed();
            }
        }
        return false;
    }

    /**
     * Returns the href attributes of all "More Comparisons" links.
     */
    public List<String> getMoreComparisonLinks() {
        List<WebElement> links = getElements(moreComparisonLinks);
        List<String> hrefs = new ArrayList<>();
        for (WebElement link : links) {
            hrefs.add(link.getAttribute("href"));
        }
        return hrefs;
    }

    /**
     * Navigates directly to the comparison page for the given two country slugs.
     *
     * @param c1 first country slug, e.g. "france"
     * @param c2 second country slug, e.g. "germany"
     */
    public void clickMoreComparison(String c1, String c2) {
        logger.info("Navigating to comparison: {} vs {}", c1, c2);
        String targetHrefFragment = "/" + c1.toLowerCase() + "/" + c2.toLowerCase();
        List<WebElement> links = getElements(moreComparisonLinks);
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && href.toLowerCase().contains(targetHrefFragment)) {
                link.click();
                return;
            }
        }
        // Fall back to direct navigation
        navigateTo(getCurrentUrl().replaceAll("/compare/.*", "") + "/compare/" + c1 + "/" + c2);
    }

    /**
     * Returns the count of warning (red) badges across both country columns.
     */
    public int getWarningCount() {
        return getElementCount(warningBadges);
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
