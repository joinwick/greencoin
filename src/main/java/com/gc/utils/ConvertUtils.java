package com.gc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * @author join wick
 * @version 1.0.0
 * @description convert utils
 * @createDate 2020/12/23 16:41
 * @since 1.0.0
 */
public class ConvertUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertUtils.class);

    private ConvertUtils() {
    }

    /**
     * convert hex string to decimal string
     *
     * @param hexString String
     * @return String
     */
    public static String convertHexToTen(String hexString) {
        if (CommonUtils.isEmpty(hexString)) {
            LOGGER.error("empty data in method<ConvertUtils: convertHexToTen>");
            return "";
        }
        return new BigInteger(hexString, 16).toString();
    }

    /**
     * convert decimal string to hex string
     *
     * @param tenString String
     * @return String
     */
    public static String convertTenToHex(String tenString) {
        if (CommonUtils.isEmpty(tenString)) {
            LOGGER.error("empty data in method<ConvertUtils: convertTenToHex>");
            return "";
        }
        // get hex string
        String hexString = String.format(ConstantUtils.DEFAULT_HEX_STRING_FORMAT, new BigInteger(tenString));
        // get padding length
        int paddingLength = ConstantUtils.DEFAULT_HASH_HEX_LENGTH - hexString.length();
        // get filling res
        return StringUtils.paddingIterator(hexString, ConstantUtils.DEFAULT_ZERO_STRING, paddingLength, ConstantUtils.DEFAULT_APPEND_FLAG);
    }

    public static String convertBinaryToTen(String binaryString) {

        return "";

    }
}
