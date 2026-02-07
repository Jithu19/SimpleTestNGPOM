package com.qa.driver;

import org.openqa.selenium.WebDriver;

public final class WebDriverManager {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private WebDriverManager() {}

    public static void initDriver(String browser) {
        if (tlDriver.get() == null) {
            WebDriver driver = DriverFactory.createDriver(browser);
            tlDriver.set(driver);
        }
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}