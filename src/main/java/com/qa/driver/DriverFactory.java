package com.qa.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {

    private DriverFactory() {}

    public static WebDriver createDriver(String browser) {

        if (browser == null || browser.isBlank()) {
            throw new IllegalArgumentException("Browser name is null or empty");
        }

        return switch (browser.toLowerCase().strip()) {

            case "chrome" -> {

                ChromeOptions options = new ChromeOptions();

                options.addArguments(
                        "--incognito",
                        "--disable-notifications",
                        "--disable-extensions",
                        "--disable-infobars",
                        "--no-first-run",
                        "--no-default-browser-check",
                        "--disable-popup-blocking",
                        "--remote-allow-origins=*"
                );

                // Force a clean profile every run
                options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/chrome-profile-" + System.nanoTime());

                yield new ChromeDriver(options);
            }

            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.addPreference("dom.webnotifications.enabled", false);
                options.addPreference("geo.enabled", false);
                options.addPreference("signon.rememberSignons", false);
                yield new FirefoxDriver(options);
            }

            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--disable-notifications");
                yield new EdgeDriver(options);
            }

            default -> throw new IllegalArgumentException("Unsupported Browser: " + browser);
        };
    }
}