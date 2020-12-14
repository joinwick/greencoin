package com.gc.cryptology;

import com.gc.common.entity.EnumEntity;
import com.gc.common.inter.SignatureInterface;
import com.gc.exception.GCException;
import com.gc.exception.SystemCode;
import com.gc.exception.SystemErrorCode;
import com.gc.utils.CommonUtils;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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
        try {
            Cipher cipher = Cipher.getInstance(EnumEntity.EllipticSchema.ECIES.getValue(), EnumEntity.SecurityProvider.BC.getValue());

            KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
            // convert byte(public key) to BCECPublicKey
            BCECPublicKey bcecPublicKey = (BCECPublicKey)keyFactory.generatePublic(new X509EncodedKeySpec(key));
            cipher.init(Cipher.ENCRYPT_MODE, bcecPublicKey);

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) {
        return new byte[0];
    }

    @Override
    public byte[] signature(byte[] data, byte[] key) {
        return new byte[0];
    }

    @Override
    public boolean validation(byte[] data, byte[] key) {
        return false;
    }
}
