package com.gc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

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
     * use DateTimeFormatter(thread safety) to replace SimpleDateFormat(not thread safety)
     *
     * @param timeStamp long(milliseconds)
     * @return String
     */
    public static String convertTimeStampToUnixDateTime(long timeStamp) {
        if (timeStamp < 0) {
            LOGGER.error("unsupported negative time stamp in method<TimeUtils: convertTimeStampToUnixDateTime>");
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(ConstantUtils.DEFAULT_DATE_TIME_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timeStamp / 1000L, 0, ZoneOffset.UTC);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * convert unix date time to time stamp
     *
     * @param dateTime String
     * @return long
     */
    public static long convertUnixDateTimeToTimeStamp(String dateTime) {
        if (CommonUtils.isEmpty(dateTime)) {
            LOGGER.error("empty data in method<TimeUtils: convertUnixDateTimeToTimeStamp>");
            return 0;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(ConstantUtils.DEFAULT_DATE_TIME_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
        return LocalDateTime.from(localDateTime).atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

    /**
     * convert unix time stamp(needs to be accurate to milliseconds) to local date time
     *
     * @param timeStamp long(milliseconds)
     * @return String
     */
    public static String convertTimeStampToLocalDateTime(long timeStamp) {
        if (timeStamp < 0) {
            LOGGER.error("unsupported negative time stamp in method<TimeUtils: convertTimeStampToLocalDateTime>");
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(ConstantUtils.DEFAULT_DATE_TIME_FORMAT);
        Instant instant = Instant.ofEpochMilli(timeStamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * get unix time stamp with milliseconds, such as 1608472377000L
     *
     * @return long
     */
    public static long getUnixTimeStamp() {
        return LocalDateTime.now(ZoneOffset.UTC).atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
}
