package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.HomePage;
import com.qa.utils.AllureUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Login Page Validation")
public class LoginTest extends BaseTest {

    @Test(priority = 0, description = "Verify login page - login button")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the login button is visible on Login Page")
    public void verifyLoginButton() {

        boolean isVisible = pageObjectManager
                .getLoginPage()
                .isLoginButtonVisible();

        Assert.assertTrue(isVisible, "Login button should be visible");

        AllureUtils.log("Verified login button visibility");
        AllureUtils.capture(driver, "Login Button");
    }

    @Test(priority = 0, description = "Verify login page - login button (FAIL TEST)")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Intentional failure to validate Allure reporting")
    public void verifyLoginButtonFail() {

        boolean isVisible = pageObjectManager
                .getLoginPage()
                .isLoginButtonVisible();

        Assert.assertFalse(isVisible, "Login button should NOT be visible");

        AllureUtils.log("Intentional failure test");
    }

    @Test(priority = 1, description = "Verify login page - invalid login message")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify error message when user clicks login without credentials")
    public void verifyInvalidLogin() {

        pageObjectManager
                .getLoginPage()
                .clickLoginButton();

        String errorMsg = pageObjectManager
                .getLoginPage()
                .getInvalidLoginErrorMsg();

        Assert.assertEquals(
                errorMsg,
                "Epic sadface: Username is required"
        );

        AllureUtils.log("Verified invalid login error message");
        AllureUtils.capture(driver, "Invalid Login Error");
    }

    @Test(priority = 2, description = "Verify login page - successful login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the user is able to login successfully")
    public void verifyLogin() {

        HomePage homePage =
                pageObjectManager
                        .getLoginPage()
                        .login("standard_user", "secret_sauce");

        Assert.assertEquals(
                homePage.verifyHomePageLogo(),
                "Swag Labs"
        );

        AllureUtils.log("User logged in successfully");
        AllureUtils.capture(driver, "Home Page After Login");
    }
}