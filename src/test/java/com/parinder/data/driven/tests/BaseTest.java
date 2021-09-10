package com.parinder.data.driven.tests;

import com.parinder.data.driven.configuration.Config;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected Config config;
    protected WebDriver driver;
    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();


    public void setup() throws IOException {
        this.config = new Config();
        this.setupDriver();
    }

    private void setupDriver() {
        RemoteWebDriver newWebDriver;
        String hostUrl = config.getProperty("webdriver.host.url");
        String browser = config.getProperty("webdriver.browser");

        switch (browser) {
            case "firefox":
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(firefoxProfile);
                try {
                    newWebDriver = new RemoteWebDriver(new URL(hostUrl), firefoxOptions);
                    break;
                } catch (MalformedURLException e1) {
                    throw new IllegalArgumentException("Invalid webdriver host URL: " + hostUrl);
                }
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setProxy(null);
                try {
                    newWebDriver = new RemoteWebDriver(new URL(hostUrl), chromeOptions);
                    break;
                } catch (MalformedURLException e) {
                    throw new IllegalArgumentException("Invalid webdriver host URL: " + hostUrl);
                }
            default:
                throw new IllegalArgumentException("Invalid webdriver browser: " + browser);
        }

        int windowHeight = Integer.parseInt(config.getProperty("webdriver.window.height"));
        int windowWidth = Integer.parseInt(config.getProperty("webdriver.window.width"));
        newWebDriver.manage().window().setSize(new Dimension(windowWidth, windowHeight)); // better than default

        threadLocalDriver.set(newWebDriver); // add webdriver to threadlocal hash
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        this.getDriver().quit();
    }

    protected WebDriver getDriver() {
        return threadLocalDriver.get(); // get webdriver from threadlocal hash
    }
}
