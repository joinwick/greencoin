package com.gc.cryptology;

import com.gc.common.entity.ECCKeyPairRecord;
import com.gc.common.entity.EnumEntity;
import com.gc.database.LevelDB;
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
public class ECCKeyPairGenerateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ECCKeyPairGenerateService.class);
    // base encoding & decoding
    private final BaseCodec baseFunc;

    public ECCKeyPairGenerateService() {
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

    /**
     * create & store keys
     *
     * @param keyPair KeyPair
     * @return ECCKeyPairRecord
     */
    private ECCKeyPairRecord createKeyPairWithBase64(KeyPair keyPair) {
        // get private key
        BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) keyPair.getPrivate();
        // get public key
        BCECPublicKey bcecPublicKey = (BCECPublicKey) keyPair.getPublic();
        // get private key(String), get DER with format(PKCS #8) by privateKey.getEncoded()
        String privateKey = baseFunc.base64Encode(bcecPrivateKey.getEncoded());
        // get public key(String), get DER with format(X.509) by publicKey.getEncoded()
        String publicKey = baseFunc.base64Encode(bcecPublicKey.getEncoded());
        if (!saveKeyIntoDB(privateKey, publicKey)) {
            LOGGER.error("keys store into db error");
            return null;
        }
        return new ECCKeyPairRecord(privateKey, publicKey);
    }

    /**
     * store keys into db
     *
     * @param privateKey String
     * @param publicKey  String
     * @return boolean
     */
    private boolean saveKeyIntoDB(String privateKey, String publicKey) {
        if (CommonUtils.isEmpty(privateKey) || CommonUtils.isEmpty(publicKey)) {
            LOGGER.error("key is empty");
            return false;
        }
        // get leveldb instance
        LevelDB levelDB = LevelDB.getInstance();
        levelDB.initDB();
        boolean privateKeyInsertRes = levelDB.insert(ConstantUtils.DEFAULT_PRIVATE_KEY, privateKey);
        boolean publicKeyInsertRes = levelDB.insert(ConstantUtils.DEFAULT_PUBLIC_KEY, publicKey);
        levelDB.closeDB();
        return privateKeyInsertRes && publicKeyInsertRes;
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
