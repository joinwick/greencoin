package com.gc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * @author join wick
 * @version 1.0.0
 * @description convert utils
 * @createDate 2020/12/23 16:41
 * @since 1.0.0
 */
public class ConvertUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertUtils.class);

    private ConvertUtils() {
    }

    /**
     * convert source format to target format
     *
     * @param sourceString String
     * @param sourceFormat int
     * @param targetFormat int
     * @return String
     */
    public static String convertSourceFormatToSpecialFormat(String sourceString, int sourceFormat, int targetFormat) {
        if (CommonUtils.isEmpty(sourceString)) {
            LOGGER.error("empty data in method<ConvertUtils: convertSourceFormatToSpecialFormat>");
            return "";
        }
        if (sourceFormat < ConstantUtils.DEFAULT_MIN_FORMAT || sourceFormat > ConstantUtils.DEFAULT_MAX_FORMAT) {
            LOGGER.error("unsupported source format in method<ConvertUtils: convertSourceFormatToSpecialFormat>");
            return "";
        }
        if (targetFormat < ConstantUtils.DEFAULT_MIN_FORMAT || targetFormat > ConstantUtils.DEFAULT_MAX_FORMAT) {
            LOGGER.error("unsupported target format in method<ConvertUtils: convertSourceFormatToSpecialFormat>");
            return "";
        }
        return new BigInteger(sourceString, sourceFormat).toString(targetFormat);
    }
}
