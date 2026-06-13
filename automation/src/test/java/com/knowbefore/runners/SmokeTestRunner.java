package com.knowbefore.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features/smoke",
    glue = {"com.knowbefore.stepdefinitions", "com.knowbefore.hooks"},
    plugin = {
        "pretty",
        "html:test-output/reports/smoke-report.html",
        "json:test-output/reports/smoke-report.json",
        "junit:test-output/reports/smoke-report.xml"
    },
    monochrome = true,
    tags = "@smoke"
)
public class SmokeTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
