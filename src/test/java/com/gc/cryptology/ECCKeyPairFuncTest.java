package com.gc.cryptology;

import com.gc.common.entity.ECCKeyPairRecord;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(ECCKeyPairFuncTest.class);
    private final ECCKeyPairFunc eccKeyPairService = new ECCKeyPairFunc();
    @Test
    public void createKeyPair() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        ECCKeyPairRecord eccKeyPairRecord = eccKeyPairService.createKeyPair();
        LOGGER.debug("private key =<{}>", eccKeyPairRecord.getPrivateKey());
        LOGGER.debug("public  key =<{}>", eccKeyPairRecord.getPublicKey());
        assertEquals(192, eccKeyPairRecord.getPrivateKey().length());
        assertEquals(120, eccKeyPairRecord.getPublicKey().length());
    }
}