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
    public void convertSourceFormatToSpecialFormat_HexToTen(){
        String expectedRes = "419668748";
        assertEquals(expectedRes, ConvertUtils.convertSourceFormatToSpecialFormat(hexString, 16, 10));
    }

    @Test
    public void convertSourceFormatToSpecialFormat_TenToHex() {
        String expectedRes = "0000000000000003a30c00000000000000000000000000000000000000000000";
        String actualRes = ConvertUtils.convertSourceFormatToSpecialFormat(targetString, 10, 16);
        LOGGER.debug("initial actualRes(hex) is <{}>", actualRes);
        actualRes = StringUtils.paddingIterator(actualRes,
                ConstantUtils.DEFAULT_ZERO_STRING,
                ConstantUtils.DEFAULT_HASH_HEX_LENGTH - actualRes.length(),
                ConstantUtils.DEFAULT_APPEND_FLAG);
        assertEquals(expectedRes, actualRes);
    }
}