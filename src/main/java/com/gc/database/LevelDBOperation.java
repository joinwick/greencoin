package com.gc.database;

import com.gc.exception.GCException;
import com.gc.exception.SystemErrorCode;
import com.gc.utils.CommonUtils;
import com.gc.utils.FileUtils;
import com.gc.utils.PropertyUtils;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author join wick
 * @version 1.0.0
 * @description redis operation
 * @createDate 2020/12/10 8:49
 * @since 1.0.0
 */
public class LevelDBOperation {
    private static final Logger LOGGER = LoggerFactory.getLogger(LevelDBOperation.class);
    private DB db = null;
    public LevelDBOperation(){
        initDB();
    }

    /**
     * init level db
     */
    private void initDB() {
        DBFactory dbFactory = new Iq80DBFactory();
        Options options = new Options();
        options.createIfMissing(true);
        String filePath = PropertyUtils.getProperty("gc.db.file");
        // create fir failed
        if (!FileUtils.fileExist(filePath) && !FileUtils.fileDirCreate(filePath)) {
            LOGGER.error("create file <{}> failed", filePath);
            throw new GCException(SystemErrorCode.SYSTEM_RESOURCE_FILE_CREATE_FAILED);
        }
        try {
            db = dbFactory.open(new File(filePath), options);
        } catch (IOException e) {
            LOGGER.error("db start failed");
            throw new GCException(SystemErrorCode.DB_INIT_FAILED);
        }
    }

    /**
     * store value into db
     *
     * @param key   String
     * @param value String
     * @return boolean
     */
    public boolean insert(String key, String value) {
        if (CommonUtils.isEmpty(key)) {
            LOGGER.error("key should not be empty in method<LevelDBOperation: storeData>");
            throw new GCException(SystemErrorCode.KEY_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(value)) {
            LOGGER.error("value should not be empty");
            throw new GCException(SystemErrorCode.VALUE_IS_EMPTY);
        }
        try {
            db.put(Iq80DBFactory.bytes(key), Iq80DBFactory.bytes(value));
            return true;
        } catch (Exception e) {
            LOGGER.error("data insert error: <{}>", e.getMessage());
            throw new GCException(SystemErrorCode.DB_INSERT_FAILED);
        }
    }

    /**
     * query data by key
     *
     * @param key String
     * @return String
     */
    public String query(String key) {
        if (CommonUtils.isEmpty(key)) {
            LOGGER.error("key should not be empty in method<LevelDBOperation: queryData>");
            throw new GCException(SystemErrorCode.KEY_NOT_EXISTS);
        }
        try {
            return Iq80DBFactory.asString(db.get(Iq80DBFactory.bytes(key)));
        } catch (Exception e) {
            LOGGER.error("data query error: <{}>", e.getMessage());
            throw new GCException(SystemErrorCode.DB_QUERY_FAILED);
        }
    }

    /**
     * delete data by key
     *
     * @param key String
     * @return boolean
     */
    public boolean delete(String key) {
        if (CommonUtils.isEmpty(key)) {
            LOGGER.error("key should not be empty in method<LevelDBOperation: deleteData>");
            throw new GCException(SystemErrorCode.KEY_NOT_EXISTS);
        }
        try {
            db.delete(Iq80DBFactory.bytes(key));
            return true;
        } catch (Exception e) {
            LOGGER.error("data delete error: <{}>", e.getMessage());
            throw new GCException(SystemErrorCode.DB_DELETE_FAILED);
        }
    }

    /**
     * query all keys
     *
     * @return List<String>
     */
    public List<String> getAllKeys() {
        List<String> keyList = new ArrayList<>();
        try (DBIterator dbIterator = db.iterator()) {
            while (dbIterator.hasNext()) {
                Map.Entry<byte[], byte[]> item = dbIterator.next();
                keyList.add(Iq80DBFactory.asString(item.getKey()));
            }
        } catch (IOException e) {
            LOGGER.error("key query error: <{}>", e.getMessage());
            throw new GCException(SystemErrorCode.DB_QUERY_FAILED);
        }
        return keyList;
    }

    /**
     * close db resource
     */
    public void closeDB() {
        if (db != null) {
            try {
                db.close();
            } catch (IOException e) {
                LOGGER.error("db close error: <{}>", e.getMessage());
                throw new GCException(SystemErrorCode.DB_CLOSE_FAILED);
            }
        }
    }

    public static void main(String[] args) {
        String privateKey = "privateKey";
        String privateKeyValue = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEzkchRA2weIWmMpaRsCejeNgq3gyCpMQMPJtCXQS1JUZTmYEnOmLpzugp9Eoh0cJDCJhhwp2d9kZJAWqysTNYAg==";
        String publicKey = "publicKey";
        String publicKeyValue = "1HHdvEcZgbBRWdG7xMCG84Ysiuw4kbjV8n";
        LevelDBOperation levelDBOperation = new LevelDBOperation();
//        levelDBOperation.initDB();
        boolean insertRes = levelDBOperation.insert(privateKey, privateKeyValue);
        insertRes = levelDBOperation.insert(publicKey, publicKeyValue);
        LOGGER.debug("insert res <{}>", insertRes);
        LOGGER.debug("private key <{}>", levelDBOperation.query(privateKey));
        LOGGER.debug("key size <{}>", levelDBOperation.getAllKeys().size());
        levelDBOperation.closeDB();
    }


}
