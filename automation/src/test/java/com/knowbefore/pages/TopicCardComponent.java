package com.knowbefore.pages;

import com.knowbefore.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TopicCardComponent extends BasePage {

    private final By topicCards = By.cssSelector("a[href*='/'][class*='rounded'], .grid a[href^='/topics/']");
    private final By topicCardTitles = By.cssSelector("a[href^='/topics/'] p.font-bold, a[href^='/topics/'] h3");
    private final By topicCardIcons = By.cssSelector("a[href^='/topics/'] span.text-3xl, a[href^='/topics/'] .text-3xl");
    private final By hotBadge = By.xpath("//a[contains(@href,'/topics/')]//span[contains(text(),'🔥')]");

    public TopicCardComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getAllTopicCards() {
        return getElements(topicCards);
    }

    public int getTopicCardCount() {
        return getElementCount(topicCards);
    }

    public List<String> getTopicTitles() {
        return getElements(topicCardTitles).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getTopicHrefs() {
        return getElements(topicCards).stream()
                .map(e -> e.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public void clickTopicByName(String topicName) {
        getElements(topicCards).stream()
                .filter(e -> e.getText().toLowerCase().contains(topicName.toLowerCase()))
                .findFirst()
                .ifPresent(e -> {
                    e.click();
                    WaitUtils.waitForPageLoad();
                });
    }

    public boolean isTopicVisible(String topicName) {
        return getElements(topicCards).stream()
                .anyMatch(e -> e.getText().toLowerCase().contains(topicName.toLowerCase()));
    }

    public int getHotTopicCount() {
        return getElementCount(hotBadge);
    }
}