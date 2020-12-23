package com.gc.utils;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
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

    private ObjectUtils(){}

    /**
     * get object size, bytes
     *
     * @param obj Object
     * @return long
     */
    public static long getObjectSize(Object obj) {
        if (obj == null) {
            LOGGER.error("object is null in method <ObjectUtils: getObjectSize>");
            return 0L;
        }
        return ClassLayout.parseInstance(obj).instanceSize();
    }

    /**
     * get object & reference object size, bytes
     *
     * @param obj Object
     * @return long
     */
    public static long getObjectTotalSize(Object obj) {
        if (obj == null) {
            LOGGER.error("object is null in method <ObjectUtils: getObjectTotalSize>");
            return 0L;
        }
        return GraphLayout.parseInstance(obj).totalSize();
    }
}
