package com.knowbefore.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features/regression",
    glue = {"com.knowbefore.stepdefinitions", "com.knowbefore.hooks"},
    plugin = {
        "pretty",
        "html:test-output/reports/regression-report.html",
        "json:test-output/reports/regression-report.json",
        "junit:test-output/reports/regression-report.xml"
    },
    monochrome = true,
    tags = "@regression"
)
public class RegressionTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
