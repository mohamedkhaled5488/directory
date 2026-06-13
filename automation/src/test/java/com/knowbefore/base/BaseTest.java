package com.knowbefore.base;

import com.knowbefore.config.ConfigReader;
import com.knowbefore.driver.DriverFactory;
import com.knowbefore.utils.ReportManager;
import com.knowbefore.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class BaseTest {

    protected static final Logger log = LogManager.getLogger(BaseTest.class);
    protected static final ConfigReader config = ConfigReader.getInstance();

    @BeforeSuite
    public void beforeSuite() {
        ReportManager.initReports();
        log.info("========== TEST SUITE STARTED ==========");
    }

    @AfterSuite
    public void afterSuite() {
        ReportManager.flushReports();
        log.info("========== TEST SUITE COMPLETED ==========");
    }

    @BeforeMethod
    public void setUp(Method method) {
        log.info("---------- TEST STARTED: {} ----------", method.getName());
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(config.getBaseUrl());
        log.info("Navigated to base URL: {}", config.getBaseUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            log.error("TEST FAILED: {}", result.getName());
            ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            log.info("TEST PASSED: {}", result.getName());
        } else {
            log.warn("TEST SKIPPED: {}", result.getName());
        }
        DriverFactory.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    protected void navigateTo(String path) {
        String url = config.getBaseUrl() + path;
        getDriver().get(url);
        log.info("Navigated to: {}", url);
    }
}
