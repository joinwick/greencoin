package com.gc.cryptology;

import com.gc.common.entity.EnumEntity;
import com.gc.utils.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/19 14:19
 * @since 1.0.0
 */
public class SymmetricCryptoTest {

    private final SymmetricCrypto symmetricCrypto = new SymmetricCrypto();
    private final BaseCodec baseCodec = new BaseCodec();
    String symmetricKey = "m1Rd9eCWP7PcRiZRQFgDGGG2CF/YpT5oIQQODlPYvv0=";
    String initialData = "上海123";
    byte[] initialBytes = StringUtils.convertStringToByteArray(initialData);
    byte[] symmetricKeyBytes = new BaseCodec().base64Decode(symmetricKey);



    @Test
    public void encrypt_ValidEntry() {
        byte[] encryptData = symmetricCrypto.encrypt(initialBytes, symmetricKeyBytes);
        assertTrue(encryptData.length > 0);
    }

    @Test
    public void decrypt_ValidEntry() {
        byte[] encryptData = symmetricCrypto.encrypt(initialBytes, symmetricKeyBytes);
        byte[] decryptData = symmetricCrypto.decrypt(encryptData, symmetricKeyBytes);
        String decryptInitialData = StringUtils.convertByteArrayToString(decryptData);
        assertEquals(initialData, decryptInitialData);
    }

    @Test
    public void encrypt_SelfAlgorithm__ValidEntry() {
        byte[] symmetricKeyBytes = baseCodec.base64Decode("V6Fe+5fm4Mg=");
        byte[] encryptData = symmetricCrypto.encrypt(initialBytes, symmetricKeyBytes, EnumEntity.SymmetricAlgorithm.DES);
        assertTrue(encryptData.length > 0);
    }

    @Test
    public void decrypt_SelfAlgorithm__ValidEntry() {
        byte[] symmetricKeyBytes = baseCodec.base64Decode("V6Fe+5fm4Mg=");
        byte[] encryptData = symmetricCrypto.encrypt(initialBytes, symmetricKeyBytes, EnumEntity.SymmetricAlgorithm.DES);
        byte[] decryptData = symmetricCrypto.decrypt(encryptData, symmetricKeyBytes, EnumEntity.SymmetricAlgorithm.DES);
        String decryptInitialData = StringUtils.convertByteArrayToString(decryptData);
        assertEquals(initialData, decryptInitialData);
    }
}