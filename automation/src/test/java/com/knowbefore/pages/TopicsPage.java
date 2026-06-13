package com.knowbefore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class TopicsPage extends BasePage {

    // -------------------------------------------------------------------------
    // Locators
    // -------------------------------------------------------------------------

    private final By pageHeading     = By.cssSelector("h1");
    private final By topicCards      = By.cssSelector("a[href^='/topics/']");
    private final By topicIcons      = By.cssSelector("a[href^='/topics/'] span.text");
    private final By totalTopicsText = By.xpath("//p[contains(text(),'topic')]");

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public TopicsPage(WebDriver driver) {
        super(driver);
    }

    // -------------------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------------------

    /**
     * Returns the H1 heading of the Topics page.
     */
    public String getPageHeading() {
        return getText(pageHeading);
    }

    /**
     * Returns the number of topic cards displayed on the page.
     */
    public int getTopicCardCount() {
        return getElementCount(topicCards);
    }

    /**
     * Clicks the topic card whose text contains the given topic name.
     */
    public void clickTopicByName(String topicName) {
        logger.info("Clicking topic: {}", topicName);
        List<WebElement> cards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(topicCards));
        for (WebElement card : cards) {
            if (card.getText().trim().toLowerCase().contains(topicName.toLowerCase())) {
                card.click();
                return;
            }
        }
        throw new NoSuchElementException("Topic not found: " + topicName);
    }

    /**
     * Returns the visible text labels of all topic cards.
     */
    public List<String> getAllTopicNames() {
        List<WebElement> cards = getElements(topicCards);
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
     * Returns true when a topic card with the given name is visible.
     */
    public boolean isTopicVisible(String topicName) {
        List<WebElement> cards = getElements(topicCards);
        for (WebElement card : cards) {
            if (card.getText().trim().toLowerCase().contains(topicName.toLowerCase())) {
                return card.isDisplayed();
            }
        }
        return false;
    }

    /**
     * Returns the icon/emoji text for a topic card matching the given name.
     * Returns empty string if the topic or its icon element is not found.
     */
    public String getTopicIcon(String topicName) {
        List<WebElement> cards = getElements(topicCards);
        for (WebElement card : cards) {
            if (card.getText().trim().toLowerCase().contains(topicName.toLowerCase())) {
                try {
                    WebElement iconSpan = card.findElement(By.cssSelector("span.text"));
                    return iconSpan.getText().trim();
                } catch (NoSuchElementException e) {
                    logger.warn("Icon span not found inside topic card: {}", topicName);
                    return "";
                }
            }
        }
        return "";
    }
}
