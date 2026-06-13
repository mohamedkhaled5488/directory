package com.knowbefore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class TopicDetailPage extends BasePage {

    // -------------------------------------------------------------------------
    // Locators
    // -------------------------------------------------------------------------

    private final By pageH1             = By.cssSelector("h1");
    private final By quickAnswerBox     = By.cssSelector(".rounded-2xl.border");
    private final By quickAnswerText    = By.cssSelector("p.text-lg, p.text-base");
    private final By detailSection      = By.xpath(
            "//h2[contains(text(),'What You Need to Know')]/following-sibling::div");
    private final By touristTips        = By.cssSelector(".rounded-2xl li, ul li");
    private final By warningBox         = By.xpath(
            "//div[contains(@class,'red') or contains(@class,'warning')]");
    private final By comparisonSection  = By.xpath(
            "//h2[contains(text(),'Compare') or contains(text(),'Other')]");
    private final By affiliateCards     = By.cssSelector(".rounded-2xl a[href*='http']");
    private final By moreTopicsSection  = By.xpath("//h2[contains(text(),'More About')]");
    private final By lastVerified       = By.xpath("//p[contains(text(),'Last verified')]");
    private final By breadcrumbs        = By.cssSelector("nav a");
    private final By backToCountryLink  = By.cssSelector("nav a[href^='/']");

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public TopicDetailPage(WebDriver driver) {
        super(driver);
    }

    // -------------------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------------------

    /**
     * Returns the H1 heading of the topic detail page.
     */
    public String getPageTitle() {
        return getText(pageH1);
    }

    /**
     * Returns the quick-answer text shown in the first rounded card.
     */
    public String getQuickAnswer() {
        // Wait for the box to be visible then retrieve the paragraph inside it
        wait.until(ExpectedConditions.visibilityOfElementLocated(quickAnswerBox));
        List<WebElement> paragraphs = getElements(quickAnswerText);
        if (!paragraphs.isEmpty()) {
            return paragraphs.get(0).getText().trim();
        }
        return "";
    }

    /**
     * Returns the full text of the "What You Need to Know" detail section.
     */
    public String getDetailText() {
        try {
            WebElement section = driver.findElement(detailSection);
            return section.getText().trim();
        } catch (NoSuchElementException e) {
            logger.warn("Detail section not found");
            return "";
        }
    }

    /**
     * Returns all tourist tip bullet-point texts.
     */
    public List<String> getTouristTips() {
        List<WebElement> items = getElements(touristTips);
        List<String> tips = new ArrayList<>();
        for (WebElement item : items) {
            String text = item.getText().trim();
            if (!text.isEmpty()) {
                tips.add(text);
            }
        }
        return tips;
    }

    /**
     * Returns true when a warning/red box is present and visible.
     */
    public boolean isWarningBoxVisible() {
        return isDisplayed(warningBox);
    }

    /**
     * Returns the text content of the warning box, or empty string if absent.
     */
    public String getWarningText() {
        if (isWarningBoxVisible()) {
            return getText(warningBox);
        }
        return "";
    }

    /**
     * Returns the href values of all country links within the comparison section.
     */
    public List<String> getComparedCountries() {
        try {
            WebElement section = driver.findElement(comparisonSection);
            // Walk up to the section container and find all anchor children
            WebElement parent = section.findElement(By.xpath(".."));
            List<WebElement> links = parent.findElements(By.cssSelector("a[href^='/']"));
            List<String> hrefs = new ArrayList<>();
            for (WebElement link : links) {
                hrefs.add(link.getAttribute("href"));
            }
            return hrefs;
        } catch (NoSuchElementException e) {
            logger.warn("Comparison section not found");
            return new ArrayList<>();
        }
    }

    /**
     * Returns href attributes for all affiliate/partner card links.
     */
    public List<String> getAffiliateCards() {
        List<WebElement> cards = getElements(affiliateCards);
        List<String> hrefs = new ArrayList<>();
        for (WebElement card : cards) {
            hrefs.add(card.getAttribute("href"));
        }
        return hrefs;
    }

    /**
     * Returns the "Last verified: …" text, or empty string if not present.
     */
    public String getLastVerified() {
        if (isElementPresent(lastVerified)) {
            return getText(lastVerified);
        }
        return "";
    }

    /**
     * Clicks the "Home" breadcrumb (first nav link).
     */
    public void clickBreadcrumbHome() {
        logger.info("Clicking breadcrumb: Home");
        List<WebElement> crumbs = getElements(breadcrumbs);
        if (!crumbs.isEmpty()) {
            crumbs.get(0).click();
        }
    }

    /**
     * Clicks the country breadcrumb (second nav link).
     */
    public void clickBreadcrumbCountry() {
        logger.info("Clicking breadcrumb: Country");
        List<WebElement> crumbs = getElements(breadcrumbs);
        if (crumbs.size() >= 2) {
            crumbs.get(1).click();
        } else {
            throw new NoSuchElementException("Country breadcrumb not found");
        }
    }

    /**
     * Returns topic names listed in the "More About" sidebar section.
     */
    public List<String> getMoreTopics() {
        try {
            WebElement section = driver.findElement(moreTopicsSection);
            WebElement parent = section.findElement(By.xpath(".."));
            List<WebElement> links = parent.findElements(By.cssSelector("a"));
            List<String> topics = new ArrayList<>();
            for (WebElement link : links) {
                String text = link.getText().trim();
                if (!text.isEmpty()) {
                    topics.add(text);
                }
            }
            return topics;
        } catch (NoSuchElementException e) {
            logger.warn("More topics section not found");
            return new ArrayList<>();
        }
    }
}
