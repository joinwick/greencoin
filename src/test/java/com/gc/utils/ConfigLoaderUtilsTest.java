package com.gc.utils;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @className ConfigLoaderUtilsTest.java
 * @description
 * @createDate 2020/12/15 21:53
 * @since 1.0.0
 */
public class ConfigLoaderUtilsTest {

    @Test
    public void loadProperties_ValidEntry() {
        Properties properties = ConfigLoaderUtils.loadProperties(ConstantUtils.DEFAULT_CONFIG_FILE);
        assertNotNull(properties);
        assertTrue(properties.keySet().size() > 0);
    }

}