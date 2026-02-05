package com.qa.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;

import java.io.ByteArrayInputStream;

public class AllureUtils {

    public static void capture(WebDriver driver, String name) {
        byte[] screenshot =
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment(name,
                new ByteArrayInputStream(screenshot));
    }

    public static void log(String message) {
        Allure.addAttachment("Log", message);
    }

    public static void highlightAndCapture(
            WebDriver driver,
            By locator,
            String attachmentName
    ) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            WebElement element = driver.findElement(locator);

            // highlight element
            js.executeScript(
                    "arguments[0].style.border='3px solid red';" +
                            "arguments[0].style.backgroundColor='yellow';",
                    element
            );

            // wait a bit so highlight is visible
            Thread.sleep(300);

            // take screenshot
            byte[] screenshot =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    attachmentName,
                    new ByteArrayInputStream(screenshot)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
