package com.gc.cryptology;

import com.gc.common.entity.ECCKeyPairRecord;
import org.junit.Test;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/11 18:04
 * @since 1.0.0
 */
public class ECCKeyPairFuncTest {
    private final ECCKeyPairFunc eccKeyPairService = new ECCKeyPairFunc();
    @Test
    public void createKeyPair() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        ECCKeyPairRecord eccKeyPairRecord = eccKeyPairService.createKeyPair();
        assertEquals(64, eccKeyPairRecord.getPrivateKey().length());
        assertEquals(128, eccKeyPairRecord.getPublicKey().length());
    }
}