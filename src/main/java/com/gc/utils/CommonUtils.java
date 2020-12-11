package com.gc.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * @author join wick
 * @version 1.0.0
 * @className CommonUtils.java
 * @description common utils
 * @createDate 2020/12/10 14:19
 * @since 1.0.0
 */
public class CommonUtils {
    private CommonUtils() {
    }

    /**
     * check object is empty
     *
     * @param object Object
     * @return boolean
     */
    public static boolean isEmpty(Object object) {
        return object == null || "".equals(object);
    }

    /**
     * check collection is empty
     *
     * @param collection Collection<?>
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * check map is empty
     *
     * @param map Map<?, ?>
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * check object array is empty
     *
     * @param objects Object[]
     * @return boolean
     */
    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    /**
     * check basic byte(8:[-128, 127]) array is empty
     *
     * @param bytes byte[]
     * @return boolean
     */
    public static boolean isEmpty(byte[] bytes) {
        return bytes == null || bytes.length == 0;
    }

    /**
     * check basic short(16:[-32768, 32767]) array is empty
     *
     * @param shorts short[]
     * @return boolean
     */
    public static boolean isEmpty(short[] shorts) {
        return shorts == null || shorts.length == 0;
    }

    /**
     * check basic int(32:[-2147483648, 2147483647]) array is empty
     *
     * @param ints int[]
     * @return boolean
     */
    public static boolean isEmpty(int[] ints) {
        return ints == null || ints.length == 0;
    }

    /**
     * check basic long(64:[-9223372036854775808, 9223372036854775807]) array is empty
     *
     * @param longs long[]
     * @return boolean
     */
    public static boolean isEmpty(long[] longs) {
        return longs == null || longs.length == 0;
    }

    /**
     * check basic float(32) array is empty
     *
     * @param floats float[]
     * @return boolean
     */
    public static boolean isEmpty(float[] floats) {
        return floats == null || floats.length == 0;
    }

    /**
     * check basic double(64) array is empty
     *
     * @param doubles double[]
     * @return boolean
     */
    public static boolean isEmpty(double[] doubles) {
        return doubles == null || doubles.length == 0;
    }

    /**
     * check basic boolean(1) array is empty
     *
     * @param booleans boolean[]
     * @return boolean
     */
    public static boolean isEmpty(boolean[] booleans) {
        return booleans == null || booleans.length == 0;
    }

    /**
     * check basic char(16[\u0000, \uffff]) array is empty
     *
     * @param chars char[]
     * @return boolean
     */
    public static boolean isEmpty(char[] chars) {
        return chars == null || chars.length == 0;
    }

    /**
     * merge byte array
     *
     * @param bytes1 byte[]
     * @param bytes2 byte[]
     * @return byte[]
     */
    public static byte[] mergeArray(byte[] bytes1, byte[] bytes2) {
        byte[] res = Arrays.copyOf(bytes1, bytes1.length + bytes2.length);
        System.arraycopy(bytes2, 0, res, bytes1.length, bytes2.length);
        return res;
    }


}
