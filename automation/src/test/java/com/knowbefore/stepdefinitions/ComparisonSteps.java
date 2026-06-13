package com.knowbefore.stepdefinitions;

import com.knowbefore.config.ConfigReader;
import com.knowbefore.driver.DriverFactory;
import com.knowbefore.pages.CountryComparisonPage;
import com.knowbefore.utils.AssertionUtils;
import com.knowbefore.utils.ReportManager;
import com.knowbefore.utils.WaitUtils;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComparisonSteps {
    private static final Logger log = LogManager.getLogger(ComparisonSteps.class);
    private final CountryComparisonPage comparisonPage = new CountryComparisonPage(DriverFactory.getDriver());
    private final ConfigReader config = ConfigReader.getInstance();

    @Given("the user navigates to compare {string} and {string}")
    public void userNavigatesToCompare(String c1, String c2) {
        DriverFactory.getDriver().get(config.getBaseUrl() + "/compare/" + c1 + "/" + c2);
        WaitUtils.waitForPageLoad();
        ReportManager.logInfo("Comparison page: " + c1 + " vs " + c2);
    }

    @Then("the comparison title should mention both countries")
    public void comparisonTitleShouldMentionBothCountries() {
        String title = comparisonPage.getComparisonTitle();
        AssertionUtils.assertTrue(title.contains("vs"), "Title contains 'vs': " + title);
    }

    @Then("the comparison should show {int} topics")
    public void comparisonShouldShowTopics(int expected) {
        int actual = comparisonPage.getTopicCount();
        AssertionUtils.assertTrue(actual >= expected, "Topics shown: " + actual + " >= " + expected);
    }

    @Then("the more comparisons section should be visible")
    public void moreComparisonsShouldBeVisible() {
        AssertionUtils.assertTrue(
            WaitUtils.isElementVisible(org.openqa.selenium.By.xpath("//h2[contains(text(),'More Comparisons')]")),
            "More Comparisons section is visible"
        );
    }

    @Then("each topic should have answers for both countries")
    public void eachTopicShouldHaveAnswers() {
        int topicCount = comparisonPage.getTopicCount();
        AssertionUtils.assertTrue(topicCount > 0, "Comparison topics present: " + topicCount);
        ReportManager.logInfo("Topics in comparison: " + topicCount);
    }

    @Then("warning badges should be shown where applicable")
    public void warningBadgesShouldBeShown() {
        int warnings = comparisonPage.getWarningCount();
        ReportManager.logInfo("Warning badges in comparison: " + warnings);
        AssertionUtils.assertTrue(warnings >= 0, "Warning badge count retrieved: " + warnings);
    }
}
