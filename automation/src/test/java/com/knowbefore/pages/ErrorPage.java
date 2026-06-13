package com.knowbefore.pages;

import com.knowbefore.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ErrorPage extends BasePage {

    private final By heading404 = By.cssSelector("h1, h2");
    private final By messageText = By.cssSelector("p");
    private final By homeLink = By.cssSelector("a[href='/']");
    private final By backLink = By.cssSelector("a");

    public ErrorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean is404Page() {
        String title = driver.getTitle().toLowerCase();
        String bodyText = getText(By.cssSelector("body")).toLowerCase();
        return title.contains("not found") || title.contains("404")
                || bodyText.contains("404") || bodyText.contains("not found");
    }

    public String getErrorHeading() {
        return getText(heading404);
    }

    public String getErrorMessage() {
        return getText(messageText);
    }

    public boolean isHomeLinkVisible() {
        return isElementPresent(homeLink);
    }

    public void clickHomeLink() {
        waitAndClick(homeLink);
        WaitUtils.waitForPageLoad();
    }

    public void clickBackLink() {
        waitAndClick(backLink);
        WaitUtils.waitForPageLoad();
    }
}