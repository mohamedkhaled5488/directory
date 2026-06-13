package com.knowbefore.pages;

import com.knowbefore.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CountryCardComponent extends BasePage {

    private final By countryCards = By.xpath("//a[starts-with(@href,'/') and not(contains(@href,'/topics')) and not(contains(@href,'/regions')) and not(contains(@href,'/compare'))]");
    private final By countryCardEmojis = By.cssSelector(".text-4xl, .text-3xl");
    private final By countryCardNames = By.cssSelector("p.font-bold, h3");
    private final By countryCardRegions = By.cssSelector("p.text-xs.text-gray-400, p.text-gray-400");

    public CountryCardComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getAllCountryCards() {
        return getElements(countryCards);
    }

    public int getCountryCardCount() {
        return getElementCount(countryCards);
    }

    public List<String> getCountryNames() {
        return getElements(countryCardNames).stream()
                .map(WebElement::getText)
                .filter(t -> !t.isEmpty())
                .collect(Collectors.toList());
    }

    public List<String> getCountryHrefs() {
        return getElements(countryCards).stream()
                .map(e -> e.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public void clickCountryByName(String countryName) {
        getElements(countryCards).stream()
                .filter(e -> e.getText().toLowerCase().contains(countryName.toLowerCase()))
                .findFirst()
                .ifPresent(e -> {
                    e.click();
                    WaitUtils.waitForPageLoad();
                });
    }

    public boolean isCountryVisible(String countryName) {
        return getElements(countryCards).stream()
                .anyMatch(e -> e.getText().toLowerCase().contains(countryName.toLowerCase()));
    }

    public boolean allCardsHaveLinks() {
        return getElements(countryCards).stream()
                .allMatch(e -> {
                    String href = e.getAttribute("href");
                    return href != null && !href.isEmpty();
                });
    }
}