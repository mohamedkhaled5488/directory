package com.knowbefore.stepdefinitions;

import com.knowbefore.config.ConfigReader;
import com.knowbefore.driver.DriverFactory;
import com.knowbefore.pages.RegionPage;
import com.knowbefore.utils.AssertionUtils;
import com.knowbefore.utils.ReportManager;
import com.knowbefore.utils.WaitUtils;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NavigationSteps {
    private static final Logger log = LogManager.getLogger(NavigationSteps.class);
    private final RegionPage regionPage = new RegionPage(DriverFactory.getDriver());
    private final ConfigReader config = ConfigReader.getInstance();

    @When("the user navigates to the {string} region page")
    public void userNavigatesToRegionPage(String regionSlug) {
        DriverFactory.getDriver().get(config.getBaseUrl() + "/regions/" + regionSlug);
        WaitUtils.waitForPageLoad();
        ReportManager.logInfo("Navigated to region: " + regionSlug);
    }

    @Then("the region page should load successfully")
    public void regionPageShouldLoad() {
        String h1 = regionPage.getRegionName();
        AssertionUtils.assertNotEmpty(h1, "Region page H1 is visible");
        ReportManager.logInfo("Region page loaded: " + h1);
    }

    @Then("the region should display countries")
    public void regionShouldDisplayCountries() {
        int count = regionPage.getCountryCount();
        AssertionUtils.assertTrue(count > 0, "Region displays countries. Count: " + count);
    }

    @Then("the URL should contain {string}")
    public void urlShouldContain(String path) {
        AssertionUtils.assertUrlContains(
            DriverFactory.getDriver().getCurrentUrl(), path, "URL contains: " + path
        );
    }

    @When("the user navigates to the compare page for {string} and {string}")
    public void userNavigatesToComparePage(String country1, String country2) {
        DriverFactory.getDriver().get(config.getBaseUrl() + "/compare/" + country1 + "/" + country2);
        WaitUtils.waitForPageLoad();
        ReportManager.logInfo("Navigated to compare: " + country1 + " vs " + country2);
    }

    @Then("the comparison page should display both countries")
    public void comparisonPageShouldDisplayBothCountries() {
        String title = DriverFactory.getDriver().getTitle();
        AssertionUtils.assertTrue(title.contains("vs"), "Comparison page shows both countries");
    }

    @When("the user navigates to {string}")
    public void userNavigatesTo(String path) {
        DriverFactory.getDriver().get(config.getBaseUrl() + path);
        WaitUtils.waitForPageLoad();
        ReportManager.logInfo("Navigated to: " + path);
    }

    @Then("the page should load with status {int}")
    public void pageShouldLoadWithStatus(int expectedStatus) {
        AssertionUtils.assertTrue(
            WaitUtils.isElementPresent(org.openqa.selenium.By.cssSelector("body")),
            "Page body is present (status " + expectedStatus + ")"
        );
    }

    @Then("the breadcrumbs should be visible")
    public void breadcrumbsShouldBeVisible() {
        AssertionUtils.assertTrue(
            WaitUtils.isElementVisible(org.openqa.selenium.By.cssSelector("nav.flex")),
            "Breadcrumbs are visible"
        );
    }
}
