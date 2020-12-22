package com.gc.utils;

import org.apache.lucene.util.RamUsageEstimator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @description object utils
 * @createDate 2020/12/22 13:48
 * @since 1.0.0
 */
public class ObjectUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtils.class);

    /**
     * get object & reference object size, bytes
     *
     * @param obj Object
     * @return long
     */
    public static long getObjectSize(Object obj) {
        if (obj == null) {
            LOGGER.error("object is null in method <ObjectUtils: getObjectSize>");
            return 0L;
        }
        return RamUsageEstimator.sizeOfObject(obj);
    }

    /**
     * get object & reference object heap size, bytes
     *
     * @param obj Object
     * @return long
     */
    public static long getObjectHeapSize(Object obj) {
        if (obj == null) {
            LOGGER.error("object is null in method <ObjectUtils: getObjectHeapSize>");
            return 0L;
        }
        return RamUsageEstimator.shallowSizeOf(obj);
    }
}
