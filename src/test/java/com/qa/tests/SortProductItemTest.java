package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.HomePage;
import com.qa.utils.AllureUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Product Filter Validation")
public class SortProductItemTest extends BaseTest {

    @Test(priority = 0, description = "Verify home page - product sorting")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the user is able to sort products using all available filters")
    public void sortProduct() {

        HomePage homePage =
                pageObjectManager
                        .getLoginPage()
                        .login("standard_user", "secret_sauce");

        Assert.assertEquals(
                homePage.verifyHomePageLogo(),
                "Swag Labs"
        );

        homePage.sortProductItem("lohi");
        Assert.assertEquals(homePage.getProductSortedValue(), "Price (low to high)");

        homePage.sortProductItem("hilo");
        Assert.assertEquals(homePage.getProductSortedValue(), "Price (high to low)");

        homePage.sortProductItem("za");
        Assert.assertEquals(homePage.getProductSortedValue(), "Name (Z to A)");

        homePage.sortProductItem("az");
        Assert.assertEquals(homePage.getProductSortedValue(), "Name (A to Z)");

        AllureUtils.log("Verified all product sorting options");
        AllureUtils.capture(driver, "Product Sorting");
    }
}
