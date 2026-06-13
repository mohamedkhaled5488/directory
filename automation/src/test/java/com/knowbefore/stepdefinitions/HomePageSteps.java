package com.knowbefore.stepdefinitions;

import com.knowbefore.config.ConfigReader;
import com.knowbefore.driver.DriverFactory;
import com.knowbefore.pages.HomePage;
import com.knowbefore.pages.NavigationComponent;
import com.knowbefore.utils.AssertionUtils;
import com.knowbefore.utils.ReportManager;
import com.knowbefore.utils.WaitUtils;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePageSteps {
    private static final Logger log = LogManager.getLogger(HomePageSteps.class);
    private final HomePage homePage = new HomePage(DriverFactory.getDriver());
    private final NavigationComponent nav = new NavigationComponent(DriverFactory.getDriver());
    private final ConfigReader config = ConfigReader.getInstance();

    @Given("the user is on the KnowBefore homepage")
    public void userIsOnHomepage() {
        DriverFactory.getDriver().get(config.getBaseUrl());
        WaitUtils.waitForPageLoad();
        ReportManager.logInfo("Navigated to KnowBefore homepage");
    }

    @Then("the homepage should load successfully")
    public void homepageShouldLoadSuccessfully() {
        AssertionUtils.assertTrue(
            DriverFactory.getDriver().getTitle().contains("KnowBefore"),
            "Homepage title contains KnowBefore"
        );
        AssertionUtils.assertTrue(homePage.isSearchBarVisible(), "Search bar is visible");
        ReportManager.logPass("Homepage loaded successfully");
    }

    @Then("the page title should contain {string}")
    public void pageTitleShouldContain(String expectedTitle) {
        String actualTitle = DriverFactory.getDriver().getTitle();
        AssertionUtils.assertContains(actualTitle, expectedTitle, "Page title verification");
    }

    @Then("the navigation bar should be visible")
    public void navigationBarShouldBeVisible() {
        AssertionUtils.assertTrue(nav.isNavBarVisible(), "Navigation bar is visible");
    }

    @Then("the search bar should be visible on the homepage")
    public void searchBarShouldBeVisible() {
        AssertionUtils.assertTrue(homePage.isSearchBarVisible(), "Search bar is visible");
    }

    @Then("the country grid should display countries")
    public void countryGridShouldDisplayCountries() {
        int count = homePage.getCountryCardCount();
        AssertionUtils.assertTrue(count > 0, "Country grid displays countries. Count: " + count);
        ReportManager.logInfo("Country cards visible: " + count);
    }

    @When("the user clicks on the Countries navigation link")
    public void userClicksCountriesNav() {
        nav.clickCountriesLink();
        WaitUtils.waitForPageLoad();
        ReportManager.logInfo("Clicked Countries navigation link");
    }

    @When("the user clicks on the Topics navigation link")
    public void userClicksTopicsNav() {
        nav.clickTopicsLink();
        WaitUtils.waitForPageLoad();
        ReportManager.logInfo("Clicked Topics navigation link");
    }

    @Then("the user should be on the countries page")
    public void userShouldBeOnCountriesPage() {
        AssertionUtils.assertUrlContains(
            DriverFactory.getDriver().getCurrentUrl(), "/countries", "URL contains /countries"
        );
    }

    @Then("the user should be on the topics page")
    public void userShouldBeOnTopicsPage() {
        AssertionUtils.assertUrlContains(
            DriverFactory.getDriver().getCurrentUrl(), "/topics", "URL contains /topics"
        );
    }

    @When("the user clicks the KnowBefore logo")
    public void userClicksLogo() {
        nav.clickLogo();
        WaitUtils.waitForPageLoad();
    }

    @Then("the user should be redirected to the homepage")
    public void userShouldBeOnHomepage() {
        String url = DriverFactory.getDriver().getCurrentUrl();
        AssertionUtils.assertTrue(
            url.equals(config.getBaseUrl() + "/") || url.equals(config.getBaseUrl()),
            "User is on homepage"
        );
    }
}
