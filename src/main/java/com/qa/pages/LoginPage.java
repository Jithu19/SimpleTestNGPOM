package com.qa.pages;

import com.qa.utils.GeneralUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By loginButton = By.id("login-button");
    By alertMsg = By.xpath("//h3[@data-test='error']");
    By userName = By.id("user-name");
    By password = By.id("password");

    public boolean isLoginButtonVisible() {

        return GeneralUtils.isElementDisplayed(driver, loginButton);

    }

    public void clickLoginButton() {

        GeneralUtils.clickElement(driver, loginButton);

    }

    public String getInvalidLoginErrorMsg() {

        return GeneralUtils.getTextValue(driver, alertMsg);

    }

    public void enterUserCredentials(String usrName, String usrPwd) {

        GeneralUtils.setTextValue(driver, userName, usrName);
        GeneralUtils.setTextValue(driver, password, usrPwd);

    }

    public HomePage login(String usrName, String usrPwd) {

        enterUserCredentials(usrName, usrPwd);
        clickLoginButton();
        return new HomePage(driver);

    }

}
