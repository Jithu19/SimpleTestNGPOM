package com.qa.tests;

import com.qa.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Login Page Validation")
public class LoginTest extends BaseTest {

    @Test(priority = 0, description = "Verify login page - login button")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the login button of Login Page")
    public void verifyLoginButton() {
        Assert.assertEquals(loginPage.isLoginButtonVisible(), true);

    }

    @Test(priority = 0, description = "Verify login page - login button (FAIL TEST)")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the login button of Login Page - FAILED TEST CASE")
    public void verifyLoginButtonf() {
        Assert.assertEquals(loginPage.isLoginButtonVisible(), false);

    }

    @Test(priority = 1, description = "Verify login page - invalid login message")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the error message in Login Page")
    public void verifyInvalidLogin() {
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getInvalidLoginErrorMsg(), "Epic sadface: Username is required");

    }

    @Test(priority = 2, description = "Verify login page - successful login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the user is able to login successfully")
    public void verifyLogin() {
        homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(homePage.verifyHomePageLogo(), "Swag Labs");

    }
}
