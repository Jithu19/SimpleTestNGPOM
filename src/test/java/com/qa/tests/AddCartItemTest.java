package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.CartPage;
import com.qa.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Add Item to Cart")
public class AddCartItemTest extends BaseTest {

    @Test(priority = 0, description = "Add Item to Cart flow")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the user is able to add items to cart")
    public void addCartItem() {

        HomePage homePage =
                pageObjectManager
                        .getLoginPage()
                        .login("standard_user", "secret_sauce");

        Assert.assertEquals(
                homePage.verifyHomePageLogo(),
                "Swag Labs"
        );

        homePage.sortProductItem("lohi");
        Assert.assertEquals(
                homePage.getProductSortedValue(),
                "Price (low to high)"
        );

        homePage.clickAddToCartByIndex(1);
        homePage.clickAddToCartByIndex(3);
        Assert.assertEquals(homePage.getCartItemCount(), "2");

        CartPage cartPage =
                pageObjectManager.getCartPage();

        cartPage = homePage.clickShoppingCartLink();
        Assert.assertEquals(
                cartPage.verifyCartPageTitle(),
                "Your Cart"
        );

        cartPage.getCartItemDetails();

        cartPage.removeCartItemByIndex("2");
        Assert.assertEquals(cartPage.getCartItemCount(), 1);

        cartPage.getCartItemDetails();

        cartPage.continueShopping();
        Assert.assertEquals(
                pageObjectManager
                        .getHomePage()
                        .verifyHomePageLogo(),
                "Swag Labs"
        );

        pageObjectManager
                .getHomePage()
                .sortProductItem("hilo");

        Assert.assertEquals(
                pageObjectManager
                        .getHomePage()
                        .getProductSortedValue(),
                "Price (high to low)"
        );

        pageObjectManager
                .getHomePage()
                .clickAddToCartByIndex(1);

        Assert.assertEquals(
                pageObjectManager
                        .getHomePage()
                        .getCartItemCount(),
                "2"
        );

        cartPage =
                pageObjectManager
                        .getHomePage()
                        .clickShoppingCartLink();

        Assert.assertEquals(
                cartPage.verifyCartPageTitle(),
                "Your Cart"
        );

        cartPage.getCartItemDetails();
        Assert.assertEquals(cartPage.getCartItemCount(), 2);
    }
}
