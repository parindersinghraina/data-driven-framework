package com.parinder.data.driven.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Config {
    protected Properties configProperties;
    protected String webDriverConfig;
    protected String environmentConfig;
    protected String testDataSheet;


    public Config() throws IOException {
        this.configProperties = new Properties();

        // Load target environment config file based on environment variable
        String targetEnvironmentConfigFIlePath = "configuration/environment-config.properties";
        this.loadPropertiesFile(targetEnvironmentConfigFIlePath);

        // Load target environment config file based on environment variable
        String targetTestDataFilePath = "test-data-sheet.xlsx";
        this.loadPropertiesFile(targetTestDataFilePath);
    }

    private void loadPropertiesFile(String filePath) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(filePath).getFile());
        FileInputStream fileInputStream = new FileInputStream(file);
        this.configProperties.load(fileInputStream);
        fileInputStream.close();
    }

    public String getProperty(String name) {
        return configProperties.getProperty(name);
    }

    public String getSecret(String name) {
        return System.getenv(name);
    }
}
