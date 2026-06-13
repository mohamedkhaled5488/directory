package com.knowbefore.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class AssertionUtils {

    private static final Logger log = LogManager.getLogger(AssertionUtils.class);

    private AssertionUtils() {}

    public static void assertEquals(String actual, String expected, String message) {
        log.info("Assert equals - Expected: '{}' | Actual: '{}'", expected, actual);
        Assertions.assertThat(actual).as(message).isEqualTo(expected);
        ReportManager.logPass(message + " | Expected: " + expected + " | Actual: " + actual);
    }

    public static void assertContains(String actual, String expected, String message) {
        log.info("Assert contains - Expected to contain: '{}' | Actual: '{}'", expected, actual);
        Assertions.assertThat(actual).as(message).containsIgnoringCase(expected);
        ReportManager.logPass(message + " | Contains: " + expected);
    }

    public static void assertTrue(boolean condition, String message) {
        log.info("Assert true: {}", message);
        Assertions.assertThat(condition).as(message).isTrue();
        ReportManager.logPass(message);
    }

    public static void assertFalse(boolean condition, String message) {
        log.info("Assert false: {}", message);
        Assertions.assertThat(condition).as(message).isFalse();
        ReportManager.logPass(message);
    }

    public static void assertNotNull(Object obj, String message) {
        log.info("Assert not null: {}", message);
        Assertions.assertThat(obj).as(message).isNotNull();
        ReportManager.logPass(message + " is not null");
    }

    public static void assertNotEmpty(String value, String message) {
        log.info("Assert not empty: {}", message);
        Assertions.assertThat(value).as(message).isNotBlank();
        ReportManager.logPass(message + " is not empty");
    }

    public static void assertListNotEmpty(List<?> list, String message) {
        log.info("Assert list not empty: {}", message);
        Assertions.assertThat(list).as(message).isNotEmpty();
        ReportManager.logPass(message + " list is not empty");
    }

    public static void assertListSize(List<?> list, int expectedSize, String message) {
        log.info("Assert list size - Expected: {} | Actual: {}", expectedSize, list.size());
        Assertions.assertThat(list).as(message).hasSize(expectedSize);
        ReportManager.logPass(message + " | Size: " + expectedSize);
    }

    public static void assertElementVisible(WebElement element, String message) {
        log.info("Assert element visible: {}", message);
        Assertions.assertThat(element.isDisplayed()).as(message + " should be visible").isTrue();
        ReportManager.logPass(message + " is visible");
    }

    public static void assertElementEnabled(WebElement element, String message) {
        log.info("Assert element enabled: {}", message);
        Assertions.assertThat(element.isEnabled()).as(message + " should be enabled").isTrue();
        ReportManager.logPass(message + " is enabled");
    }

    public static void assertUrlContains(String driver_url, String expected, String message) {
        log.info("Assert URL contains: {}", expected);
        Assertions.assertThat(driver_url).as(message).containsIgnoringCase(expected);
        ReportManager.logPass(message + " URL contains: " + expected);
    }

    public static void assertPageTitle(String actualTitle, String expectedTitle) {
        log.info("Assert page title - Expected: '{}' | Actual: '{}'", expectedTitle, actualTitle);
        Assertions.assertThat(actualTitle).as("Page title mismatch").containsIgnoringCase(expectedTitle);
        ReportManager.logPass("Page title verified: " + expectedTitle);
    }

    public static void softAssertEquals(String actual, String expected, String message) {
        try {
            assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            ReportManager.logWarning("Soft Assert Failed - " + message + ": " + e.getMessage());
            log.warn("Soft assertion failed: {}", message);
        }
    }
}
