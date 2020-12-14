package com.gc.cryptology;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @description rsa service
 * @createDate 2020/12/14 13:57
 * @since 1.0.0
 */
public class RSAFunc {
    private static final Logger LOGGER = LoggerFactory.getLogger(RSAFunc.class);


    /**
     * encrypt by public key
     *
     * @param data      byte[]
     * @param publicKey byte[]
     * @return byte[]
     */
    public byte[] encryptByPublicKey(byte[] data, byte[] publicKey) {
        byte[] encryptData = new byte[1];


        return encryptData;
    }

    /**
     * gen signature with private key
     *
     * @param data       byte[]
     * @param privateKey byte[]
     * @return byte[]
     */
    public byte[] signatureByPrivateKey(byte[] data, byte[] privateKey) {
        byte[] encryptData = new byte[1];


        return encryptData;
    }

    /**
     * validate signature with public key
     *
     * @param data      byte[]
     * @param publicKey byte[]
     * @return boolean
     */
    public boolean signatureValidationByPublicKey(byte[] data, byte[] publicKey) {
        return false;
    }

    public byte[] decryptByPrivateKey(byte[] data, byte[] privateKey) {
        byte[] decryptData = new byte[1];

        return decryptData;
    }
}
