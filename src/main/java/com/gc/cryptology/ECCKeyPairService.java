package com.gc.cryptology;

import com.gc.common.entity.ECCKeyPairRecord;
import com.gc.common.entity.EnumEntity;
import com.gc.utils.CommonUtils;
import com.gc.utils.ConstantUtils;
import com.gc.utils.StringUtils;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

/**
 * @author join wick
 * @version 1.0.0
 * @className ECCKeyPairService.java
 * @description ecc key pair generation
 * @createDate 2020/12/11 13:52
 * @since 1.0.0
 */
public class ECCKeyPairService {
    /**
     * generate ecc key pair
     *
     * @return ECCKeyPairRecord
     * @throws NoSuchProviderException            NoSuchProviderException
     * @throws NoSuchAlgorithmException           NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException InvalidAlgorithmParameterException
     */
    public ECCKeyPairRecord createKeyPair() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        // add bouncy castle as key pair generator
        Security.addProvider(new BouncyCastleProvider());
        // generator key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EnumEntity.ECCAlgorithm.ECDSA.getValue(), EnumEntity.SecurityProvider.BC.getValue());
        // generate the corresponding (precomputed) elliptic curve domain parameters with a standard (or predefined) name
        ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec(EnumEntity.ECCAlgorithm.SECP256K1.getValue());
        // initializes the key pair generator with the given parameter set and source of randomness.
        keyPairGenerator.initialize(ecGenParameterSpec, new SecureRandom());
        // generate key pair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // convert key pair to ECCKeyPair
        return createKeyPair(keyPair);
    }

    /**
     * generate key pair by using ecc
     *
     * @param keyPair KeyPair
     * @return ECCKeyPairRecord
     */
    private ECCKeyPairRecord createKeyPair(KeyPair keyPair) {
        BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) keyPair.getPrivate();
        BCECPublicKey bcecPublicKey = (BCECPublicKey) keyPair.getPublic();
        BigInteger privateKeyValue = bcecPrivateKey.getD();
        byte[] publicKeyBytes = bcecPublicKey.getQ().getEncoded(false);
        BigInteger publicKeyValue = new BigInteger(1, Arrays.copyOfRange(publicKeyBytes, 1, publicKeyBytes.length));
        return new ECCKeyPairRecord(fixLength(privateKeyValue.toString(16), 64), fixLength(publicKeyValue.toString(16), 128));
    }

    /**
     * fix with default value(0) when not enough data
     *
     * @param data   String
     * @param length int
     * @return String
     */
    private String fixLength(String data, int length) {
        if (CommonUtils.isEmpty(data)) {
            return data;
        }
        while (data.length() < length) {
            data = StringUtils.appendByCondition(data, ConstantUtils.GC_KEY_PREFIX,true);
        }
        return data;
    }
}
