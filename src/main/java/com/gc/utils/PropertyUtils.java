package com.gc.utils;

import com.gc.common.entity.EnumEntity;
import com.gc.exception.GCException;
import com.gc.exception.SystemErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author join wick
 * @version 1.0.0
 * @description property utils
 * @createDate 2020/12/15 15:06
 * @since 1.0.0
 */
public class PropertyUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtils.class);
    private static Properties properties = ConfigLoaderUtils.loadProperties(
            EnumEntity.ConfigPathName.GC_COMMON_CONFIG.getValue(),
            ConstantUtils.DEFAULT_CONFIG_FILE);

    private PropertyUtils() {
    }

    /**
     * get value by key
     *
     * @param key String
     * @return String
     */
    public static String getProperty(String key) {
        if (CommonUtils.isEmpty(key)) {
            LOGGER.error("key should not be empty");
            throw new GCException(SystemErrorCode.KEY_NOT_EXISTS);
        }
        return properties.getProperty(key);
    }

    /**
     * update value by key
     *
     * @param key   String
     * @param value String
     * @return boolean
     */
    public static boolean updateProperty(String key, String value) {
        if (CommonUtils.isEmpty(key)) {
            LOGGER.error("key should not be empty");
            return false;
        }
        if (CommonUtils.isEmpty(value)) {
            LOGGER.error("value should not be empty");
            return false;
        }
        if (CommonUtils.isEmpty(getProperty(key))) {
            LOGGER.error("key does not exist");
            return false;
        }
        properties.setProperty(key, value);
        return true;
    }

}
