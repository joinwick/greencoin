package com.gc.utils;

import java.math.BigInteger;

/**
 * @author join wick
 * @version 1.0.0
 * @description biginteger utils
 * @createDate 2020/12/10 21:09
 * @since 1.0.0
 */
public class BigIntegerUtils {
    private BigIntegerUtils(){}
    public static BigInteger add(BigInteger x, BigInteger y){
        return x.add(y);
    }

    public static BigInteger mod(BigInteger x, BigInteger y){
        return x.mod(y);
    }

    public static BigInteger subtract(BigInteger x, BigInteger y){
        return x.subtract(y);
    }

    public static BigInteger multiply(BigInteger x, BigInteger y){
        return x.multiply(y);
    }

    public static BigInteger divide(BigInteger x, BigInteger y){
        return x.divide(y);
    }

    public static BigInteger modInverse(BigInteger x, BigInteger y){
        return x.modInverse(y);
    }

    public static BigInteger modPow(BigInteger x, BigInteger y, BigInteger z){
        return x.modPow(y, z);
    }

    public static BigInteger sqrt(BigInteger x){
        BigInteger sqrtRes = BigInteger.ZERO;


        return sqrtRes;
    }

    public static BigInteger addMod(BigInteger x, BigInteger y, BigInteger m){
        return mod(add(x, y), m);
    }

    public static BigInteger addMod(BigInteger x, BigInteger y, BigInteger z, BigInteger m){
        return mod(add(add(x, y), z), m);
    }

    public static BigInteger multiplyMod(BigInteger x, BigInteger y, BigInteger m){
        return mod(multiply(x, y), m);
    }

    public static BigInteger multiplyMod(BigInteger x, BigInteger y, BigInteger z, BigInteger m){
        return mod(multiply(multiply(x, y), z), m);
    }

    public static BigInteger multiplyMod(BigInteger x, BigInteger y, BigInteger z, BigInteger w, BigInteger m){
        return mod(multiply(multiply(x, y), multiply(z, w)), m);
    }


}
