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

    private ConvertUtils(){}

    public static String convertHexToTen(String hexString){
        return new BigInteger(hexString, 16).toString();
    }

    public static String convertTenToHex(String tenString){
        return String.format("%08x", BigInteger.valueOf(Long.parseLong(tenString)));
    }
}
