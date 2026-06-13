package com.knowbefore.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Logger log = LogManager.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();
    private static ConfigReader instance;
    private static final String CONFIG_FILE = "src/test/resources/config.properties";

    private ConfigReader() {
        loadProperties();
    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
            log.info("Configuration loaded successfully from: {}", CONFIG_FILE);
        } catch (IOException e) {
            log.error("Failed to load configuration from: {}", CONFIG_FILE, e);
            loadFromClasspath();
        }
    }

    private void loadFromClasspath() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
                log.info("Configuration loaded from classpath");
            }
        } catch (IOException e) {
            log.error("Failed to load config from classpath", e);
        }
    }

    public String getProperty(String key) {
        String value = System.getProperty(key);
        if (value != null) {
            return value;
        }
        value = properties.getProperty(key);
        if (value == null) {
            log.warn("Property not found: {}", key);
        }
        return value;
    }

    public String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    public String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }

    public boolean isBrowserMaximize() {
        return Boolean.parseBoolean(getProperty("browser.maximize", "true"));
    }

    public String getBaseUrl() {
        return getProperty("base.url", "https://theknowbefore.com");
    }

    public int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    public int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "20"));
    }

    public int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout", "30"));
    }

    public int getShortWait() {
        return Integer.parseInt(getProperty("short.wait", "5"));
    }

    public String getReportsPath() {
        return getProperty("reports.path", "test-output/reports/");
    }

    public String getScreenshotsPath() {
        return getProperty("screenshots.path", "test-output/screenshots/");
    }

    public String getReportName() {
        return getProperty("report.name", "KnowBefore_Test_Report");
    }

    public String getLogPath() {
        return getProperty("log.path", "logs/");
    }

    public String getTestDataPath() {
        return getProperty("testdata.path", "src/test/resources/testdata/");
    }

    public String getEnvironment() {
        return getProperty("environment", "production");
    }

    public int getThreadCount() {
        return Integer.parseInt(getProperty("thread.count", "3"));
    }
}
