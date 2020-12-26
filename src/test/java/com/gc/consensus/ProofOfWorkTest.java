package com.gc.consensus;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/24 15:32
 * @since 1.0.0
 */
public class ProofOfWorkTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProofOfWorkTest.class);

    private ProofOfWork proofOfWork = new ProofOfWork();

    String binaryHashString = "000000000000000000000000000000000000000100000001001100101100000000000000000000000000" +
            "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
            "0000000000000000000000000000000000000000000000000000000000000000000000";

    String binaryTargetString = "00000000000000000000000000000000000000010000000";
    int zeroCount = 39;

    @Test
    public void checkBlockValid_ValidEntry() {
        assertTrue(proofOfWork.checkBlockValid(binaryTargetString, binaryHashString));
    }

    @Test
    public void testCheckBlockValid_ValidEntry() {
        assertTrue(proofOfWork.checkBlockValid(zeroCount, binaryHashString));
    }

}