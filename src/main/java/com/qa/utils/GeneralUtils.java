package com.qa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GeneralUtils {

    public static boolean isElementDisplayed(WebDriver driver, By webElement) {

        return driver.findElement(webElement).isDisplayed();

    }

    public static void clickElement(WebDriver driver, By webElement) {

        driver.findElement(webElement).click();

    }

    public static String getTextValue(WebDriver driver, By webElement) {

        return driver.findElement(webElement).getText();

    }

    public static void setTextValue(WebDriver driver, By webElement, String value) {

        driver.findElement(webElement).clear();
        driver.findElement(webElement).sendKeys(value);

    }

    public static String getProperty(String filePath, String propertyName) {

        String propertyValue = null;
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(fis);
            propertyValue = properties.getProperty(propertyName);

        } catch (IOException e) {
            System.err.println("Error reading from properties file.");
            e.printStackTrace();
        }

        return propertyValue;
    }

}
