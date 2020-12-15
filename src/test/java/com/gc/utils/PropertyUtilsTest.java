package com.gc.utils;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @className PropertyUtilsTest.java
 * @description
 * @createDate 2020/12/15 21:53
 * @since 1.0.0
 */
public class PropertyUtilsTest {
    String key = "gc.db.file";

    @Test
    public void getProperty_ValidEntry() {
        String expectedValue = "db/gc.db";
        assertEquals(expectedValue, PropertyUtils.getProperty(key));
    }

    @Test
    public void updateProperty() {
    }
}