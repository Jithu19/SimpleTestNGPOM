package com.qa.tests;

import com.qa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test(priority = 0)
    public void verifyHomePage() {
        loginPage.enterUserCredentials("standard_user", "secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertEquals(homePage.verifyHomePageLogo(), "Swag Labs");

    }

    @Test(priority = 1)
    public void verifyProductTitle() {
        loginPage.enterUserCredentials("standard_user", "secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertEquals(homePage.verifyHomePageProductTitle(), "Products");

    }

}
