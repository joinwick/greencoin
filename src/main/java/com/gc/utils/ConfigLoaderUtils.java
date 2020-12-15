package com.gc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * @author join wick
 * @version 1.0.0
 * @description config loader utils
 * @createDate 2020/12/15 14:26
 * @since 1.0.0
 */
public class ConfigLoaderUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigLoaderUtils.class);

    private ConfigLoaderUtils() {

    }

    /**
     * load properties
     *
     * @param configName            String
     * @param defaultConfigFilePath String
     * @return Properties
     */
    public static Properties loadProperties(String configName, String defaultConfigFilePath) {
        Properties properties = new Properties();
        String filePath = System.getProperty(configName);
        if (CommonUtils.isEmpty(filePath)) {
            return loadProperties(defaultConfigFilePath);
        }
        try (InputStream inputStream = new FileInputStream(filePath)) {
            LOGGER.debug("load config info from config path: <{}>, configName:<{}>", filePath, configName);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("load config info<{}> with error<{}>", filePath, e.getMessage());
        }
        return properties;
    }

    /**
     * load properties from default config file path
     *
     * @param configFilePath String
     * @return Properties
     */
    public static Properties loadProperties(String configFilePath) {
        Properties properties = new Properties();
        try (InputStream inputStream = ConfigLoaderUtils.class.getResourceAsStream(configFilePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("load config info<{}> with error<{}>", configFilePath, e.getMessage());
        }
        return properties;
    }
}
