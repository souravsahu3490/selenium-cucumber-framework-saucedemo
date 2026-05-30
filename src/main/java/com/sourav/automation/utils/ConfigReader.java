package com.sourav.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Objects;

public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    static {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
            LoggerUtils.info("Configuration file loaded successfully");
        } catch (IOException e) {
            LoggerUtils.error("Failed to load config.properties: " + e.getMessage());
            throw new RuntimeException("Configuration file not found at: " + CONFIG_FILE_PATH, e);
        }
    }

    /**
     * Get property value by key
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (Objects.isNull(value)) {
            LoggerUtils.warn("Property not found for key: " + key);
            return "";
        }
        LoggerUtils.info("Retrieved property: " + key + " = " + value);
        return value;
    }

    /**
     * Get property with default value
     */
    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        LoggerUtils.info("Retrieved property: " + key + " = " + value);
        return value;
    }

    /**
     * Get integer property
     */
    public static int getIntProperty(String key) {
        String value = getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LoggerUtils.error("Invalid integer value for key: " + key);
            throw new NumberFormatException("Key " + key + " does not contain an integer value: " + value);
        }
    }

    /**
     * Get boolean property
     */
    public static boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        return Boolean.parseBoolean(value);
    }
}