package com.gc.common.inter;

/**
 * @author join wick
 * @version 1.0.0
 * @description signature & validation interface
 * @createDate 2020/12/14 16:03
 * @since 1.0.0
 */
public interface SignatureInterface extends BaseCryptologyInterface {
    // signature by private key
    byte[] signature(byte[] data, byte[] key);

    // validate signature by public key
    boolean validation(byte[] data, byte[] key);
}
