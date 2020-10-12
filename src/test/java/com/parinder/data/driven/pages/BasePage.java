package com.parinder.data.driven.pages;

import com.parinder.data.driven.configuration.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver = null;
    protected WebDriverWait wait = null;
    protected Config config;
    protected String url;

    public BasePage(WebDriver driver, Config config) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 25);
        this.config = config;
        PageFactory.initElements(this.driver, this);
    }

    public void get() {
        this.driver.get(this.url);
    }
    // implicit wait (shameful I know)
    public void implicitlyWait(Integer secondsToWait) {
        long millisToWait = secondsToWait * 1000;
        try {
            Thread.sleep(millisToWait);
        } catch (Exception e) {
            // do nothing as this will literally never happen
        }
    }
}
