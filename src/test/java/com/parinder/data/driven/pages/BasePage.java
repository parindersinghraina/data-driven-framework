package com.parinder.data.driven.pages;

import com.parinder.data.driven.configuration.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Config config;
    protected String url;

    public BasePage(WebDriver driver, Config config) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 25);
        this.config= config;
    }
}
