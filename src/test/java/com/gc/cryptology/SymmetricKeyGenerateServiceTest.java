package com.gc.cryptology;

import com.gc.common.entity.EnumEntity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/18 22:46
 * @since 1.0.0
 */
public class SymmetricKeyGenerateServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SymmetricKeyGenerateServiceTest.class);

    private final SymmetricKeyGenerateService symmetricKeyGenerateService = new SymmetricKeyGenerateService();
    EnumEntity.SymmetricAlgorithm symmetricAlgorithm = EnumEntity.SymmetricAlgorithm.AES;
    EnumEntity.KeyLengthType keyLengthType = EnumEntity.KeyLengthType.KEY_256;

    @Test
    public void createKey_AES_ValidEntry() throws NoSuchAlgorithmException {
        String symmetricKey = symmetricKeyGenerateService.createKey(symmetricAlgorithm, keyLengthType);
        LOGGER.debug("symmetric key <{}>", symmetricKey);
        assertEquals(44, symmetricKey.length());
    }
}