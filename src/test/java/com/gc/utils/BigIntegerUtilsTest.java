package com.gc.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/24 9:58
 * @since 1.0.0
 */
public class BigIntegerUtilsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BigIntegerUtilsTest.class);
    BigInteger x = BigInteger.valueOf(238348);
    BigInteger y = BigInteger.valueOf(176);
    BigInteger base = BigInteger.valueOf(2);

    @Test
    public void add_ValidEntry() {
        int expectedRes = 238524;
        assertEquals(expectedRes, BigIntegerUtils.add(x, y).intValue());
    }

    @Test
    public void mod_ValidEntry() {
        int expectedRes = 44;
        assertEquals(expectedRes, BigIntegerUtils.mod(x, y).intValue());
    }

    @Test
    public void subtract_ValidEntry() {
        int expectedRes = 238172;
        assertEquals(expectedRes, BigIntegerUtils.subtract(x, y).intValue());
    }

    @Test
    public void multiply_ValidEntry() {
        long expectedRes = 41949248;
        assertEquals(expectedRes, BigIntegerUtils.multiply(x, y).longValue());
    }

    @Test
    public void divide_ValidEntry() {
        int expectedRes = 1354;
        assertEquals(expectedRes, BigIntegerUtils.divide(x, y).intValue());
    }

    @Test
    public void pow_ValidEntry() {
        double expectedRes = 9.5780971304118053647396689196894e+52;
        assertEquals(expectedRes, BigIntegerUtils.pow(base, y).doubleValue(), 0.1);

    }

    @Test
    public void complex_Calculation_ValidEntry() {
        BigInteger actualRes = BigIntegerUtils.multiply(x,
                BigIntegerUtils.pow(base, y));
        LOGGER.debug("res = <{}>", actualRes.toString());
        LOGGER.debug("res = <{}>", actualRes.toString(2));
        LOGGER.debug("res = <{}>", actualRes.toString(16));

    }
}