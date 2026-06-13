package com.knowbefore.hooks;

import com.knowbefore.driver.DriverFactory;
import com.knowbefore.config.ConfigReader;
import com.knowbefore.utils.ReportManager;
import com.knowbefore.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);
    private static final ConfigReader config = ConfigReader.getInstance();

    @Before
    public void setUp(Scenario scenario) {
        log.info("=== SCENARIO STARTED: {} ===", scenario.getName());
        ReportManager.createTest(scenario.getName(), scenario.getId());
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(config.getBaseUrl());
        log.info("Browser launched and navigated to: {}", config.getBaseUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("SCENARIO FAILED: {}", scenario.getName());
            byte[] screenshot = ScreenshotUtils.captureScreenshotAsBytes(DriverFactory.getDriver());
            if (screenshot.length > 0) {
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
            }
            String screenshotPath = ScreenshotUtils.captureScreenshot(
                DriverFactory.getDriver(), scenario.getName());
            ReportManager.logFail("Scenario Failed: " + scenario.getName());
            if (screenshotPath != null) {
                ReportManager.logScreenshot(screenshotPath, "Failure Screenshot");
            }
        } else {
            ReportManager.logPass("Scenario Passed: " + scenario.getName());
            log.info("=== SCENARIO PASSED: {} ===", scenario.getName());
        }
        DriverFactory.quitDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("Step failed in scenario: {}", scenario.getName());
        }
    }
}
