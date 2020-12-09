package com.gc.utils;

/**
 * @author join wick
 * @version 1.0.0
 * @className StringUtils.java
 * @description string utils
 * @createDate 2020/12/8 18:44
 * @since 1.0.0
 */
public class StringUtils {
    /**
     * check string empty
     *
     * @param str String
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * convert byte array to hex string
     *
     * @param bytes byte[]
     * @return String
     */
    public static String convertByteToHexString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            int tempInt = aByte & 0xff;
            String tempString = Integer.toHexString(tempInt);
            if (tempString.length() < 2) {
                hexStringBuilder.append("0").append(tempString);
            } else {
                hexStringBuilder.append(tempString);
            }
        }
        return hexStringBuilder.toString();
    }

    /**
     * find matched str by simple loop
     *
     * @param strings     String[]
     * @param searchedStr String
     * @return boolean
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
