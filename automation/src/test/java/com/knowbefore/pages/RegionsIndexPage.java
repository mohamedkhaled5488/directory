package com.knowbefore.pages;

import com.knowbefore.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RegionsIndexPage extends BasePage {

    private final By pageHeading = By.cssSelector("h1");
    private final By regionCards = By.cssSelector("a[href^='/regions/']");
    private final By regionCardTitles = By.cssSelector("a[href^='/regions/'] p.font-bold, a[href^='/regions/'] h2, a[href^='/regions/'] h3");
    private final By regionCardCountryCounts = By.cssSelector("a[href^='/regions/'] p.text-sm");
    private final By europeCard = By.cssSelector("a[href='/regions/europe']");
    private final By asiaCard = By.cssSelector("a[href='/regions/asia']");
    private final By americasCard = By.cssSelector("a[href='/regions/americas']");
    private final By middleEastCard = By.cssSelector("a[href='/regions/middle-east']");
    private final By africaOceaniaCard = By.cssSelector("a[href='/regions/africa-oceania']");

    public RegionsIndexPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getPageHeading() {
        return getText(pageHeading);
    }

    public List<WebElement> getAllRegionCards() {
        return getElements(regionCards);
    }

    public int getRegionCardCount() {
        return getElementCount(regionCards);
    }

    public List<String> getRegionNames() {
        return getElements(regionCardTitles).stream()
                .map(WebElement::getText)
                .filter(t -> !t.isEmpty())
                .collect(Collectors.toList());
    }

    public List<String> getRegionHrefs() {
        return getElements(regionCards).stream()
                .map(e -> e.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public void clickEurope() { waitAndClick(europeCard); WaitUtils.waitForPageLoad(); }
    public void clickAsia() { waitAndClick(asiaCard); WaitUtils.waitForPageLoad(); }
    public void clickAmericas() { waitAndClick(americasCard); WaitUtils.waitForPageLoad(); }
    public void clickMiddleEast() { waitAndClick(middleEastCard); WaitUtils.waitForPageLoad(); }
    public void clickAfricaOceania() { waitAndClick(africaOceaniaCard); WaitUtils.waitForPageLoad(); }

    public void clickRegionByName(String regionName) {
        getElements(regionCards).stream()
                .filter(e -> e.getText().toLowerCase().contains(regionName.toLowerCase()))
                .findFirst()
                .ifPresent(e -> {
                    e.click();
                    WaitUtils.waitForPageLoad();
                });
    }

    public boolean isRegionVisible(String regionName) {
        return getElements(regionCards).stream()
                .anyMatch(e -> e.getText().toLowerCase().contains(regionName.toLowerCase()));
    }
}