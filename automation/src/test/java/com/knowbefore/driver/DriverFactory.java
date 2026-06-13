package com.knowbefore.driver;

import com.knowbefore.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {

    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ConfigReader config = ConfigReader.getInstance();

    private DriverFactory() {}

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void initDriver() {
        initDriver(config.getBrowser());
    }

    public static void initDriver(String browserName) {
        if (driverThreadLocal.get() != null) {
            log.warn("Driver already initialized for thread: {}", Thread.currentThread().getId());
            return;
        }

        BrowserType browserType = BrowserType.fromString(browserName);
        WebDriver driver;

        switch (browserType) {
            case FIREFOX:
                driver = createFirefoxDriver();
                break;
            case EDGE:
                driver = createEdgeDriver();
                break;
            case CHROME:
            default:
                driver = createChromeDriver();
                break;
        }

        configureDriver(driver);
        driverThreadLocal.set(driver);
        log.info("WebDriver initialized: {} | Thread: {}", browserName, Thread.currentThread().getId());
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (config.isHeadless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        log.info("Creating ChromeDriver | Headless: {}", config.isHeadless());
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (config.isHeadless()) {
            options.addArguments("-headless");
        }
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        log.info("Creating FirefoxDriver | Headless: {}", config.isHeadless());
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (config.isHeadless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1920,1080");
        log.info("Creating EdgeDriver | Headless: {}", config.isHeadless());
        return new EdgeDriver(options);
    }

    private static void configureDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(config.getPageLoadTimeout()));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        if (config.isBrowserMaximize()) {
            driver.manage().window().maximize();
        }
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
                log.info("WebDriver quit | Thread: {}", Thread.currentThread().getId());
            } catch (Exception e) {
                log.error("Error quitting driver", e);
            } finally {
                driverThreadLocal.remove();
            }
        }
    }

    public static boolean isDriverInitialized() {
        return driverThreadLocal.get() != null;
    }
}
