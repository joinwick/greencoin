package com.gc.cryptology;

import com.gc.common.entity.EnumEntity;
import com.gc.common.inter.BaseCryptologyInterface;
import com.gc.exception.GCException;
import com.gc.exception.SystemErrorCode;
import com.gc.utils.CommonUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author join wick
 * @version 1.0.0
 * @description symmetric crypto
 * @createDate 2020/12/18 22:58
 * @since 1.0.0
 */
public class SymmetricCrypto implements BaseCryptologyInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(SymmetricCrypto.class);

    private final BaseCodec baseCodec;

    public SymmetricCrypto() {
        baseCodec = new BaseCodec();
    }

    // init security provider
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) {
        if (CommonUtils.isEmpty(data)) {
            throw new GCException(SystemErrorCode.DATA_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(key)) {
            throw new GCException(SystemErrorCode.SYMMETRIC_KEY_NOT_EXISTS);
        }
        byte[] encryptData = new byte[0];
        // get SecretKey
        SecretKey secretKey = getKey(key, EnumEntity.SymmetricAlgorithm.AES);
        // get cipher
//        Cipher cipher = Cipher.getInstance();


        return encryptData;
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) {
        return new byte[0];
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
