package com.gc.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/20 13:56
 * @since 1.0.0
 */
public class TimeUtilsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtilsTest.class);

    @Test
    public void convertTimeStampToDateTime() {
        long timeStamp = 1608472377000L;
        String expectDateTime = "2020-12-20 13:52:57";
        assertEquals(expectDateTime, TimeUtils.convertTimeStampToUnixDateTime(timeStamp));
    }

    @Test
    public void convertTimeStampToDateTime_InitialDateTime() {
        long timeStamp = 0L;
        String expectDateTime = "1970-01-01 00:00:00";
        assertEquals(expectDateTime, TimeUtils.convertTimeStampToUnixDateTime(timeStamp));
    }

    @Test
    public void getUnixTimeStamp() {
        long timeStamp = TimeUtils.getUnixTimeStamp();
        LOGGER.debug("current time stamp <{}>", System.currentTimeMillis());
        assertTrue(timeStamp > 0);
        LOGGER.debug("current date time <{}>", TimeUtils.convertTimeStampToUnixDateTime(timeStamp));
    }

    @Test
    public void convertTimeStampToLocalDateTime() {
        long timeStamp = 1608472377000L;
        String expectDateTime = "2020-12-20 21:52:57";
        assertEquals(expectDateTime, TimeUtils.convertTimeStampToLocalDateTime(timeStamp));
    }

    @Test
    public void convertTimeStampToLocalDateTime_InitialDateTime() {
        long timeStamp = 0L;
        String expectDateTime = "1970-01-01 08:00:00";
        assertEquals(expectDateTime, TimeUtils.convertTimeStampToLocalDateTime(timeStamp));
    }
}