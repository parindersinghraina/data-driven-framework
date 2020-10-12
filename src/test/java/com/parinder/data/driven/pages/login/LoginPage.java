package com.parinder.data.driven.pages.login;

import com.parinder.data.driven.configuration.Config;
import com.parinder.data.driven.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, Config config) {
        super(driver, config);
        this.url = this.config.getProperty("ui.linkedin.baseurl") + "login";
    }

    //username
    @FindBy(xpath = "//input[@id='username']")
    protected WebElement userNameInput;

    public void enterUserName(String userName){
        this.wait.until(ExpectedConditions.visibilityOf(userNameInput));
        this.userNameInput.clear();
        this.userNameInput.sendKeys(userName);
    }
    //password
    @FindBy(xpath = "//input[@id='password']")
    protected WebElement passwordInput;

    public void enterPassword(String password){
        this.wait.until(ExpectedConditions.visibilityOf(passwordInput));
        this.passwordInput.clear();
        this.passwordInput.sendKeys(password);
    }
}
