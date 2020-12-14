package com.gc.cryptology;

import com.gc.utils.ConstantUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/14 14:15
 * @since 1.0.0
 */
public class RSAFuncTest {
    private final RSAFunc rsaService = new RSAFunc();
    String initialData = "greenCoin,你好!";
    String privateKey = "91f44dde387a17341966a96efa9c4cd56ba27be4f14559c89f34078260ccefed";
    String publicKey = "bd620f9e73ab05e9315c9b65b47350de595dfd367e48b951952cc57f4679889fa6ebc124fa8e820e328be1b2cb3f3fc567f030aa7e88ce1469085c7a3e6a0e65";
    byte[] privateKeyBytes = new byte[0];
    byte[] publicKeyBytes = new byte[0];
    byte[] initialDataBytes = new byte[0];


    @Before
    public void setUp() {
        privateKeyBytes = privateKey.getBytes();
        publicKeyBytes = publicKey.getBytes();
        initialDataBytes = initialData.getBytes(ConstantUtils.DEFAULT_CHARSET);
        for (byte b : publicKeyBytes){
            System.out.print(b);
        }

    }

    @Test
    public void encryptByPublicKey_ValidEntry() {
        byte[] expectedData = "".getBytes(ConstantUtils.DEFAULT_CHARSET);
        assertEquals(expectedData, rsaService.encryptByPublicKey(initialDataBytes, publicKeyBytes));
    }

    @Test
    public void signatureByPrivateKey_ValidEntry() {
        byte[] expectedData = "".getBytes();
        assertEquals(expectedData, rsaService.signatureByPrivateKey(initialDataBytes, privateKeyBytes));
    }

    @Test
    public void signatureValidationByPublicKey_ValidEntry() {
        byte[] signatureData = "".getBytes();
        assertTrue(rsaService.signatureValidationByPublicKey(signatureData, publicKeyBytes));
    }

    @Test
    public void decryptByPrivateKey_ValidEntry() {
        byte[] expectedData = "".getBytes();
        assertEquals(expectedData, rsaService.decryptByPrivateKey(initialDataBytes, privateKeyBytes));
    }
}