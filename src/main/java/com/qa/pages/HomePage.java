package com.qa.pages;

import com.qa.utils.GeneralUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    By swagLabLogo = By.xpath("//div[@class='app_logo']");
    By productTitle = By.xpath("//span[@data-test='title']");

    public String verifyHomePageLogo() {

        return GeneralUtils.getTextValue(driver, swagLabLogo);

    }

    public String verifyHomePageProductTitle() {

        return  GeneralUtils.getTextValue(driver, productTitle);

    }

}
