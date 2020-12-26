package com.gc.cryptology;

import com.gc.common.entity.EnumEntity;
import com.gc.common.inter.BaseCryptologyInterface;
import com.gc.exception.GCException;
import com.gc.exception.SystemErrorCode;
import com.gc.utils.CommonUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description symmetric crypto
 * @createDate 2020/12/18 22:58
 * @since 1.0.0
 */
public class SymmetricCrypto implements BaseCryptologyInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(SymmetricCrypto.class);

    // init security provider
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * use symmetric key to encrypting data
     *
     * @param data byte[]
     * @param key  byte[]
     * @return byte[]
     */
    @Override
    public byte[] encrypt(byte[] data, byte[] key) {
        if (CommonUtils.isEmpty(data)) {
            throw new GCException(SystemErrorCode.DATA_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(key)) {
            throw new GCException(SystemErrorCode.SYMMETRIC_KEY_NOT_EXISTS);
        }
        byte[] encryptData = new byte[0];
        try {
            // get SecretKey
            SecretKey secretKey = getKey(key, EnumEntity.SymmetricAlgorithm.AES);
            // get cipher
            Cipher cipher = Cipher.getInstance(EnumEntity.SymmetricAlgorithm.AES.getValue());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptData = cipher.doFinal(data);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            LOGGER.error("encrypt data failed with reason <{}>", e.getMessage());
        }
        return encryptData;
    }

    /**
     * use symmetric key to decrypting data
     *
     * @param data byte[]
     * @param key  byte[]
     * @return byte[]
     */
    @Override
    public byte[] decrypt(byte[] data, byte[] key) {
        if (CommonUtils.isEmpty(data)) {
            throw new GCException(SystemErrorCode.DATA_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(key)) {
            throw new GCException(SystemErrorCode.SYMMETRIC_KEY_NOT_EXISTS);
        }
        byte[] decryptData = new byte[0];
        try {
            // get SecretKey
            SecretKey secretKey = getKey(key, EnumEntity.SymmetricAlgorithm.AES);
            // get cipher
            Cipher cipher = Cipher.getInstance(EnumEntity.SymmetricAlgorithm.AES.getValue());
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptData = cipher.doFinal(data);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LOGGER.error("decrypt data failed with reason <{}>", e.getMessage());
        }
        return decryptData;
    }

    /**
     * use symmetric key to encrypting data
     *
     * @param data               byte[]
     * @param key                byte[]
     * @param symmetricAlgorithm EnumEntity.SymmetricAlgorithm
     * @return byte[]
     */
    public byte[] encrypt(byte[] data, byte[] key, EnumEntity.SymmetricAlgorithm symmetricAlgorithm) {
        if (CommonUtils.isEmpty(data)) {
            throw new GCException(SystemErrorCode.DATA_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(key)) {
            throw new GCException(SystemErrorCode.SYMMETRIC_KEY_NOT_EXISTS);
        }
        byte[] encryptData = new byte[0];
        try {
            // get SecretKey
            SecretKey secretKey = getKey(key, symmetricAlgorithm);
            // get cipher
            Cipher cipher = Cipher.getInstance(symmetricAlgorithm.getValue());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptData = cipher.doFinal(data);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            LOGGER.error("encrypt data failed with reason <{}>", e.getMessage());
        }
        return encryptData;
    }

    /**
     * use symmetric key to decrypting data
     *
     * @param data               byte[]
     * @param key                byte[]
     * @param symmetricAlgorithm EnumEntity.SymmetricAlgorithm
     * @return byte[]
     */
    public byte[] decrypt(byte[] data, byte[] key, EnumEntity.SymmetricAlgorithm symmetricAlgorithm) {
        if (CommonUtils.isEmpty(data)) {
            throw new GCException(SystemErrorCode.DATA_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(key)) {
            throw new GCException(SystemErrorCode.SYMMETRIC_KEY_NOT_EXISTS);
        }
        byte[] decryptData = new byte[0];
        try {
            // get SecretKey
            SecretKey secretKey = getKey(key, symmetricAlgorithm);
            // get cipher
            Cipher cipher = Cipher.getInstance(symmetricAlgorithm.getValue());
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptData = cipher.doFinal(data);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LOGGER.error("decrypt data failed with reason <{}>", e.getMessage());
        }
        return decryptData;
    }

    /**
     * convert byte[] to symmetric key according to SymmetricAlgorithm
     *
     * @param key                byte[]
     * @param symmetricAlgorithm EnumEntity.SymmetricAlgorithm
     * @return SecretKey
     */
    private SecretKey getKey(byte[] key, EnumEntity.SymmetricAlgorithm symmetricAlgorithm) {
        if (CommonUtils.isEmpty(key)) {
            LOGGER.error("symmetric key is empty");
            throw new GCException(SystemErrorCode.SYMMETRIC_KEY_NOT_EXISTS);
        }
        return new SecretKeySpec(key, symmetricAlgorithm.getValue());
    }
}
