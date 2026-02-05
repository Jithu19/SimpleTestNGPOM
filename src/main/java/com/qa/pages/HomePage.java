package com.qa.pages;

import com.qa.utils.AllureUtils;
import com.qa.utils.GeneralUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    private final By swagLabLogo = By.xpath("//div[@class='app_logo']");
    private final By productTitle = By.xpath("//span[@data-test='title']");
    private final By productSort = By.xpath("//select[@data-test='product-sort-container']");
    private final By shoppingCartLink = By.xpath("//a[@data-test='shopping-cart-link']");
    private final By shoppingCartBadge = By.xpath("//a/span[@data-test='shopping-cart-badge']");

    private final String inventoryItem = "//div[@data-test='inventory-item']//button[text()='Add to cart']";

    @Step("Get Home Page logo")
    public String verifyHomePageLogo() {

        return GeneralUtils.getTextValue(driver, swagLabLogo);

    }

    @Step("Get Home Page title")
    public String verifyHomePageProductTitle() {

        return  GeneralUtils.getTextValue(driver, productTitle);

    }

    @Step("Click on Shopping Cart Link")
    public CartPage clickShoppingCartLink() {

        GeneralUtils.clickElement(driver, shoppingCartLink);
        return new CartPage(driver);

    }

    @Step("Sort product item(s) by: {sortingValue}")
    public void sortProductItem(String sortingValue) {

        GeneralUtils.selectDropDownByValue(driver, productSort, sortingValue);
        AllureUtils.capture(driver, "Sorted product image");

    }

    @Step("Add item to cart by index: {index}")
    public void clickAddToCartByIndex(int index) {

        List<WebElement> addToCartButtons = driver.findElements(By.xpath(inventoryItem));

        if (index < 0 || index >= addToCartButtons.size()) {
            throw new IllegalArgumentException(
                    "Invalid index: " + index + ". Available items: " + addToCartButtons.size());
        }

        WebElement addToCartButtonForItem = addToCartButtons.get(index);
        GeneralUtils.clickElement(driver, addToCartButtonForItem);
        AllureUtils.capture(driver, "Added item by index: " + index);

    }

    @Step("Get product sorted value")
    public String getProductSortedValue() {

        return GeneralUtils.getDropDownValue(driver, productSort);

    }

    @Step("Get total cart item count")
    public String getCartItemCount() {

        AllureUtils.highlightAndCapture(driver, shoppingCartBadge, "Shopping cart badge");
        return GeneralUtils.getTextValue(driver, shoppingCartBadge);

    }


}
