package com.gc.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/23 16:55
 * @since 1.0.0
 */
public class ConvertUtilsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertUtilsTest.class);

    String hexString = "1903a30c";
    String targetString = "22829202948393929850749706076701368331072452018388575715328";

    @Test
    public void convertHexToTen_ValidEntry() {
        String expectedRes = "419668748";
        assertEquals(expectedRes, ConvertUtils.convertHexToTen(hexString));
    }

    @Test
    public void convertTenToHex_ValidEntry() {
        String expectedRes = "0000000000000003a30c00000000000000000000000000000000000000000000";
        String actualRes = ConvertUtils.convertTenToHex(targetString);
        assertEquals(expectedRes, actualRes);


    }
}