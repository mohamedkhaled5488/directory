package com.knowbefore.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.knowbefore.config.ConfigReader;
import com.knowbefore.constants.AppConstants;
import com.knowbefore.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager implements ITestListener {

    private static final Logger log = LogManager.getLogger(ReportManager.class);
    private static final ConfigReader config = ConfigReader.getInstance();
    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static void initReports() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportsPath = config.getReportsPath();
        new File(reportsPath).mkdirs();

        String reportFilePath = reportsPath + AppConstants.REPORT_NAME + "_" + timestamp + ".html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle(AppConstants.REPORT_TITLE);
        sparkReporter.config().setReportName(AppConstants.REPORT_NAME);
        sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application", "KnowBefore - theknowbefore.com");
        extentReports.setSystemInfo("Browser", config.getBrowser());
        extentReports.setSystemInfo("Environment", config.getEnvironment());
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));

        log.info("ExtentReports initialized: {}", reportFilePath);
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest test = extentReports.createTest(testName);
        extentTestThreadLocal.set(test);
        return test;
    }

    public static ExtentTest createTest(String testName, String description) {
        ExtentTest test = extentReports.createTest(testName, description);
        extentTestThreadLocal.set(test);
        return test;
    }

    public static ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    public static void logPass(String message) {
        if (getTest() != null) getTest().log(Status.PASS, message);
        log.info("PASS: {}", message);
    }

    public static void logFail(String message) {
        if (getTest() != null) getTest().log(Status.FAIL, message);
        log.error("FAIL: {}", message);
    }

    public static void logInfo(String message) {
        if (getTest() != null) getTest().log(Status.INFO, message);
        log.info("INFO: {}", message);
    }

    public static void logWarning(String message) {
        if (getTest() != null) getTest().log(Status.WARNING, message);
        log.warn("WARNING: {}", message);
    }

    public static void logScreenshot(String screenshotPath, String message) {
        try {
            if (getTest() != null && screenshotPath != null) {
                getTest().addScreenCaptureFromPath(screenshotPath, message);
            }
        } catch (Exception e) {
            log.error("Failed to add screenshot to report", e);
        }
    }

    public static void flushReports() {
        if (extentReports != null) {
            extentReports.flush();
            log.info("ExtentReports flushed successfully");
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        createTest(result.getName());
        logInfo("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logPass("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = ScreenshotUtils.captureScreenshot(
                DriverFactory.getDriver(), result.getName());
        logFail("Test Failed: " + result.getName() + " - " + result.getThrowable().getMessage());
        if (screenshotPath != null) {
            logScreenshot(screenshotPath, "Failure Screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logWarning("Test Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Test Suite Started: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        flushReports();
        log.info("Test Suite Finished: {}", context.getName());
    }
}
