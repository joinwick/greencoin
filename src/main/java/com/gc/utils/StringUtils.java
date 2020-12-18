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
            LOGGER.error("empty string");
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
            LOGGER.error("empty byte array");
            return null;
        }
        return new String(bytes, ConstantUtils.DEFAULT_CHARSET);
    }

    /**
     * convert byte(-128~127) array to hex string
     *
     * @param bytes byte[]
     * @return String
     */
    public static String convertByteToHexString(byte[] bytes) {
        if (CommonUtils.isEmpty(bytes)) {
            LOGGER.error("empty byte array");
            return null;
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

    /**
     * convert string to byte array
     *
     * @param hexString String
     * @return byte[]
     */
    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.toUpperCase();
        char[] hexChars = hexString.toCharArray();
        int hexLength = hexChars.length;
        if ((hexLength & 0x01) != 0){
            return new byte[0];
        }

        byte[] bytes = new byte[hexLength >> 1];

        for (int i = 0; i < hexLength; i++) {
            int index = i * 2;
            // & 0xff 中 & 表示按位与,只有两个位同时为1,才能得到1, 相当于取低八位(0xff = 1111 1111)
            bytes[i] = (byte) (hexCharToByte(hexChars[index]) << 4 | (hexCharToByte(hexChars[index + 1])) & 0xff);
        }
        return bytes;
    }

    /**
     * hex char[0123456789ABCDEF] to byteX
     *
     * @param c char
     * @return byte
     */
    private static byte hexCharToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
