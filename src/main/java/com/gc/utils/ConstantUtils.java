package com.gc.utils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author join wick
 * @version 1.0.0
 * @className ConstantUtils.java
 * @description constant definition
 * @createDate 2020/12/04 23:56:00
 * @since 1.0.0
 */
public final class ConstantUtils {
    // no need to user constructor
    private ConstantUtils(){

    }
    // default server ip
    public static final String DEFAULT_SERVER_IP = "127.0.0.1";
    // default server port
    public static final int DEFAULT_SERVER_PORT = 9081;
    // default select loop time
    public static final int DEFAULT_SELECTOR_LOOP_TIME = 1000;
    // default buffer size
    public static final int DEFAULT_BYTE_BUFFER_ALLOCATION = 1024;
    // default charset
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    // default base58
    public static final BigInteger BASE58_LENGTH = BigInteger.valueOf(58);
    // default base58 characters
    public static final String BASE58_CHARACTERS = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

    /*private static final String[] BASE58_BYTES = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static String[] getBase58Bytes() {
        return BASE58_BYTES;
    }*/
}
