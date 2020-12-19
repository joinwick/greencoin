package com.gc.cryptology;

import com.gc.utils.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/19 14:22
 * @since 1.0.0
 */
public class AsymmetricCryptoTest {

    private final AsymmetricCrypto asymmetricCryptoFunc = new AsymmetricCrypto();
    private final BaseCodec baseCodec = new BaseCodec();
    String privateKey = "MIGNAgEAMBAGByqGSM49AgEGBSuBBAAKBHYwdAIBAQQgagwhvyMM4c0a0wMaEO1IY0GKAxUwOiKzvYs2gqtQAqugBwYFK4EEAAqhRANCAAQs618JBzf+V816uhpcNLDSJi9XzEZ9qkSotMD+5UCTeiQn02pZb7vSSssWBPgWiYczNniCmzdrGjkZ5ftAQtzO";
    String publicKey = "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAELOtfCQc3/lfNeroaXDSw0iYvV8xGfapEqLTA/uVAk3okJ9NqWW+70krLFgT4FomHMzZ4gps3axo5GeX7QELczg==";
    // get decode keys
    byte[] privateKeys = baseCodec.base64Decode(privateKey);
    byte[] publicKeys = baseCodec.base64Decode(publicKey);

    String data = "123456上海你好";
    byte[] dataBytes = StringUtils.convertStringToByteArray(data);

    @Test
    public void encrypt_ValidEntry() {
        byte[] encryptBytes = asymmetricCryptoFunc.encrypt(dataBytes, publicKeys);
        assertTrue(encryptBytes.length > 0);
    }

    @Test
    public void decrypt_ValidEntry() {
        byte[] encryptBytes = asymmetricCryptoFunc.encrypt(dataBytes, publicKeys);
        byte[] decryptBytes = asymmetricCryptoFunc.decrypt(encryptBytes, privateKeys);
        String decryptData = StringUtils.convertByteArrayToString(decryptBytes);
        assertEquals(data, decryptData);
    }

    @Test
    public void signature_ValidEntry() {
        byte[] signature = asymmetricCryptoFunc.signature(dataBytes, privateKeys);
        assertTrue(signature.length > 0);
    }

    @Test
    public void validation_ValidEntry() {
        byte[] signature = asymmetricCryptoFunc.signature(dataBytes, privateKeys);
        boolean verifyRes = asymmetricCryptoFunc.validation(dataBytes, signature, publicKeys);
        assertTrue(verifyRes);
    }
}