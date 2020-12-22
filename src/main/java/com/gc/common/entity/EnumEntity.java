package com.gc.common.entity;

import com.gc.common.inter.EnumInterface;

/**
 * @author join wick
 * @version 1.0.0
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
        // BC
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

    public enum EllipticSchema implements EnumInterface {
        // ECIES
        ECIES("ECIES", "ECIES"),
        // EC
        EC("EC", "EC"),
        // ECDSA
        ECDSA("ECDSA", "ECDSA"),
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

    public enum BaseEncodingAlgorithm implements EnumInterface {
        // base58
        BASE58("BASE58", "BASE58"),
        // base64
        BASE64("BASE64", "BASE64"),
        ;

        String value;
        String label;

        BaseEncodingAlgorithm(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static BaseEncodingAlgorithm getEnumByValue(String value) {
            for (BaseEncodingAlgorithm baseEncodingAlgorithm : BaseEncodingAlgorithm.values()) {
                if (baseEncodingAlgorithm.value.equals(value)) {
                    return baseEncodingAlgorithm;
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

    public enum SignatureAlgorithm implements EnumInterface {
        // SHA1WithRSA
        SHA1WithRSA("SHA1WithRSA", "SHA1WithRSA"),
        // SHA256withECDSA
        SHA256withECDSA("SHA256withECDSA", "SHA256withECDSA"),
        ;

        String value;
        String label;

        SignatureAlgorithm(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static SignatureAlgorithm getEnumByValue(String value) {
            for (SignatureAlgorithm signatureAlgorithm : SignatureAlgorithm.values()) {
                if (signatureAlgorithm.value.equals(value)) {
                    return signatureAlgorithm;
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

    public enum ConfigPathName implements EnumInterface {
        // GC_COMMON_CONFIG
        GC_COMMON_CONFIG("config.path", "常规配置文件"),
        ;

        String value;
        String label;

        ConfigPathName(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static ConfigPathName getEnumByValue(String value) {
            for (ConfigPathName configPathName : ConfigPathName.values()) {
                if (configPathName.value.equals(value)) {
                    return configPathName;
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

    public enum SymmetricAlgorithm implements EnumInterface {
        // des
        DES("DES", "DES"),
        // 3des
        DESEDE("DESede", "DESede"),
        // aes
        AES("AES", "AES"),
        // PBEWITHMD5andDES
        PBEWITHMD5andDES("PBEWITHMD5andDES", "PBEWITHMD5andDES"),
        // PBEWITHMD5andTripleDES
        PBEWITHMD5andTripleDES("PBEWITHMD5andTripleDES", "PBEWITHMD5andTripleDES"),
        // PBEWITHSHA1AndDESede
        PBEWITHSHA1AndDESede("PBEWITHSHA1AndDESede", "PBEWITHSHA1AndDESede"),
        // PBEWITHSHA1AndRC2_40
        PBEWITHSHA1AndRC2_40("PBEWITHSHA1AndRC2_40", "PBEWITHSHA1AndRC2_40"),
        ;

        String value;
        String label;

        SymmetricAlgorithm(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static SymmetricAlgorithm getEnumByValue(String value) {
            for (SymmetricAlgorithm symmetricAlgorithm : SymmetricAlgorithm.values()) {
                if (symmetricAlgorithm.value.equals(value)) {
                    return symmetricAlgorithm;
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

    public enum KeyType implements EnumInterface {
        // private key
        PRIVATE_KEY("PrivateKey", "PrivateKey"),
        // public key
        PUBLIC_KEY("PublicKey", "PublicKey"),
        // symmetric key
        SYMMETRIC_KEY("SymmetricKey", "SymmetricKey"),
        ;

        String value;
        String label;

        KeyType(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static KeyType getEnumByValue(String value) {
            for (KeyType keyType : KeyType.values()) {
                if (keyType.value.equals(value)) {
                    return keyType;
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

    public enum KeyLengthType implements EnumInterface {
        // key length 56
        KEY_56("56", "56"),
        // key length 112
        KEY_112("112", "112"),
        // key length 128
        KEY_128("128", "128"),
        // key length 168
        KEY_168("168", "168"),
        // key length 128
        KEY_192("192", "192"),
        // key length 128
        KEY_256("256", "256"),
        ;

        String value;
        String label;

        KeyLengthType(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static KeyLengthType getEnumByValue(String value) {
            for (KeyLengthType keyLengthType : KeyLengthType.values()) {
                if (keyLengthType.value.equals(value)) {
                    return keyLengthType;
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

    public enum ConnectionStatus implements EnumInterface {
        // good connection
        GOOD("1", "good connection"),
        // general connection
        GENERAL("2", "general connection"),
        // bad connection
        BAD("3", "bad connection"),
        ;

        String value;
        String label;

        ConnectionStatus(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static ConnectionStatus getEnumByValue(String value) {
            for (ConnectionStatus connectionStatus : ConnectionStatus.values()) {
                if (connectionStatus.value.equals(value)) {
                    return connectionStatus;
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

    public enum NodeType implements EnumInterface {
        // light node
        LIGHT("1", "light node"),
        // general node
        GENERAL("2", "general node"),
        // mining node
        MINING("3", "mining node"),
        // full node
        FULL("4", "full node"),
        ;

        String value;
        String label;

        NodeType(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public static NodeType getEnumByValue(String value) {
            for (NodeType nodeType : NodeType.values()) {
                if (nodeType.value.equals(value)) {
                    return nodeType;
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
