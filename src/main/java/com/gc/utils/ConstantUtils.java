package com.gc.utils;

import java.io.File;
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
    /**
     * no need to gen constructor
     */
    private ConstantUtils() {

    }

    /**
     * default server ip
     */
    public static final String DEFAULT_SERVER_IP = "127.0.0.1";
    /**
     * default server port
     */
    public static final int DEFAULT_SERVER_PORT = 9081;
    /**
     * default select loop time
     */
    public static final int DEFAULT_SELECTOR_LOOP_TIME = 1000;
    /**
     * default time out
     */
    public static final int DEFAULT_TIME_OUT = 3000;
    /**
     * default buffer size
     */
    public static final int DEFAULT_BYTE_BUFFER_ALLOCATION = 1024;
    /**
     * default thread num
     */
    public static final int DEFAULT_THREAD_COUNT = 10;
    /**
     * default charset
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    /**
     * default base58
     */
    public static final BigInteger DEFAULT_BASE58_LENGTH = BigInteger.valueOf(58);
    /**
     * default base58 characters
     */
    public static final String DEFAULT_BASE58_CHARACTERS = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    /**
     * default fix prefix
     */
    public static final String DEFAULT_GC_KEY_PREFIX = "0";
    /**
     * default address fix length
     */
    public static final int DEFAULT_ACCOUNT_FIX_LENGTH = 4;
    /**
     * default address fix prefix
     */
    public static final String DEFAULT_GC_ACCOUNT_PREFIX = "04";
    /**
     * default private key length
     */
    public static final int DEFAULT_PRIVATE_KEY_LENGTH = 64;
    /**
     * default public key length
     */
    public static final int DEFAULT_PUBLIC_KEY_LENGTH = 128;
    /**
     * default encrypt size
     */
    public static final int DEFAULT_ENCRYPT_SIZE = 4096;
    /**
     * default config file
     */
    public static final String DEFAULT_CONFIG_FILE = "/gc-config.properties";
    /**
     * default file separator
     */
    public static final String DEFAULT_FILE_SEPARATOR = File.separator;
    /**
     * default private key
     */
    public static final String DEFAULT_PRIVATE_KEY = "privateKey";
    /**
     * default public key
     */
    public static final String DEFAULT_PUBLIC_KEY = "publicKey";
    /**
     * default symmetric key
     */
    public static final String DEFAULT_SYMMETRIC_KEY = "SymmetricKey";
    /**
     * default date time format
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * default hash length(hex format)
     */
    public static final int DEFAULT_HASH_HEX_LENGTH = 64;
    /**
     * default hash length(binary format)
     */
    public static final int DEFAULT_HASH_BINARY_LENGTH = 256;
    /**
     * default hex string
     */
    public static final String DEFAULT_HEX_STRING = "0123456789abcdef";
    /**
     * default zero string
     */
    public static final String DEFAULT_ZERO_STRING = "0";
    /**
     * default zero char
     */
    public static final char DEFAULT_ZERO_CHAR = '0';
    /**
     * default bits prefix
     */
    public static final String DEFAULT_BITS_PREFIX = "0x";
    /**
     * default hex string format
     */
    public static final String DEFAULT_HEX_STRING_FORMAT = "%08x";
    /**
     * default append flag(true-left padding, false-right padding)
     */
    public static final boolean DEFAULT_APPEND_FLAG = true;
    /**
     * default power base
     */
    public static final BigInteger DEFAULT_POWER_BASE = BigInteger.valueOf(2);
    /**
     * default target factor 0x08
     */
    public static final BigInteger DEFAULT_TARGET_FACTOR_ONE = BigInteger.valueOf(8);
    /**
     * default target factor 0x03
     */
    public static final BigInteger DEFAULT_TARGET_FACTOR_TWO = BigInteger.valueOf(3);
    /**
     * default valid target length
     */
    public static final int DEFAULT_VALID_TARGET_LENGTH = 10;
    /**
     * default coefficient length
     */
    public static final int DEFAULT_COEFFICIENT_LENGTH = 6;
    /**
     * default minimum format
     */
    public static final int DEFAULT_MIN_FORMAT = 2;
    /**
     * default maximum format
     */
    public static final int DEFAULT_MAX_FORMAT = 30;
    /**
     * default difficulty hex target
     */
    public static final String DEFAULT_DIFFICULTY_HEX_TARGET = "00000000ffff0000000000000000000000000000000000000000000000000000";
    /**
     * default difficulty adjust block count
     */
    public static final int DEFAULT_BLOCK_COUNT = 2020;
    /**
     * default difficulty adjust period,seconds
     */
    public static final int DEFAULT_DIFFICULTY_ADJUST_PERIOD = 10 * 60 * DEFAULT_BLOCK_COUNT;
}
