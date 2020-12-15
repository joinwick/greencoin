package com.gc.cryptology;

import com.gc.common.entity.ECCKeyPairRecord;
import com.gc.common.entity.EnumEntity;
import com.gc.utils.CommonUtils;
import com.gc.utils.ConstantUtils;
import com.gc.utils.StringUtils;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

/**
 * @author join wick
 * @version 1.0.0
 * @description ecc key pair generation
 * @createDate 2020/12/11 13:52
 * @since 1.0.0
 */
public class ECCKeyPairFunc {
    private static final Logger LOGGER = LoggerFactory.getLogger(ECCKeyPairFunc.class);
    private final BaseCodec baseFunc;
    public ECCKeyPairFunc(){
        baseFunc = new BaseCodec();
    }

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
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EnumEntity.EllipticSchema.ECDSA.getValue(), EnumEntity.SecurityProvider.BC.getValue());
        // generate the corresponding (precomputed) elliptic curve domain parameters with a standard (or predefined) name
        ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec(EnumEntity.ECCAlgorithm.SECP256K1.getValue());
        // initializes the key pair generator with the given parameter set and source of randomness(not need random seed).
        keyPairGenerator.initialize(ecGenParameterSpec, new SecureRandom());
        // generate key pair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // convert key pair to ECCKeyPair
        return createKeyPairWithBase64(keyPair);
    }

    public ECCKeyPairRecord createKeyPairWithBase64(KeyPair keyPair){
        // get private key
        BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) keyPair.getPrivate();
        // get public key
        BCECPublicKey bcecPublicKey = (BCECPublicKey) keyPair.getPublic();
        return new ECCKeyPairRecord(
                baseFunc.base64Encode(bcecPrivateKey.getEncoded()),
                baseFunc.base64Encode(bcecPublicKey.getEncoded()));
    }

    /**
     * generate key pair by using ecc
     *
     * @param keyPair KeyPair
     * @return ECCKeyPairRecord
     */
    private ECCKeyPairRecord createKeyPair(KeyPair keyPair) {
        // get keys from KeyPair
        BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) keyPair.getPrivate();
        BCECPublicKey bcecPublicKey = (BCECPublicKey) keyPair.getPublic();
        BigInteger privateKeyValue = bcecPrivateKey.getD();
        byte[] publicKeyBytes = bcecPublicKey.getQ().getEncoded(false);
        BigInteger publicKeyValue = new BigInteger(1, Arrays.copyOfRange(publicKeyBytes, 1, publicKeyBytes.length));
        // fix keys with default length
        String privateKey = fixLength(privateKeyValue.toString(16), ConstantUtils.DEFAULT_PRIVATE_KEY_LENGTH);
        String publicKey = fixLength(publicKeyValue.toString(16), ConstantUtils.DEFAULT_PUBLIC_KEY_LENGTH);
        // get ECCKeyPairRecord
        return new ECCKeyPairRecord(privateKey, publicKey);
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
            LOGGER.debug("data is empty");
            return data;
        }
        while (data.length() < length) {
            data = StringUtils.appendByCondition(data, ConstantUtils.DEFAULT_GC_KEY_PREFIX, true);
        }
        return data;
    }
}
