package com.gc.common.entity;

import com.gc.common.inter.EnumInterface;

/**
 * @author join wick
 * @version 1.0.0
 * @className EnumEntity.java
 * @description enum entity
 * @createDate 2020/12/8 18:00
 * @since 1.0.0
 */
public class EnumEntity {
    // hash algorithm enum
    public enum HashAlgorithm implements EnumInterface {
        // sha 256
        SHA256("SHA-256", "SHA256"),
        // ripe md160
        RIPEMD160("RipeMD160", "RipeMD160");

        String value;
        String label;

        HashAlgorithm(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static HashAlgorithm getEnumByValue(String value) {
            for (HashAlgorithm hashAlgorithm : HashAlgorithm.values()) {
                if (hashAlgorithm.value.equals(value)) {
                    return hashAlgorithm;
                }
            }
            return null;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public String getLabel() {
            return this.label;
        }
    }

    public enum ECCAlgorithm implements EnumInterface {
        // secp256k1
        SECP256K1("secp256k1", "secp256k1"),

        // ECDSA
        ECDSA("ECDSA", "ECDSA"),
        ;

        String value;
        String label;

        ECCAlgorithm(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static ECCAlgorithm getEnumByValue(String value) {
            for (ECCAlgorithm eccAlgorithm : ECCAlgorithm.values()) {
                if (eccAlgorithm.value.equals(value)) {
                    return eccAlgorithm;
                }
            }
            return null;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public String getLabel() {
            return this.label;
        }
    }

    public enum SecurityProvider implements EnumInterface {
        // secp256k1
        BC("BC", "BC"),
        ;

        String value;
        String label;

        SecurityProvider(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static SecurityProvider getEnumByValue(String value) {
            for (SecurityProvider securityProvider : SecurityProvider.values()) {
                if (securityProvider.value.equals(value)) {
                    return securityProvider;
                }
            }
            return null;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public String getLabel() {
            return this.label;
        }
    }

    public enum EncryptAlgorithm implements EnumInterface{
        // AES
        AES("AES/CBC/PKCS5Padding", "AES/CBC/PKCS5Padding"),
        // RSA
        RSA("RSA/ECB/PKCS1Padding", "RSA/ECB/PKCS1Padding"),
        ;

        String value;
        String label;

        EncryptAlgorithm(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static EncryptAlgorithm getEnumByValue(String value) {
            for (EncryptAlgorithm encryptAlgorithm : EncryptAlgorithm.values()) {
                if (encryptAlgorithm.value.equals(value)) {
                    return encryptAlgorithm;
                }
            }
            return null;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public String getLabel() {
            return this.label;
        }
    }

    public enum EllipticSchema implements EnumInterface{
        // ECIES
        ECIES("ECIES", "ECIES"),
        ;

        String value;
        String label;

        EllipticSchema(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static EllipticSchema getEnumByValue(String value) {
            for (EllipticSchema ellipticSchema : EllipticSchema.values()) {
                if (ellipticSchema.value.equals(value)) {
                    return ellipticSchema;
                }
            }
            return null;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public String getLabel() {
            return this.label;
        }
    }



}
