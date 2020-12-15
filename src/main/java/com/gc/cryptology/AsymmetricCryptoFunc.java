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
    private final BaseCodec baseFunc;
    public AsymmetricCryptoFunc(){
        baseFunc = new BaseCodec();
    }

    public static void main(String[] args) {
        AsymmetricCryptoFunc asymmetricCryptoFunc = new AsymmetricCryptoFunc();
        String publicKey = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEzkchRA2weIWmMpaRsCejeNgq3gyCpMQMPJtCXQS1JUZTmYEnOmLpzugp9Eoh0cJDCJhhwp2d9kZJAWqysTNYAg==";
        byte[] publicKeys = new BaseCodec().base64Decode(publicKey);
        String data = "123";
        byte[] dataBytes = StringUtils.convertStringToByteArray(data);
        byte[] bytes = asymmetricCryptoFunc.encrypt(dataBytes, dataBytes);
        LOGGER.debug("encrypt msg = <{}>", StringUtils.convertByteToHexString(bytes));

    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    /**
     * use public key to gen encrypted data
     *
     * @param data byte[]
     * @param key  byte[]
     * @return byte[]
     */
    @Override
    public byte[] encrypt(byte[] data, byte[] key) {
        if (CommonUtils.isEmpty(data)){
            throw new GCException(SystemErrorCode.DATA_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(key)){
            throw  new GCException(SystemErrorCode.PUBLIC_KEY_NOT_EXISTS);
        }
        byte[] encryptData = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance(EnumEntity.EllipticSchema.ECIES.getValue(), EnumEntity.SecurityProvider.BC.getValue());
            KeyFactory keyFactory = KeyFactory.getInstance(EnumEntity.EllipticSchema.EC.getValue(), EnumEntity.SecurityProvider.BC.getValue());
            // convert byte(public key) to BCECPublicKey
            BCECPublicKey bcecPublicKey = (BCECPublicKey)keyFactory.generatePublic(new X509EncodedKeySpec(key));
            cipher.init(Cipher.ENCRYPT_MODE, bcecPublicKey);
            encryptData = cipher.doFinal(data);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            LOGGER.error("encrypt data failed with reason <{}>", e.getMessage());
        }
        return encryptData;
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) {
        return new byte[0];
    }

    @Override
    public byte[] signature(byte[] data, byte[] key) {
        if (CommonUtils.isEmpty(data)){
            throw new GCException(SystemErrorCode.DATA_NOT_EXISTS);
        }
        if (CommonUtils.isEmpty(key)){
            throw  new GCException(SystemErrorCode.PRIVATE_KEY_NOT_EXISTS);
        }
        byte[] signatureBytes = new byte[0];
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(
                    EnumEntity.EllipticSchema.EC.getValue(), EnumEntity.SecurityProvider.BC.getValue());
            // convert byte(public key) to BCECPublicKey
            BCECPrivateKey bcecPrivateKey = (BCECPrivateKey)keyFactory.generatePublic(new X509EncodedKeySpec(key));
            Signature signature = Signature.getInstance(
                    EnumEntity.SignatureAlgorithm.SHA256withECDSA.getValue(), EnumEntity.SecurityProvider.BC.getValue());
            signature.initSign(bcecPrivateKey);
            signature.update(data);
            signatureBytes = signature.sign();
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {
            LOGGER.error("signature data failed with reason <{}>", e.getMessage());
        }

        return signatureBytes;
    }

    @Override
    public boolean validation(byte[] data, byte[] key) {
        return false;
    }
}
