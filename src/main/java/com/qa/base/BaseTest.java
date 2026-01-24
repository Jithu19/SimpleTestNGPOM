package com.qa.base;

import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.utils.GeneralUtils;
import com.qa.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;

    public static final String CONFIG_PATH =
            System.getProperty("user.dir")
                    + "/src/test/resources/config/config.properties";

    @BeforeMethod
    public void setUp() {

        String browser = System.getProperty("browsername", GeneralUtils.getProperty(CONFIG_PATH, "browser"));

        driver = WebDriverManager.getInstance(browser).getDriver();

        driver.get(GeneralUtils.getProperty(CONFIG_PATH, "site_url"));

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.quitDriver();

    }

}