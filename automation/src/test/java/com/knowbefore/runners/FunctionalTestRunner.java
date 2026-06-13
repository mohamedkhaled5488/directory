package com.knowbefore.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features/functional",
    glue = {"com.knowbefore.stepdefinitions", "com.knowbefore.hooks"},
    plugin = {
        "pretty",
        "html:test-output/reports/functional-report.html",
        "json:test-output/reports/functional-report.json"
    },
    monochrome = true,
    tags = "@functional"
)
public class FunctionalTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
