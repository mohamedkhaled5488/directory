package com.knowbefore.pages;

import com.knowbefore.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class BreadcrumbComponent extends BasePage {

    private final By breadcrumbNav = By.cssSelector("nav.flex");
    private final By breadcrumbLinks = By.cssSelector("nav.flex a");
    private final By breadcrumbSeparators = By.cssSelector("nav.flex span");
    private final By homeBreadcrumb = By.cssSelector("nav.flex a[href='/']");
    private final By currentPageBreadcrumb = By.cssSelector("nav.flex span.font-medium");

    public BreadcrumbComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isBreadcrumbVisible() {
        return isDisplayed(breadcrumbNav);
    }

    public List<String> getBreadcrumbTexts() {
        return getElements(breadcrumbLinks).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getCurrentPageText() {
        return isElementPresent(currentPageBreadcrumb) ? getText(currentPageBreadcrumb) : "";
    }

    public void clickHomeBreadcrumb() {
        waitAndClick(homeBreadcrumb);
        WaitUtils.waitForPageLoad();
    }

    public void clickBreadcrumbByText(String text) {
        getElements(breadcrumbLinks).stream()
                .filter(e -> e.getText().equalsIgnoreCase(text))
                .findFirst()
                .ifPresent(e -> {
                    e.click();
                    WaitUtils.waitForPageLoad();
                });
    }

    public int getBreadcrumbDepth() {
        return getElements(breadcrumbLinks).size();
    }

    public boolean containsBreadcrumb(String text) {
        return getBreadcrumbTexts().stream()
                .anyMatch(t -> t.equalsIgnoreCase(text));
    }
}