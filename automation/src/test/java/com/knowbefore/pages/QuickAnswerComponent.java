package com.knowbefore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class QuickAnswerComponent extends BasePage {

    private final By quickAnswerCard = By.cssSelector(".rounded-2xl.border");
    private final By quickAnswerText = By.cssSelector(".rounded-2xl.border p");
    private final By warningBox = By.xpath("//div[contains(@class,'red') or contains(@class,'warning') or .//span[contains(text(),'⛔')]]");
    private final By warningText = By.xpath("//p[contains(@class,'red') or .//span[contains(text(),'⛔')]]/following-sibling::p | //div[contains(@class,'red')]//p");
    private final By sentimentBadge = By.cssSelector(".badge-green, .badge-yellow, .badge-red");
    private final By lastVerified = By.xpath("//p[contains(text(),'Last verified') or contains(text(),'verified')]");

    public QuickAnswerComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getQuickAnswerText() {
        try {
            List<WebElement> elements = getElements(quickAnswerText);
            return elements.isEmpty() ? "" : elements.get(0).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isWarningVisible() {
        return isElementPresent(warningBox);
    }

    public String getWarningText() {
        return isElementPresent(warningText) ? getText(warningText) : "";
    }

    public String getSentiment() {
        if (!isElementPresent(sentimentBadge)) return "unknown";
        String badgeClass = driver.findElement(sentimentBadge).getAttribute("class");
        if (badgeClass.contains("red")) return "red";
        if (badgeClass.contains("yellow")) return "yellow";
        return "green";
    }

    public String getLastVerifiedDate() {
        return isElementPresent(lastVerified) ? getText(lastVerified) : "";
    }

    public boolean isQuickAnswerVisible() {
        return isElementPresent(quickAnswerCard);
    }
}