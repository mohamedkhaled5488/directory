package com.knowbefore.pages;

import com.knowbefore.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RegionFilterComponent extends BasePage {

    private final By filterContainer = By.cssSelector(".flex.gap-2, .flex.flex-wrap.gap");
    private final By allFilterButton = By.xpath("//button[normalize-space(text())='All']");
    private final By europeFilterButton = By.xpath("//button[normalize-space(text())='Europe']");
    private final By asiaFilterButton = By.xpath("//button[normalize-space(text())='Asia']");
    private final By americasFilterButton = By.xpath("//button[normalize-space(text())='Americas']");
    private final By middleEastFilterButton = By.xpath("//button[normalize-space(text())='Middle East']");
    private final By africaOceaniaFilterButton = By.xpath("//button[normalize-space(text())='Africa & Oceania']");
    private final By activeFilterButton = By.cssSelector("button.bg-teal-600, button[class*='bg-teal']");
    private final By allFilterButtons = By.cssSelector("button[class*='rounded']");

    public RegionFilterComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickAll() { waitAndClick(allFilterButton); WaitUtils.hardWait(400); }
    public void clickEurope() { waitAndClick(europeFilterButton); WaitUtils.hardWait(400); }
    public void clickAsia() { waitAndClick(asiaFilterButton); WaitUtils.hardWait(400); }
    public void clickAmericas() { waitAndClick(americasFilterButton); WaitUtils.hardWait(400); }
    public void clickMiddleEast() { waitAndClick(middleEastFilterButton); WaitUtils.hardWait(400); }
    public void clickAfricaOceania() { waitAndClick(africaOceaniaFilterButton); WaitUtils.hardWait(400); }

    public void clickFilterByName(String regionName) {
        By locator = By.xpath("//button[normalize-space(text())='" + regionName + "']");
        waitAndClick(locator);
        WaitUtils.hardWait(400);
    }

    public String getActiveFilter() {
        return isElementPresent(activeFilterButton) ? getText(activeFilterButton) : "All";
    }

    public List<String> getAllFilterNames() {
        return getElements(allFilterButtons).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isFilterContainerVisible() {
        return isDisplayed(filterContainer);
    }

    public int getFilterCount() {
        return getElementCount(allFilterButtons);
    }
}