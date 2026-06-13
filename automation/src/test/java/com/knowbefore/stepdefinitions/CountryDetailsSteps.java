package com.knowbefore.stepdefinitions;

import com.knowbefore.config.ConfigReader;
import com.knowbefore.driver.DriverFactory;
import com.knowbefore.pages.CountryDetailsPage;
import com.knowbefore.pages.TopicDetailPage;
import com.knowbefore.utils.AssertionUtils;
import com.knowbefore.utils.ReportManager;
import com.knowbefore.utils.WaitUtils;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CountryDetailsSteps {
    private static final Logger log = LogManager.getLogger(CountryDetailsSteps.class);
    private final CountryDetailsPage countryPage = new CountryDetailsPage(DriverFactory.getDriver());
    private final TopicDetailPage topicPage = new TopicDetailPage(DriverFactory.getDriver());
    private final ConfigReader config = ConfigReader.getInstance();

    @Given("the user navigates to the {string} country page")
    public void userNavigatesToCountryPage(String countrySlug) {
        DriverFactory.getDriver().get(config.getBaseUrl() + "/" + countrySlug);
        WaitUtils.waitForPageLoad();
        ReportManager.logInfo("Navigated to country page: " + countrySlug);
    }

    @Given("the user navigates to the {string} topic on the {string} page")
    public void userNavigatesToTopicPage(String topicSlug, String countrySlug) {
        DriverFactory.getDriver().get(config.getBaseUrl() + "/" + countrySlug + "/" + topicSlug);
        WaitUtils.waitForPageLoad();
        ReportManager.logInfo("Navigated to: /" + countrySlug + "/" + topicSlug);
    }

    @Then("the country name should be visible")
    public void countryNameShouldBeVisible() {
        String name = countryPage.getCountryName();
        AssertionUtils.assertNotEmpty(name, "Country name");
        ReportManager.logInfo("Country name: " + name);
    }

    @Then("the country page should display {string}")
    public void countryPageShouldDisplay(String countryName) {
        String actualName = countryPage.getCountryName();
        AssertionUtils.assertContains(actualName, countryName, "Country name on page");
    }

    @Then("the currency information should be displayed")
    public void currencyInfoShouldBeDisplayed() {
        String currency = countryPage.getCurrency();
        AssertionUtils.assertNotEmpty(currency, "Currency information");
        ReportManager.logInfo("Currency: " + currency);
    }

    @Then("the language information should be displayed")
    public void languageInfoShouldBeDisplayed() {
        String language = countryPage.getLanguage();
        AssertionUtils.assertNotEmpty(language, "Language information");
        ReportManager.logInfo("Language: " + language);
    }

    @Then("the topic count should be {int}")
    public void topicCountShouldBe(int expected) {
        int actual = countryPage.getTopicCount();
        AssertionUtils.assertEquals(String.valueOf(actual), String.valueOf(expected), "Topic count");
    }

    @Then("the quick answer should contain {string}")
    public void quickAnswerShouldContain(String expected) {
        String qa = topicPage.getQuickAnswer();
        AssertionUtils.assertContains(qa, expected, "Quick answer contains: " + expected);
    }

    @Then("the tourist tips should be displayed")
    public void touristTipsShouldBeDisplayed() {
        int tipsCount = topicPage.getTouristTips().size();
        AssertionUtils.assertTrue(tipsCount >= 3, "Tourist tips displayed. Count: " + tipsCount);
    }

    @Then("the warning box should be visible")
    public void warningBoxShouldBeVisible() {
        AssertionUtils.assertTrue(topicPage.isWarningBoxVisible(), "Warning box is visible");
    }

    @Then("the page should show comparison with other countries")
    public void pageShouldShowComparison() {
        AssertionUtils.assertTrue(
            WaitUtils.isElementVisible(
                org.openqa.selenium.By.xpath("//h2[contains(text(),'Compare') or contains(text(),'Other')]")),
            "Comparison section is visible"
        );
    }

    @Then("the stats bar should be visible")
    public void statsBarShouldBeVisible() {
        AssertionUtils.assertTrue(countryPage.isStatsBarVisible(), "Stats bar is visible");
    }

    @Then("the {int} topic cards should be displayed")
    public void topicCardsShouldBeDisplayed(int expected) {
        int count = countryPage.getTopicCount();
        AssertionUtils.assertTrue(count >= expected, "Topic cards count >= " + expected);
    }
}
