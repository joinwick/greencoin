package com.gc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @description string utils
 * @createDate 2020/12/8 18:44
 * @since 1.0.0
 */
public class StringUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    private StringUtils() {
    }

    /**
     * string append
     *
     * @param str         String
     * @param appendedStr String
     * @param appendFlag  boolean
     * @return String
     */
    public static String appendByCondition(String str, String appendedStr, boolean appendFlag) {
        if (appendFlag) {
            return appendedStr + str;
        }
        return str + appendedStr;
    }

    /**
     * convert string to byte[]
     *
     * @param str String
     * @return byte[]
     */
    public static byte[] convertStringToByteArray(String str) {
        if (CommonUtils.isEmpty(str)) {
            LOGGER.error("empty string in method <StringUtils: convertStringToByteArray>");
            return new byte[0];
        }
        return str.getBytes(ConstantUtils.DEFAULT_CHARSET);
    }

    /**
     * convert byte[] to string
     *
     * @param bytes byte[]
     * @return String
     */
    public static String convertByteArrayToString(byte[] bytes) {
        if (CommonUtils.isEmpty(bytes)) {
            LOGGER.error("empty byte array in method <StringUtils: convertByteArrayToString>");
            return null;
        }
        return new String(bytes, ConstantUtils.DEFAULT_CHARSET);
    }

    /**
     * find matched str by simple loop
     * return matched index, return -1 if no match
     *
     * @param strings     String[]
     * @param searchedStr String
     * @return int
     */
    public static int findStrByLoop(String[] strings, String searchedStr) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(searchedStr)) {
                return i;
            }
        }
        return -1;
    }
}
