package com.gc.common.entity;

/**
 * @author join wick
 * @version 1.0.0
 * @className ECCKeyPairRecord.java
 * @description ecc key pair record
 * @createDate 2020/12/11 17:59
 * @since 1.0.0
 */
public class ECCKeyPairRecord {
    private final String privateKey;
    private final String publicKey;

    public ECCKeyPairRecord(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
