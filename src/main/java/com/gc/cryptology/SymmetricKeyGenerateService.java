package com.gc.cryptology;

import com.gc.common.entity.EnumEntity;
import com.gc.database.LevelDB;
import com.gc.utils.CommonUtils;
import com.gc.utils.ConstantUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

/**
 * @author join wick
 * @version 1.0.0
 * @description generate symmetric key
 * @createDate 2020/12/18 22:11
 * @since 1.0.0
 */
public class SymmetricKeyGenerateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SymmetricKeyGenerateService.class);
    private final BaseCodec baseCodec;

    public SymmetricKeyGenerateService() {
        baseCodec = new BaseCodec();
    }

    /**
     * generate symmetric key
     * return null when create key failed or store key into db failed
     *
     * @param symmetricAlgorithm EnumEntity.SymmetricAlgorithm
     * @param keyLengthType      EnumEntity.KeyLengthType
     * @return String
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    public String createKey(EnumEntity.SymmetricAlgorithm symmetricAlgorithm, EnumEntity.KeyLengthType keyLengthType) throws NoSuchAlgorithmException {
        // add bouncy castle as key pair generator
        Security.addProvider(new BouncyCastleProvider());
        // get key generator
        KeyGenerator keyGenerator = KeyGenerator.getInstance(symmetricAlgorithm.getValue());
        // init key generator
        keyGenerator.init(Integer.parseInt(keyLengthType.getValue()), new SecureRandom());
        // generate symmetric key
        SecretKey secretKey = keyGenerator.generateKey();
        // convert SecretKey to String by base64 encoding
        String secretKeyString = baseCodec.base64Encode(secretKey.getEncoded());
        // save key into db
        if (!saveKeyIntoDB(secretKeyString)) {
            LOGGER.error("key store into db error");
            return null;
        }
        return secretKeyString;
    }

    /**
     * store key into db
     *
     * @param symmetricKey String
     * @return boolean
     */
    private boolean saveKeyIntoDB(String symmetricKey) {
        if (CommonUtils.isEmpty(symmetricKey)) {
            LOGGER.error("symmetric key is empty");
            return false;
        }
        boolean keyInsertRes;
        // get leveldb instance
        LevelDB levelDB = LevelDB.getInstance();
        // synchronized code block
        synchronized (LevelDB.class) {
            levelDB.initDB();
            keyInsertRes = levelDB.insert(ConstantUtils.DEFAULT_SYMMETRIC_KEY, symmetricKey);
            levelDB.closeDB();
        }
        return keyInsertRes;
    }

}
