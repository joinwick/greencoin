package com.gc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author join wick
 * @version 1.0.0
 * @description file utils
 * @createDate 2020/12/15 16:41
 * @since 1.0.0
 */
public class FileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    /**
     * check file exists
     *
     * @param filePath String
     * @return boolean
     */
    public static boolean fileExist(String filePath) {
        if (CommonUtils.isEmpty(filePath)) {
            LOGGER.error("file path should not be empty");
            return false;
        }
        return new File(filePath).exists();
    }

    /**
     * create file dir
     *
     * @param filePath String
     * @return boolean
     */
    public static boolean fileDirCreate(String filePath) {
        if (CommonUtils.isEmpty(filePath)) {
            LOGGER.error("file dir should not be empty");
            return false;
        }
        if (!fileExist(filePath)) {
            return new File(filePath).mkdirs();
        }
        return true;
    }
}
