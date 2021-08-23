package com.parinder.data.driven.tests;

import com.parinder.data.driven.configuration.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import java.io.IOException;

public class BaseTest {

    protected Config config;
    protected WebDriver driver;
    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();


    public void setup() throws IOException {
        this.config = new Config();
        this.setupWebDriver();
    }

    private void setupWebDriver() {
        System.setProperty("webdriver.chrome.driver", this.config.getProperty("webdriver.chrome.path"));
        this.driver = new ChromeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }

    protected WebDriver getDriver() {
        return threadLocalDriver.get(); // get webdriver from threadlocal hash
    }
}
