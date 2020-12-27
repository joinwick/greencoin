package com.gc.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/24 13:59
 * @since 1.0.0
 */
public class StringUtilsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtilsTest.class);
    String initialStr = "gc<StringUtilsTest>";
    String paddingStr = "UT";

    @Test
    public void appendByCondition_True() {
        String expectedRes = "UTgc<StringUtilsTest>";
        assertEquals(expectedRes, StringUtils.appendByCondition(initialStr, paddingStr, true));
    }

    @Test
    public void appendByCondition_False() {
        String expectedRes = "gc<StringUtilsTest>UT";
        assertEquals(expectedRes, StringUtils.appendByCondition(initialStr, paddingStr, false));
    }

    @Test
    public void convertStringToByteArray_ValidEntry() {
        String str = "12";
        byte[] expectedRes = new byte[]{49, 50};
        byte[] actualRes = StringUtils.convertStringToByteArray(str);
        assertArrayEquals(expectedRes, actualRes);
    }

    @Test
    public void convertByteArrayToString_ValidEntry() {
        byte[] initialBytes = new byte[]{49, 50};
        String expectedRes = "12";
        String actualRes = StringUtils.convertByteArrayToString(initialBytes);
        assertEquals(expectedRes, actualRes);
    }

    @Test
    public void paddingIterator_EqualLeftPadding() {
        int paddingLength = 2;
        String expectedRes = "UTgc<StringUtilsTest>";
        assertEquals(expectedRes, StringUtils.paddingIterator(initialStr, paddingStr, paddingLength, true));
    }

    @Test
    public void paddingIterator_LargeLeftPadding() {
        int paddingLength = 3;
        String expectedRes = "UUTgc<StringUtilsTest>";
        assertEquals(expectedRes, StringUtils.paddingIterator(initialStr, paddingStr, paddingLength, true));
    }

    @Test
    public void paddingIterator_LessLeftPadding() {
        int paddingLength = 1;
        String expectedRes = "Ugc<StringUtilsTest>";
        assertEquals(expectedRes, StringUtils.paddingIterator(initialStr, paddingStr, paddingLength, true));
    }


}