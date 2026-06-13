package com.knowbefore.stepdefinitions;

import com.knowbefore.driver.DriverFactory;
import com.knowbefore.pages.HomePage;
import com.knowbefore.utils.AssertionUtils;
import com.knowbefore.utils.ReportManager;
import com.knowbefore.utils.WaitUtils;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchSteps {
    private static final Logger log = LogManager.getLogger(SearchSteps.class);
    private final HomePage homePage = new HomePage(DriverFactory.getDriver());
    private List<WebElement> searchResults;

    @When("the user searches for {string}")
    public void userSearchesFor(String searchTerm) {
        homePage.searchFor(searchTerm);
        WaitUtils.hardWait(800);
        ReportManager.logInfo("Searched for: " + searchTerm);
    }

    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        searchResults = homePage.getSearchResults();
        AssertionUtils.assertTrue(searchResults.size() > 0, "Search results are displayed");
        ReportManager.logInfo("Search results count: " + searchResults.size());
    }

    @Then("no search results should be displayed")
    public void noSearchResultsShouldBeDisplayed() {
        searchResults = homePage.getSearchResults();
        AssertionUtils.assertTrue(searchResults.isEmpty(), "No search results for invalid input");
    }

    @Then("the search results should contain {string}")
    public void searchResultsShouldContain(String expected) {
        boolean found = homePage.getSearchResults().stream()
            .anyMatch(e -> e.getText().toLowerCase().contains(expected.toLowerCase()));
        AssertionUtils.assertTrue(found, "Search results contain: " + expected);
    }

    @When("the user clicks the first search result")
    public void userClicksFirstResult() {
        homePage.clickFirstSearchResult();
        WaitUtils.waitForPageLoad();
        ReportManager.logInfo("Clicked first search result");
    }

    @Then("the user should be navigated to a country or topic page")
    public void userShouldBeOnCountryOrTopicPage() {
        String url = DriverFactory.getDriver().getCurrentUrl();
        AssertionUtils.assertTrue(
            url.contains("theknowbefore.com/"),
            "User navigated to a valid page: " + url
        );
    }

    @Then("the search dropdown should appear")
    public void searchDropdownShouldAppear() {
        searchResults = homePage.getSearchResults();
        AssertionUtils.assertTrue(searchResults.size() > 0, "Search dropdown appeared");
    }

    @Then("the search results count should be at most {int}")
    public void searchResultsCountShouldBeAtMost(int maxCount) {
        int actual = homePage.getSearchResults().size();
        AssertionUtils.assertTrue(actual <= maxCount,
            "Search results (" + actual + ") at most " + maxCount);
    }
}
