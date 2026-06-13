package com.knowbefore.pages;

import com.knowbefore.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class FooterComponent extends BasePage {

    private final By footer = By.cssSelector("footer");
    private final By footerLogo = By.cssSelector("footer a[href='/']");
    private final By footerLogoText = By.cssSelector("footer a[href='/'] span:last-child");
    private final By footerLinks = By.cssSelector("footer a");
    private final By countriesLink = By.cssSelector("footer a[href='/countries']");
    private final By topicsLink = By.cssSelector("footer a[href='/topics']");

    public FooterComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isFooterVisible() {
        return isDisplayed(footer);
    }

    public boolean isFooterLogoVisible() {
        return isDisplayed(footerLogo);
    }

    public String getLogoText() {
        return getText(footerLogoText);
    }

    public void clickFooterLogo() {
        waitAndClick(footerLogo);
        WaitUtils.waitForPageLoad();
    }

    public void clickCountriesLink() {
        waitAndClick(countriesLink);
        WaitUtils.waitForPageLoad();
    }

    public void clickTopicsLink() {
        waitAndClick(topicsLink);
        WaitUtils.waitForPageLoad();
    }

    public List<String> getAllFooterLinkTexts() {
        return getElements(footerLinks).stream()
                .map(WebElement::getText)
                .filter(t -> !t.isEmpty())
                .collect(Collectors.toList());
    }

    public List<String> getAllFooterLinkHrefs() {
        return getElements(footerLinks).stream()
                .map(e -> e.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public boolean areFooterLinksWorking() {
        List<WebElement> links = getElements(footerLinks);
        return links.stream().allMatch(e -> e.getAttribute("href") != null && !e.getAttribute("href").isEmpty());
    }
}