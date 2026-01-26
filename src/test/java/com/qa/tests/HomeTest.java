package com.qa.tests;

import com.qa.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Home Page Validation")
public class HomeTest extends BaseTest {


    @Test(description = "Verify home page title")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the title of Home Page after successful login")
    public void verifyProductTitle() {
        loginPage.enterUserCredentials("standard_user", "secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertEquals(homePage.verifyHomePageProductTitle(), "Products");

    }

}
