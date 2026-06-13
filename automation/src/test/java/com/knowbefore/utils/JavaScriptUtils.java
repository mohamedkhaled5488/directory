package com.knowbefore.utils;

import com.knowbefore.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    private static final Logger log = LogManager.getLogger(JavaScriptUtils.class);

    private JavaScriptUtils() {}

    private static JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) DriverFactory.getDriver();
    }

    public static void clickElement(WebElement element) {
        log.debug("JS click on element");
        getJSExecutor().executeScript("arguments[0].click();", element);
    }

    public static void scrollToElement(WebElement element) {
        getJSExecutor().executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public static void scrollToTop() {
        getJSExecutor().executeScript("window.scrollTo(0, 0);");
    }

    public static void scrollToBottom() {
        getJSExecutor().executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void scrollBy(int x, int y) {
        getJSExecutor().executeScript(String.format("window.scrollBy(%d, %d);", x, y));
    }

    public static void highlightElement(WebElement element) {
        String originalStyle = element.getAttribute("style");
        getJSExecutor().executeScript(
                "arguments[0].setAttribute('style', 'border: 3px solid red; background: yellow;');", element);
        WaitUtils.hardWait(300);
        getJSExecutor().executeScript(
                "arguments[0].setAttribute('style', '" + originalStyle + "');", element);
    }

    public static void setElementValue(WebElement element, String value) {
        getJSExecutor().executeScript("arguments[0].value='" + value + "';", element);
    }

    public static String getPageTitle() {
        return (String) getJSExecutor().executeScript("return document.title;");
    }

    public static String getCurrentUrl() {
        return (String) getJSExecutor().executeScript("return window.location.href;");
    }

    public static boolean isPageLoaded() {
        return getJSExecutor().executeScript("return document.readyState").equals("complete");
    }

    public static Long getElementCount(String selector) {
        return (Long) getJSExecutor().executeScript(
                "return document.querySelectorAll('" + selector + "').length;");
    }

    public static void removeElement(WebElement element) {
        getJSExecutor().executeScript("arguments[0].remove();", element);
    }
}
