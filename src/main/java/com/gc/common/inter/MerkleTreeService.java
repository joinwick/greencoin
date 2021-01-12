package com.gc.common.inter;

/**
 * @author join wick
 * @version 1.0.0
 * @description merkle tree interface
 * @createDate 2021/1/12 19:44
 * @since 1.0.0
 */
public interface MerkleTreeService<T> {
    /**
     * generate root hash value with hex format
     *
     * @return String
     */
    String genRootHash();

    /**
     * check whether the transaction is valid
     *
     * @param t T
     * @return boolean
     */
    boolean checkTransaction(T t);

}
