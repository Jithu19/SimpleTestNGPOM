package com.qa.utils;

import org.openqa.selenium.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class GeneralUtils {

    private static final int DEFAULT_WAIT = 10;

    /* =========================
       WAIT UTILS
       ========================= */

    private static WebElement waitForElementVisible(WebDriver driver, By locator) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new AssertionError(
                    "Timeout: Element not visible within " + DEFAULT_WAIT + "s → " + locator, e);
        }
    }

    private static WebElement waitForElementClickable(WebDriver driver, By locator) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT))
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new AssertionError(
                    "Timeout: Element not clickable within " + DEFAULT_WAIT + "s → " + locator, e);
        }
    }

    /* =========================
       ELEMENT METHODS
       ========================= */

    public static boolean isElementDisplayed(WebDriver driver, By locator) {
        try {
            return waitForElementVisible(driver, locator).isDisplayed();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Element not found: " + locator, e);
        }
    }

    public static void clickElement(WebDriver driver, By locator) {
        try {
            waitForElementClickable(driver, locator).click();
        } catch (ElementClickInterceptedException e) {
            throw new AssertionError("Element click intercepted: " + locator, e);
        } catch (StaleElementReferenceException e) {
            throw new AssertionError("Stale element reference while clicking: " + locator, e);
        }
    }

    public static void clickElement(WebDriver driver, WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT))
                    .until(ExpectedConditions.elementToBeClickable(element))
                    .click();
        } catch (TimeoutException e) {
            throw new AssertionError("Timeout: WebElement not clickable", e);
        } catch (StaleElementReferenceException e) {
            throw new AssertionError("Stale WebElement reference during click", e);
        }
    }

    public static String getTextValue(WebDriver driver, By locator) {
        try {
            return waitForElementVisible(driver, locator).getText();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Element not found while getting text: " + locator, e);
        }
    }

    public static void setTextValue(WebDriver driver, By locator, String value) {
        try {
            WebElement element = waitForElementVisible(driver, locator);
            element.clear();
            element.sendKeys(value);
        } catch (InvalidElementStateException e) {
            throw new AssertionError("Cannot set text. Invalid element state: " + locator, e);
        }
    }

    /* =========================
       DROPDOWN METHODS
       ========================= */

    public static void selectDropDownByValue(WebDriver driver, By locator, String value) {
        try {
            WebElement element = waitForElementVisible(driver, locator);
            Select select = new Select(element);
            select.selectByValue(value);
        } catch (NoSuchElementException e) {
            throw new AssertionError(
                    "Dropdown value not found: " + value + " → " + locator, e);
        }
    }

    public static String getDropDownValue(WebDriver driver, By locator) {
        try {
            WebElement element = waitForElementVisible(driver, locator);
            Select select = new Select(element);
            return select.getFirstSelectedOption().getText();
        } catch (UnexpectedTagNameException e) {
            throw new AssertionError(
                    "Element is not a dropdown: " + locator, e);
        }
    }

    /* =========================
       PROPERTY FILE
       ========================= */

    public static String getProperty(String filePath, String propertyName) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
            String value = properties.getProperty(propertyName);

            if (value == null) {
                throw new AssertionError(
                        "Property '" + propertyName + "' not found in file: " + filePath);
            }
            return value;

        } catch (IOException e) {
            throw new AssertionError(
                    "Unable to read config file: " + filePath, e);
        }
    }
}