package com.knowbefore.utils;

import com.knowbefore.config.ConfigReader;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    private static final Logger log = LogManager.getLogger(ScreenshotUtils.class);
    private static final ConfigReader config = ConfigReader.getInstance();

    private ScreenshotUtils() {}

    public static String captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            log.warn("Cannot capture screenshot: WebDriver is null");
            return null;
        }
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = sanitizeFileName(testName) + "_" + timestamp + ".png";
            String screenshotDir = config.getScreenshotsPath();

            File screenshotDir_ = new File(screenshotDir);
            if (!screenshotDir_.exists()) {
                screenshotDir_.mkdirs();
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destPath = screenshotDir + fileName;
            FileUtils.copyFile(srcFile, new File(destPath));

            log.info("Screenshot captured: {}", destPath);
            return new File(destPath).getAbsolutePath();
        } catch (IOException e) {
            log.error("Failed to capture screenshot for test: {}", testName, e);
            return null;
        }
    }

    public static byte[] captureScreenshotAsBytes(WebDriver driver) {
        if (driver == null) {
            log.warn("Cannot capture screenshot: WebDriver is null");
            return new byte[0];
        }
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            log.error("Failed to capture screenshot as bytes", e);
            return new byte[0];
        }
    }

    private static String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9_-]", "_");
    }
}
