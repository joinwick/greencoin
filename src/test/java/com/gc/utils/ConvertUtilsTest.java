package com.gc.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/23 16:55
 * @since 1.0.0
 */
public class ConvertUtilsTest {

    String hexString = "1903a30c";

    @Test
    public void convertHexToTen_ValidEntry() {
        String expectedRes = "419668748";
        assertEquals(expectedRes, ConvertUtils.convertHexToTen(hexString));
    }

    @Test
    public void convertTenToHex_ValidEntry() {

    }
}