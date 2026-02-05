package com.qa.pages;

import com.qa.utils.AllureUtils;
import com.qa.utils.GeneralUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginButton = By.id("login-button");
    private final By alertMsg = By.xpath("//h3[@data-test='error']");
    private final By userName = By.id("user-name");
    private final By password = By.id("password");

    @Step("Checking login button visibility")
    public boolean isLoginButtonVisible() {

        AllureUtils.highlightAndCapture(driver, loginButton, "Login button");
        return GeneralUtils.isElementDisplayed(driver, loginButton);

    }

    @Step("Click on login button")
    public void clickLoginButton() {

        GeneralUtils.clickElement(driver, loginButton);

    }

    @Step("Get invalid login error message")
    public String getInvalidLoginErrorMsg() {

        return GeneralUtils.getTextValue(driver, alertMsg);

    }

    @Step("Enter user credentials")
    public void enterUserCredentials(String usrName, String usrPwd) {

        GeneralUtils.setTextValue(driver, userName, usrName);
        GeneralUtils.setTextValue(driver, password, usrPwd);
        AllureUtils.capture(driver, "Entered login credentials");

    }

    @Step("Proceed to login")
    public HomePage login(String usrName, String usrPwd) {

        enterUserCredentials(usrName, usrPwd);
        clickLoginButton();
        return new HomePage(driver);

    }

}
