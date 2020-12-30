package com.gc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

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
     * padding str with fixed str
     *
     * @param str           String,initial context
     * @param paddingStr    String, fixed padding str
     * @param paddingLength int,padding length
     * @param appendFlag    boolean,true[left padding],false[right padding]
     * @return String
     */
    public static String paddingIterator(String str, String paddingStr, int paddingLength, boolean appendFlag) {
        if (CommonUtils.isEmpty(paddingStr) || paddingLength == 0) {
            LOGGER.debug("no need to padding");
            return str;
        }
        // length of fixed padding str
        int paddingStrLength = paddingStr.length();
        // paddingStrLength > paddingLength
        if (paddingStrLength > paddingLength) {
            paddingStr = paddingStr.substring(0, paddingLength);
            return appendByCondition(str, paddingStr, appendFlag);
        }
        str = appendByCondition(str, paddingStr, appendFlag);
        paddingLength -= paddingStrLength;
        return paddingIterator(str, paddingStr, paddingLength, appendFlag);
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
            return "";
        }
        return new String(bytes, ConstantUtils.DEFAULT_CHARSET);
    }

    /**
     * convert byte array to binary string
     *
     * @param bytes byte[]
     * @return String
     */
    public static String convertByteArrayToBinaryString(byte[] bytes) {
        if (CommonUtils.isEmpty(bytes)) {
            LOGGER.error("empty byte array in method <StringUtils: convertByteArrayToBinaryString>");
            return "";
        }
        return convertByteArrayToSpecialFormat(bytes, 2);
    }

    /**
     * convert byte array to hex string
     *
     * @param bytes byte[]
     * @return String
     */
    public static String convertByteArrayToHexString(byte[] bytes) {
        if (CommonUtils.isEmpty(bytes)) {
            LOGGER.error("empty byte array in method <StringUtils: convertByteArrayToHexString>");
            return "";
        }
        return convertByteArrayToSpecialFormat(bytes, 16);
    }

    /**
     * convert byte array to special format
     *
     * @param bytes byte[]
     * @param radix int
     * @return String
     */
    private static String convertByteArrayToSpecialFormat(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);
    }

    /**
     * convert hex string to byte array
     *
     * @param hexString String
     * @return byte[]
     */
    public static byte[] convertHexStringToByteArray(String hexString) {
        if (CommonUtils.isEmpty(hexString)) {
            LOGGER.error("empty data in method <StringUtils: convertHexStringToByteArray>");
            return new byte[0];
        }
        byte[] byteArray = new byte[hexString.length() / 2];
        for (int i = 0; i < byteArray.length; i++) {
            String subStr = hexString.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte) Integer.parseInt(subStr, 16));
        }
        return byteArray;
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
