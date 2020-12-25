package com.gc.consensus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
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
@RunWith(PowerMockRunner.class)
@PrepareForTest({ProofOfWork.class})
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class ProofOfWorkTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProofOfWorkTest.class);
    @InjectMocks
    private ProofOfWork proofOfWork;

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

    @Test
    public void getTarget_ValidEntry() throws Exception {
        String exponentHexString = "19";
        String coefficientHexString = "03a30c";
        String expectedTargetString = "22829202948393929850749706076701368331072452018388575715328";
        String actualTargetString = Whitebox.invokeMethod(
                proofOfWork, "getTargetWithDecimalSystem", exponentHexString, coefficientHexString);
        assertEquals(expectedTargetString, actualTargetString);
    }
}