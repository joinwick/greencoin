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

    public static final BigInteger ModuloHalb     = new BigInteger("7FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF7FFFFE17", 16);


    public static final BigInteger HALB        = new BigInteger("7fffffffffffffffffffffffffffffff5d576e7357a4501ddfe92f46681b20a1", 16);
    public static final BigInteger BIG_INTEGER_ZERO  = BigInteger.ZERO;
    public static final BigInteger BIG_INTEGER_ONE   = BigInteger.ONE;
    public static final BigInteger BIG_INTEGER_TWO   = BigInteger.valueOf(2);
    public static final BigInteger BIG_INTEGER_THREE = BigInteger.valueOf(3);
    public static final BigInteger BIG_INTEGER_FOUR  = BigInteger.valueOf(4);
    public static final BigInteger BIG_INTEGER_FIVE  = BigInteger.valueOf(5);
    public static final BigInteger BIG_INTEGER_SIX   = BigInteger.valueOf(6);
    public static final BigInteger BIG_INTEGER_SEVEN = BigInteger.valueOf(7);

    public static final BigInteger GC_MODULO       = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16);
    public static final BigInteger GC_GENERATORS   = new BigInteger("79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798", 16);
    public static final BigInteger GC_GENERATORS_Y = new BigInteger("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8", 16);
    public static final BigInteger GC_ORDER_NUM    = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16);


}
