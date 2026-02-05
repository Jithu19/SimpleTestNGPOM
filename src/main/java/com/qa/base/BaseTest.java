package com.qa.base;

import com.qa.manager.PageObjectManager;
import com.qa.utils.ConfigReader;
import com.qa.driver.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected PageObjectManager pageObjectManager;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        String browser = System.getProperty(
                "browsername",
                ConfigReader.get("browser")
        );

        WebDriverManager.initDriver(browser);
        driver = WebDriverManager.getDriver();

        if (driver == null) {
            throw new IllegalStateException("WebDriver initialization failed");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.get("site_url"));

        pageObjectManager = new PageObjectManager(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverManager.quitDriver();

    }

}