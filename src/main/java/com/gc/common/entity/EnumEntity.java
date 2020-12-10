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
        HashAlgorithm(String value, String label){
            this.value = value;
            this.label = label;
        }
        public static HashAlgorithm getEnumByValue(String value){
            for (HashAlgorithm hashAlgorithm : HashAlgorithm.values()){
                if (hashAlgorithm.value.equals(value)){
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

}
