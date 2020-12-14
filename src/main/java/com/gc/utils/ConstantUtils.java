package com.gc.utils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author join wick
 * @version 1.0.0
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
    // default thread num
    public static final int DEFAULT_THREAD_COUNT = 10;
    // default charset
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    // default base58
    public static final BigInteger DEFAULT_BASE58_LENGTH = BigInteger.valueOf(58);
    // default base58 characters
    public static final String DEFAULT_BASE58_CHARACTERS = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    // default fix prefix
    public static final String DEFAULT_GC_KEY_PREFIX = "0";
    // default address fix length
    public static final int DEFAULT_ACCOUNT_FIX_LENGTH = 4;
    // default address fix prefix
    public static final String DEFAULT_GC_ACCOUNT_PREFIX = "04";
    // default private key length
    public static final int DEFAULT_PRIVATE_KEY_LENGTH = 64;
    // default public key length
    public static final int DEFAULT_PUBLIC_KEY_LENGTH = 128;
    // default encrypt size
    public static final int DEFAULT_ENCRYPT_SIZE = 4096;
}
