package com.parinder.data.driven.tests;

import com.parinder.data.driven.configuration.Config;
import com.parinder.data.driven.utils.excelutils.ExcelUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class BaseTest {

    protected Config config;
    protected WebDriver driver;
    protected ExcelUtil excelUtil;

    public void setup() throws IOException {
        this.config = new Config();
        this.setupWebDriver();
    }

    private void setupWebDriver() {
        System.setProperty("webdriver.chrome.driver", this.config.getProperty("webdriver.chrome.path"));
        this.driver = new ChromeDriver();
    }
}
