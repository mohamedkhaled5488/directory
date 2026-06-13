package com.knowbefore.pages;

import com.knowbefore.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchDropdownComponent extends BasePage {

    private final By searchInput = By.cssSelector("input[placeholder*='Search'], input[type='search']");
    private final By dropdownContainer = By.cssSelector(".absolute");
    private final By resultItems = By.cssSelector(".absolute .flex.items-start, .absolute a, .absolute .cursor-pointer");
    private final By resultCountry = By.cssSelector(".font-semibold");
    private final By resultTopic = By.cssSelector(".text-xs.text-gray-500");
    private final By noResultsMsg = By.xpath("//*[contains(text(),'No results') or contains(text(),'no results')]");

    public SearchDropdownComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void typeSearchTerm(String term) {
        WebElement input = WaitUtils.waitForElementClickable(searchInput);
        input.clear();
        input.sendKeys(term);
        WaitUtils.hardWait(600);
    }

    public void clearSearch() {
        WebElement input = WaitUtils.waitForElementClickable(searchInput);
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);
        WaitUtils.hardWait(400);
    }

    public boolean isDropdownVisible() {
        return isElementPresent(dropdownContainer) && isDisplayed(dropdownContainer);
    }

    public List<WebElement> getResultItems() {
        if (!isDropdownVisible()) return Collections.emptyList();
        return getElements(resultItems);
    }

    public int getResultCount() {
        return getResultItems().size();
    }

    public List<String> getResultTexts() {
        return getResultItems().stream()
                .map(WebElement::getText)
                .filter(t -> !t.isEmpty())
                .collect(Collectors.toList());
    }

    public void clickResult(int index) {
        List<WebElement> results = getResultItems();
        if (index < results.size()) {
            results.get(index).click();
            WaitUtils.waitForPageLoad();
        }
    }

    public void clickFirstResult() {
        clickResult(0);
    }

    public boolean hasNoResultsMessage() {
        return isElementPresent(noResultsMsg);
    }

    public boolean resultsContainText(String text) {
        return getResultTexts().stream()
                .anyMatch(t -> t.toLowerCase().contains(text.toLowerCase()));
    }
}