package com.gc.utils;

import com.gc.exception.GCException;
import com.gc.exception.SystemErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * @author join wick
 * @version 1.0.0
 * @description biginteger utils
 * @createDate 2020/12/10 21:09
 * @since 1.0.0
 */
public class BigIntegerUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BigIntegerUtils.class);

    private BigIntegerUtils() {
    }

    /**
     * add calculation
     *
     * @param x BigInteger
     * @param y BigInteger
     * @return BigInteger
     */
    public static BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    /**
     * mod calculation
     *
     * @param x BigInteger
     * @param y BigInteger
     * @return BigInteger
     */
    public static BigInteger mod(BigInteger x, BigInteger y) {
        return x.mod(y);
    }

    /**
     * subtract calculation
     *
     * @param x BigInteger
     * @param y BigInteger
     * @return BigInteger
     */
    public static BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    /**
     * multiply calculation
     *
     * @param x BigInteger
     * @param y BigInteger
     * @return BigInteger
     */
    public static BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    /**
     * divide calculation
     *
     * @param x BigInteger
     * @param y BigInteger
     * @return BigInteger
     */
    public static BigInteger divide(BigInteger x, BigInteger y) {
        if (y.compareTo(BigInteger.ZERO) == 0) {
            LOGGER.error("divisor is zero in method<BigIntegerUtils: divide>");
            throw new GCException(SystemErrorCode.DIVISOR_IS_ZERO);
        }
        return x.divide(y);
    }

    /**
     * pow calculation
     *
     * @param x BigInteger
     * @param y BigInteger
     * @return BigInteger
     */
    public static BigInteger pow(BigInteger x, BigInteger y) {
        return x.pow(y.intValue());
    }

}
