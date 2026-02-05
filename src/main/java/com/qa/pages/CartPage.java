package com.qa.pages;

import com.qa.utils.AllureUtils;
import com.qa.utils.GeneralUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By cartTitle = By.xpath("//span[@data-test='title']");
    private final By cartItemTotal = By.xpath("//div[@data-test='inventory-item']");
    private final By checkoutButton = By.xpath("//button[@id='checkout']");
    private final By continueShoppingButton = By.xpath("//button[@id='continue-shopping']");

    private final String cartItemName = "//div[@data-test='inventory-item']//div[@data-test='inventory-item-name']";
    private final String cartItemPrice = "//div[@data-test='inventory-item']//div[@data-test='inventory-item-price']";
    private final String removeCartItem = "//div[@data-test='inventory-item']//button[text()='Remove']";

    @Step("Get cart page title")
    public String verifyCartPageTitle() {

        return  GeneralUtils.getTextValue(driver, cartTitle);

    }

    @Step("Get cart item(s) count")
    public int getCartItemCount() {

        return driver.findElements(cartItemTotal).size();

    }

    @Step("Get cart item(s) details")
    public void getCartItemDetails() {

        int size = getCartItemCount();
        String productItem = "";
        String productPrice = "";
        Map<Integer, List<String>> productDetails = new HashMap<>();
        for (int i=1; i<=size; i++) {
            productItem = driver.findElement(By.xpath("(" + cartItemName + ")" + "[" + i + "]")).getText();
            productPrice = driver.findElement(By.xpath("(" + cartItemPrice + ")" + "[" + i + "]")).getText();
            productDetails.computeIfAbsent(i, k -> new ArrayList<>()).add(productItem);
            productDetails.computeIfAbsent(i, k -> new ArrayList<>()).add(productPrice);

        }

        AllureUtils.log("Product details: " + productDetails);
        AllureUtils.capture(driver, "Product Item(s)");

    }

    @Step("Remove cart item with index: {index}")
    public void removeCartItemByIndex(String index) {

        WebElement itemToRemove = driver.findElement(By.xpath("(" + removeCartItem + ")" + "[" + index + "]"));
        GeneralUtils.clickElement(driver, itemToRemove);
        AllureUtils.capture(driver, "Removed item by index: " + index);

    }

    @Step("Proceed to checkout")
    public void proceedToCheckout() {

        GeneralUtils.clickElement(driver, checkoutButton);

    }

    @Step("Continue to shopping")
    public void continueShopping() {

        GeneralUtils.clickElement(driver, continueShoppingButton);

    }

}