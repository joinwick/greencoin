package com.gc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author join wick
 * @version 1.0.0
 * @className BigDecimalUtils.java
 * @description BigDecimal Util
 * @createDate 2020/12/10 16:09
 * @since 1.0.0
 */
public final class BigDecimalUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BigDecimalUtils.class);
    private BigDecimalUtils(){

    }

    /**
     * add operation
     * @param x     x
     * @param y     y
     * @return      BigDecimal
     */
    public static BigDecimal add(BigDecimal x, BigDecimal y) {
        return x.add(y);
    }

    /**
     * subtract operation
     * @param x     x
     * @param y     y
     * @return      BigDecimal
     */
    public static BigDecimal subtract(BigDecimal x, BigDecimal y) {
        return x.subtract(y);
    }

    /**
     * multiply operation
     * @param x     x
     * @param y     y
     * @return      multiply operation
     */
    public static BigDecimal multiply(BigDecimal x, BigDecimal y) {
        return x.multiply(y);
    }

    /**
     * multiply operation with scale
     * @param x                 x
     * @param y                 y
     * @param scale             scale
     * @param roundingMode      rounding mode
     * @return                  BigDecimal
     */
    public static BigDecimal multiply(BigDecimal x, BigDecimal y, int scale, RoundingMode roundingMode) {
        return x.multiply(y).setScale(scale, roundingMode);
    }

    /**
     * divide operation with scale
     * @param x                 x
     * @param y                 y
     * @param scale             scale
     * @param roundingMode      rounding mode
     * @return                  BigDecimal
     */
    public static BigDecimal divide(BigDecimal x, BigDecimal y, int scale, RoundingMode roundingMode){
        if (x == null || y == null){
            LOGGER.info("x & y should not be null");
            return BigDecimal.ZERO.setScale(scale, roundingMode);
        }
        if (y.signum() == 0){
            return x.signum() > 0 ? BigDecimal.valueOf(Double.MAX_VALUE) : BigDecimal.valueOf(Double.MIN_VALUE);
        }
        else {
            return x.divide(y, scale, roundingMode);
        }
    }

    /**
     *
     * @param x     x
     * @param y     y
     * @return      int,-2:x or y is null; 0:equal; 1:x > y; -1: x < y
     */
    public static int compareTo(BigDecimal x, BigDecimal y) {
        if (x == null || y == null) {
            LOGGER.error("x & y should not be null");
            return -2;
        }
        return x.compareTo(y);
    }

    /**
     * calculate abs(x)
     * @param x     x
     * @return      BigDecimal
     */
    public static BigDecimal abs(BigDecimal x){
        return x.signum() < 0 ? x.negate() : x;
    }

    /**
     * average operation
     * @param xArray                x-Array
     * @param scale                 scale
     * @param roundingMode          rounding mode
     * @param nullItemIgnoreFlag    ignore null item flag
     * @return                      BigDecimal
     */
    public static BigDecimal avg(BigDecimal[] xArray, int scale, RoundingMode roundingMode, boolean nullItemIgnoreFlag) {
        if (xArray == null || xArray.length == 0) {
            return BigDecimal.ZERO.setScale(scale, roundingMode);
        }
        BigDecimal sumDecimal = BigDecimal.ZERO;
        int validCount = 0;
        for (BigDecimal x : xArray) {
            if (x != null){
                sumDecimal = add(sumDecimal, x);
                validCount ++;
            }
        }
        if (nullItemIgnoreFlag){
            if (validCount == 0){
                return divide(sumDecimal, BigDecimal.valueOf(validCount + 1.0), scale, roundingMode);
            }
            return divide(sumDecimal, BigDecimal.valueOf(validCount), scale, roundingMode);
        }
        return divide(sumDecimal, BigDecimal.valueOf(xArray.length), scale, roundingMode);
    }

    /**
     * pow operation
     * @param x                 x
     * @param y                 y
     * @param scale             scale
     * @param roundingMode      rounding mode
     * @return                  BigDecimal
     */
    public static BigDecimal pow(BigDecimal x, BigDecimal y, int scale, RoundingMode roundingMode){
        return BigDecimal.valueOf(Math.pow(x.doubleValue(), y.doubleValue())).setScale(scale, roundingMode);
    }

    /**
     * remove null value
     * @param xArray        x-Array
     * @return              List<BigDecimal>
     */
    public static List<BigDecimal> removeNullValue(BigDecimal[] xArray){
        List<BigDecimal> validList = new ArrayList<>();
        if (xArray == null || xArray.length == 0) {
            return validList;
        }
        for(BigDecimal bigDecimal : xArray){
            if (bigDecimal != null){
                validList.add(bigDecimal);
            }
        }
        return validList;
    }

    /**
     * get minimum value
     * @param xList             List<BigDecimal>
     * @param scale             scale
     * @param roundingMode      rounding mode
     * @return                  BigDecimal
     */
    public static BigDecimal getMinValue(List<BigDecimal> xList, int scale, RoundingMode roundingMode){
        if (xList == null || xList.isEmpty()){
            return BigDecimal.ZERO.setScale(scale, roundingMode);
        }
        BigDecimal referenceBigDecimal = xList.get(0);
        for (int i = 1; i < xList.size(); i++) {
            if (xList.get(i) != null && referenceBigDecimal != null){
                referenceBigDecimal = compareTo(referenceBigDecimal, xList.get(i)) >= 0 ? xList.get(i) : referenceBigDecimal;
            }
        }
        if (referenceBigDecimal != null) {
            return referenceBigDecimal.setScale(scale, roundingMode);
        }
        else {
            LOGGER.error("referenceDecimal is null, return default value with 0");
            return BigDecimal.ZERO.setScale(scale, roundingMode);
        }
    }

    /**
     * get maximum value
     * @param xList             List<BigDecimal>
     * @param scale             scale
     * @param roundingMode      rounding mode
     * @return                  BigDecimal
     */
    public static BigDecimal getMaxValue(List<BigDecimal> xList, int scale, RoundingMode roundingMode){
        if (xList == null || xList.isEmpty()){
            return BigDecimal.ZERO.setScale(scale, roundingMode);
        }
        BigDecimal referenceBigDecimal = xList.get(0);
        for (int i = 1; i < xList.size(); i++) {
            if (xList.get(i) != null && referenceBigDecimal != null){
                referenceBigDecimal = compareTo(referenceBigDecimal, xList.get(i)) >= 0 ? referenceBigDecimal : xList.get(i);
            }
        }
        if (referenceBigDecimal != null) {
            return referenceBigDecimal.setScale(scale, roundingMode);
        }
        else {
            LOGGER.error("referenceDecimal is null, return default value with 0");
            return BigDecimal.ZERO.setScale(scale, roundingMode);
        }
    }

    /**
     * convert string array to BigDecimal array
     * @param xArray    x-Array
     * @return          BigDecimal[]
     */
    public static BigDecimal[] arrayConvertStringToBigDecimal(String[] xArray){
        if (xArray == null || xArray.length == 0){
            LOGGER.error("xArray must not be empty in method<BigDecimalUtil: arrayConvertStringToBigDecimal>");
            throw new IllegalArgumentException("xArray must not be empty in method<BigDecimalUtil: arrayConvertStringToBigDecimal>, please check!");
        }
        int length = xArray.length;
        BigDecimal[] convertRes = new BigDecimal[length];
        for (int i = 0; i < length; i++){
            convertRes[i] = new BigDecimal(xArray[i]);
        }
        return convertRes;
    }

    /**
     * convert BigDecimal array to string array
     * @param xArray    x-Array
     * @return          String[]
     */
    public static String[] arrayConvertBigDecimalToString(BigDecimal[] xArray){
        if (xArray == null || xArray.length == 0){
            LOGGER.error("xArray must not be empty in method<BigDecimalUtil: arrayConvertStringToBigDecimal>");
            throw new IllegalArgumentException("xArray must not be empty in method<BigDecimalUtil: arrayConvertStringToBigDecimal>, please check!");
        }
        int length = xArray.length;
        String[] convertRes = new String[length];
        for (int i = 0; i < length; i++){
            convertRes[i] = xArray[i].toString();
        }
        return convertRes;
    }

    /**
     * convert double array to BigDecimal array
     * @param xArray    x-Array
     * @return          BigDecimal[]
     */
    public static BigDecimal[] arrayConvertDoubleToBigDecimal(double[] xArray){
        if (xArray == null || xArray.length == 0){
            LOGGER.error("xArray must not be empty in method<BigDecimalUtil: arrayConvertDoubleToBigDecimal>");
            throw new IllegalArgumentException("xArray must not be empty in method<BigDecimalUtil: arrayConvertDoubleToBigDecimal>, please check!");
        }
        int length = xArray.length;
        BigDecimal[] convertRes = new BigDecimal[length];
        for (int i = 0; i < length; i++){
            convertRes[i] = BigDecimal.valueOf(xArray[i]);
        }
        return convertRes;
    }

}
