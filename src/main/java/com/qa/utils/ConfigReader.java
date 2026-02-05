package com.qa.utils;

import java.io.FileInputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties prop = new Properties();
    public static final String CONFIG_PATH =
            System.getProperty("user.dir")
                    + "/src/test/resources/config/config.properties";

    private ConfigReader() {}

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_PATH);
            prop.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file");
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);

    }

}
