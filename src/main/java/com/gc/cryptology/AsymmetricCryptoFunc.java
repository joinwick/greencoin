package com.gc.cryptology;

import com.gc.common.entity.EnumEntity;
import com.gc.common.inter.SignatureInterface;
import com.gc.exception.GCException;
import com.gc.exception.SystemErrorCode;
import com.gc.utils.CommonUtils;
import com.gc.utils.StringUtils;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author join wick
 * @version 1.0.0
 * @description asymmetric cryptology service
 * @createDate 2020/12/14 16:08
 * @since 1.0.0
 */
public class AsymmetricCryptoFunc implements SignatureInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsymmetricCryptoFunc.class);

    public static void main(String[] args) {
        AsymmetricCryptoFunc asymmetricCryptoFunc = new AsymmetricCryptoFunc();
        BaseCodec baseCodec = new BaseCodec();
        String privateKey = "MIGNAgEAMBAGByqGSM49AgEGBSuBBAAKBHYwdAIBAQQgagwhvyMM4c0a0wMaEO1IY0GKAxUwOiKzvYs2gqtQAqugBwYFK4EEAAqhRANCAAQs618JBzf+V816uhpcNLDSJi9XzEZ9qkSotMD+5UCTeiQn02pZb7vSSssWBPgWiYczNniCmzdrGjkZ5ftAQtzO";
        String publicKey = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAELOtfCQc3/lfNeroaXDSw0iYvV8xGfapEqLTA/uVAk3okJ9NqWW+70krLFgT4FomHMzZ4gps3axo5GeX7QELczg==";
        // get decode keys
        byte[] privateKeys = baseCodec.base64Decode(privateKey);
        byte[] publicKeys = baseCodec.base64Decode(publicKey);

        String data = "123456上海你好";
        byte[] dataBytes = StringUtils.convertStringToByteArray(data);

        byte[] encryptBytes = asymmetricCryptoFunc.encrypt(dataBytes, publicKeys);
        String encryptData = StringUtils.convertByteToHexString(encryptBytes);
        LOGGER.debug("encrypt msg   = <{}>", encryptData);

        byte[] decryptBytes = asymmetricCryptoFunc.decrypt(encryptBytes, privateKeys);
        String decryptData = StringUtils.convertByteArrayToString(decryptBytes);
        LOGGER.debug("decrypt msg   = <{}>", decryptData);

        byte[] signature = asymmetricCryptoFunc.signature(dataBytes, privateKeys);
        String signatureData = StringUtils.convertByteToHexString(signature);
        LOGGER.debug("signature msg = <{}>", signatureData);

        boolean verifyRes = asymmetricCryptoFunc.validation(dataBytes, signature, publicKeys);
        LOGGER.debug("verify msg    = <{}>", verifyRes);

    }

    // init security provider
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * use public publicKey to encrypting data
     *
     * @param data      byte[]
     * @param publicKey byte[]
     * @return byte[]
     */
    @Override
    public byte[] encrypt(byte[] data, byte[] publicKey) {
        if (CommonUtils.isEmpty(data)) {
            throw new GCException(SystemErrorCode.DATA_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(publicKey)) {
            throw new GCException(SystemErrorCode.PUBLIC_KEY_NOT_EXISTS);
        }
        byte[] encryptData = new byte[0];
        try {
            // convert byte(public publicKey) to BCECPublicKey
            BCECPublicKey bcecPublicKey = (BCECPublicKey) getKey(publicKey, EnumEntity.KeyType.PUBLIC_KEY);
            // get cipher
            Cipher cipher = Cipher.getInstance(
                    EnumEntity.EllipticSchema.ECIES.getValue(),
                    EnumEntity.SecurityProvider.BC.getValue());
            // setting cipher
            cipher.init(Cipher.ENCRYPT_MODE, bcecPublicKey);
            encryptData = cipher.doFinal(data);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException |
                InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LOGGER.error("encrypt data failed with reason <{}>", e.getMessage());
        }
        return encryptData;
    }

    /**
     * use private privateKey to decrypting data
     *
     * @param data       byte[]
     * @param privateKey byte[]
     * @return byte[]
     */
    @Override
    public byte[] decrypt(byte[] data, byte[] privateKey) {
        if (CommonUtils.isEmpty(data)) {
            throw new GCException(SystemErrorCode.DATA_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(privateKey)) {
            throw new GCException(SystemErrorCode.PRIVATE_KEY_NOT_EXISTS);
        }
        byte[] decryptData = new byte[0];
        try {
            // convert byte(private privateKey) to BCECPrivateKey
            BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) getKey(privateKey, EnumEntity.KeyType.PRIVATE_KEY);
            // get cipher
            Cipher cipher = Cipher.getInstance(
                    EnumEntity.EllipticSchema.ECIES.getValue(),
                    EnumEntity.SecurityProvider.BC.getValue());
            // setting cipher
            cipher.init(Cipher.DECRYPT_MODE, bcecPrivateKey);
            decryptData = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException | NoSuchPaddingException |
                InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LOGGER.error("decrypt data failed with reason <{}>", e.getMessage());
        }
        return decryptData;
    }

    /**
     * use private key to signature data
     *
     * @param data       byte[]
     * @param privateKey byte[]
     * @return byte[]
     */
    @Override
    public byte[] signature(byte[] data, byte[] privateKey) {
        if (CommonUtils.isEmpty(data)) {
            throw new GCException(SystemErrorCode.DATA_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(privateKey)) {
            throw new GCException(SystemErrorCode.PRIVATE_KEY_NOT_EXISTS);
        }
        byte[] signatureBytes = new byte[0];
        try {
            // convert byte(private key) to BCECPrivateKey
            BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) getKey(privateKey, EnumEntity.KeyType.PRIVATE_KEY);
            // get signature
            Signature signature = Signature.getInstance(
                    EnumEntity.SignatureAlgorithm.SHA256withECDSA.getValue(),
                    EnumEntity.SecurityProvider.BC.getValue());
            signature.initSign(bcecPrivateKey);
            signature.update(data);
            signatureBytes = signature.sign();
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException |
                InvalidKeyException | SignatureException e) {
            LOGGER.error("signature data failed with reason <{}>", e.getMessage());
        }
        return signatureBytes;
    }

    /**
     * use public key to verify signature
     *
     * @param data      byte[]
     * @param sig       byte[]
     * @param publicKey byte[]
     * @return boolean
     */
    @Override
    public boolean validation(byte[] data, byte[] sig, byte[] publicKey) {
        if (CommonUtils.isEmpty(data)) {
            throw new GCException(SystemErrorCode.DATA_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(publicKey)) {
            throw new GCException(SystemErrorCode.PUBLIC_KEY_NOT_EXISTS);
        }
        boolean validationRes = false;
        try {
            // convert byte(public key) to BCECPublicKey
            BCECPublicKey bcecPublicKey = (BCECPublicKey) getKey(publicKey, EnumEntity.KeyType.PUBLIC_KEY);
            // get signature
            Signature signature = Signature.getInstance(
                    EnumEntity.SignatureAlgorithm.SHA256withECDSA.getValue(),
                    EnumEntity.SecurityProvider.BC.getValue());
            signature.initVerify(bcecPublicKey);
            signature.update(data);
            validationRes = signature.verify(sig);

        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException | InvalidKeyException |
                SignatureException e) {
            LOGGER.error("verify signature failed with reason <{}>", e.getMessage());
        }
        return validationRes;
    }

    /**
     * convert byte[] to Key by key type
     *
     * @param key     byte[]
     * @param keyType EnumEntity.KeyType
     * @return Key
     * @throws NoSuchProviderException  NoSuchProviderException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeySpecException  InvalidKeySpecException
     */
    private Key getKey(byte[] key, EnumEntity.KeyType keyType) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(
                EnumEntity.EllipticSchema.EC.getValue(),
                EnumEntity.SecurityProvider.BC.getValue());
        if (EnumEntity.KeyType.PRIVATE_KEY == keyType) {
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(key));
        } else {
            return keyFactory.generatePublic(new X509EncodedKeySpec(key));
        }
    }

}
