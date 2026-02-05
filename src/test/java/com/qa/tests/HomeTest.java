package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.utils.AllureUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Home Page Validation")
public class HomeTest extends BaseTest {


    @Test(priority = 0, description = "Verify home page title")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the title of Home Page after successful login")
    public void verifyProductTitle() {
        homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(homePage.verifyHomePageProductTitle(), "Products");
        AllureUtils.log("Verified home page title");
        AllureUtils.capture(driver, "Home Page Title");

    }

    @Test(priority = 1, description = "Go to cart page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User navigation from home to cart page")
    public void goToCartPage() {
        homePage = loginPage.login("standard_user", "secret_sauce");
        cartPage = homePage.clickShoppingCartLink();
        Assert.assertEquals(cartPage.verifyCartPageTitle(), "Your Cart");
        AllureUtils.log("Verified cart page title");
        AllureUtils.capture(driver, "Cart Page Title");

    }

}
