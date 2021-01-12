package com.gc.common.impl;

import com.gc.common.entity.TransactionRecord;
import com.gc.common.inter.MerkleTreeService;

/**
 * @author join wick
 * @version 1.0.0
 * @description merkle tree implementation
 * @createDate 2021/1/12 19:49
 * @since 1.0.0
 */
public class MerkleTreeImpl implements MerkleTreeService<TransactionRecord> {
    @Override
    public String genRootHash() {
        return null;
    }

    @Override
    public boolean checkTransaction(TransactionRecord transactionRecord) {
        return false;
    }
}
