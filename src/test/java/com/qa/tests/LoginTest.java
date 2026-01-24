package com.qa.tests;

import com.qa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 0)
    public void verifyLoginButton() {
        Assert.assertEquals(loginPage.isLoginButtonVisible(), true);

    }

    @Test(priority = 1)
    public void verifyInvalidLogin() {
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getInvalidLoginErrorMsg(), "Epic sadface: Username is required");

    }
}
