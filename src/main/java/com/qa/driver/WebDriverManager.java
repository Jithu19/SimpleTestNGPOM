package com.qa.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {

    private static volatile WebDriverManager driverInstance;
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private WebDriverManager() {
    }

    private void initDriver(String browser) {

        switch (browser.toLowerCase()) {
            case "chrome":
                tlDriver.set(new ChromeDriver());
                break;
            case "firefox":
                tlDriver.set(new FirefoxDriver());
                break;
            case "edge":
                tlDriver.set(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Invalid Browser input: " + browser);
        }
    }

    public static WebDriverManager getInstance(String browser) {
        if(driverInstance == null) {
            synchronized (WebDriverManager.class) {
                if(driverInstance == null) {
                    driverInstance = new WebDriverManager();
                }
            }
        }

        if(tlDriver.get() == null) {
            driverInstance.initDriver(browser);
        }

        return driverInstance;
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if(tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }

}
