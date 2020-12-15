package com.gc.database;

import com.gc.exception.GCException;
import com.gc.exception.SystemErrorCode;
import com.gc.utils.CommonUtils;
import com.gc.utils.FileUtils;
import com.gc.utils.PropertyUtils;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author join wick
 * @version 1.0.0
 * @description redis operation
 * @createDate 2020/12/10 8:49
 * @since 1.0.0
 */
public class LevelDBOperation {
    private static final Logger LOGGER = LoggerFactory.getLogger(LevelDBOperation.class);

    public DB getDB() throws IOException {
        DBFactory dbFactory = new Iq80DBFactory();
        Options options = new Options();
        options.createIfMissing(true);
        String filePath = PropertyUtils.getProperty("gc.db.file");
        // file not exist
        if (!FileUtils.fileExist(filePath)){
            // create file failed
            if (!FileUtils.fileDirCreate(filePath)){
                LOGGER.error("create file <{}> failed", filePath);
                throw new GCException(SystemErrorCode.SYSTEM_RESOURCE_FILE_CREATE_FAILED);
            }
        }
        return dbFactory.open(new File(filePath), options);
    }

    public boolean storeData(DB db, String key, String value){
        if (CommonUtils.isEmpty(key)){
            LOGGER.error("key should not be empty");
            throw new GCException(SystemErrorCode.KEY_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(value)){
            LOGGER.error("value should not be empty");
            throw new GCException(SystemErrorCode.VALUE_IS_EMPTY);
        }
        db.put(Iq80DBFactory.bytes(key), Iq80DBFactory.bytes(value));
        return true;
    }
}
