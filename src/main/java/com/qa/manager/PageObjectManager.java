package com.qa.manager;

import com.qa.pages.CartPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private WebDriver driver;

    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }

    public CartPage getCartPage() {
        return (cartPage == null) ? cartPage = new CartPage(driver) : cartPage;
    }

}
