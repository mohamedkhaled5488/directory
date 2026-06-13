package com.knowbefore.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class LogManager_ {

    private LogManager_() {}

    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    public static Logger getLogger(String name) {
        return LogManager.getLogger(name);
    }

    public static void setTestContext(String testName, String scenarioId) {
        ThreadContext.put("testName", testName);
        ThreadContext.put("scenarioId", scenarioId);
        ThreadContext.put("threadId", String.valueOf(Thread.currentThread().getId()));
    }

    public static void clearTestContext() {
        ThreadContext.clearAll();
    }

    public static void logTestStart(Logger log, String testName) {
        log.info("======== TEST START: {} ========", testName);
    }

    public static void logTestEnd(Logger log, String testName, boolean passed) {
        if (passed) {
            log.info("======== TEST PASS: {} ========", testName);
        } else {
            log.error("======== TEST FAIL: {} ========", testName);
        }
    }

    public static void logStep(Logger log, String stepDescription) {
        log.info("STEP: {}", stepDescription);
    }

    public static void logPageNavigation(Logger log, String url) {
        log.info("NAVIGATE: {}", url);
    }

    public static void logAssertion(Logger log, String message, boolean result) {
        if (result) {
            log.info("ASSERT PASS: {}", message);
        } else {
            log.warn("ASSERT FAIL: {}", message);
        }
    }

    public static void logException(Logger log, String context, Throwable throwable) {
        log.error("EXCEPTION in {}: {}", context, throwable.getMessage(), throwable);
    }

    public static void logInfo(Logger log, String message) {
        log.info(message);
    }

    public static void logDebug(Logger log, String message) {
        log.debug(message);
    }

    public static void logWarn(Logger log, String message) {
        log.warn(message);
    }

    public static void logError(Logger log, String message) {
        log.error(message);
    }
}