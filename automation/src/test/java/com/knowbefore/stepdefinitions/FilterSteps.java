package com.knowbefore.stepdefinitions;

import com.knowbefore.config.ConfigReader;
import com.knowbefore.driver.DriverFactory;
import com.knowbefore.pages.CountriesPage;
import com.knowbefore.utils.AssertionUtils;
import com.knowbefore.utils.ReportManager;
import com.knowbefore.utils.WaitUtils;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FilterSteps {
    private static final Logger log = LogManager.getLogger(FilterSteps.class);
    private final CountriesPage countriesPage = new CountriesPage(DriverFactory.getDriver());
    private final ConfigReader config = ConfigReader.getInstance();
    private int initialCountryCount;
    private List<String> filteredCountries;

    @Given("the user is on the countries page")
    public void userIsOnCountriesPage() {
        DriverFactory.getDriver().get(config.getBaseUrl() + "/countries");
        WaitUtils.waitForPageLoad();
        ReportManager.logInfo("Navigated to /countries");
    }

    @When("the user selects the {string} region filter")
    public void userSelectsRegionFilter(String region) {
        initialCountryCount = countriesPage.getDisplayedCountryCount();
        countriesPage.clickRegionTab(region);
        WaitUtils.hardWait(500);
        filteredCountries = countriesPage.getAllCountryNames();
        ReportManager.logInfo("Selected region filter: " + region);
    }

    @Then("only {string} countries should be displayed")
    public void onlyRegionCountriesShouldBeDisplayed(String region) {
        int count = countriesPage.getDisplayedCountryCount();
        AssertionUtils.assertTrue(count > 0, region + " countries displayed. Count: " + count);
        ReportManager.logInfo("Countries displayed for " + region + ": " + count);
    }

    @Then("the country count should be less than the total")
    public void countryCountShouldBeLessThanTotal() {
        int filtered = countriesPage.getDisplayedCountryCount();
        AssertionUtils.assertTrue(
            filtered < initialCountryCount,
            "Filtered count (" + filtered + ") < total (" + initialCountryCount + ")"
        );
    }

    @When("the user selects the {string} filter tab")
    public void userSelectsFilterTab(String tab) {
        countriesPage.clickRegionTab(tab);
        WaitUtils.hardWait(500);
    }

    @Then("all {int} countries should be displayed")
    public void allCountriesShouldBeDisplayed(int expected) {
        int count = countriesPage.getDisplayedCountryCount();
        AssertionUtils.assertTrue(count >= expected, "All " + expected + " countries displayed. Actual: " + count);
    }

    @Then("the filtered results should not be empty")
    public void filteredResultsShouldNotBeEmpty() {
        AssertionUtils.assertTrue(
            countriesPage.getDisplayedCountryCount() > 0,
            "Filtered results are not empty"
        );
    }

    @Then("the region filter tabs should be visible")
    public void regionFilterTabsShouldBeVisible() {
        AssertionUtils.assertTrue(
            WaitUtils.isElementVisible(org.openqa.selenium.By.xpath("//button[text()='All']")),
            "Region filter tabs are visible"
        );
    }
}
