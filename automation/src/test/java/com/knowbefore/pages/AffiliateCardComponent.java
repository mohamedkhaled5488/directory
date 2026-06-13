package com.knowbefore.pages;

import com.knowbefore.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class AffiliateCardComponent extends BasePage {

    private final By affiliateSection = By.xpath("//h2[contains(text(),'Useful') or contains(text(),'Book') or contains(text(),'Recommend')]/parent::div | //h2[contains(text(),'Useful') or contains(text(),'Book')]/following-sibling::div");
    private final By affiliateCards = By.cssSelector(".rounded-2xl a[href*='http'], a[target='_blank']");
    private final By affiliateCardTitles = By.cssSelector(".rounded-2xl a[href*='http'] p.font-semibold, a[target='_blank'] p.font-semibold");
    private final By affiliateImages = By.cssSelector(".rounded-2xl a[href*='http'] img, a[target='_blank'] img");
    private final By affiliateCTAButtons = By.cssSelector(".rounded-2xl a[href*='http'] span, a[target='_blank'] span");

    public AffiliateCardComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isAffiliatesSectionVisible() {
        return isElementPresent(affiliateSection);
    }

    public List<WebElement> getAffiliateCards() {
        return getElements(affiliateCards);
    }

    public int getAffiliateCardCount() {
        return getElementCount(affiliateCards);
    }

    public List<String> getAffiliateTitles() {
        return getElements(affiliateCardTitles).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAffiliateUrls() {
        return getElements(affiliateCards).stream()
                .map(e -> e.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public boolean allAffiliateLinksHaveHref() {
        return getElements(affiliateCards).stream()
                .allMatch(e -> {
                    String href = e.getAttribute("href");
                    return href != null && !href.isEmpty();
                });
    }

    public void clickAffiliateCard(int index) {
        List<WebElement> cards = getAffiliateCards();
        if (index < cards.size()) {
            cards.get(index).click();
        }
    }
}