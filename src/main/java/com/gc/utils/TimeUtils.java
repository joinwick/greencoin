package com.gc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author join wick
 * @version 1.0.0
 * @description time utils
 * @createDate 2020/12/20 13:48
 * @since 1.0.0
 */
public class TimeUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtils.class);

    private TimeUtils() {
    }

    /**
     * convert unix time stamp(needs to be accurate to milliseconds) to GMT date time
     *
     * @param timeStamp long(milliseconds)
     * @return String
     */
    public static String convertTimeStampToDateTime(long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstantUtils.DEFAULT_DATE_TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(ConstantUtils.DEFAULT_TIME_ZONE));
        return simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
    }

    public static long getTimeStamp(){
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
