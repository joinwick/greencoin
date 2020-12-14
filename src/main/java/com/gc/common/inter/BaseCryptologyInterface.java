package com.gc.common.inter;

/**
 * @author join wick
 * @version 1.0.0
 * @description base cryptology interface
 * @createDate 2020/12/14 15:57
 * @since 1.0.0
 */
public interface BaseCryptologyInterface {
    // encrypt data by key, can be implied to Symmetric/Asymmetric
    byte[] encrypt(byte[] data, byte[] key);

    // decrypt data by key, can be implied to Symmetric/Asymmetric
    byte[] decrypt(byte[] data, byte[] key);
}
