package com.gc.common.entity;

import java.util.List;

/**
 * @author join wick
 * @version 1.0.0
 * @className BlockRecord.java
 * @description block definition
 * @createDate 2020/12/10 8:54
 * @since 1.0.0
 */
public class BlockRecord {
    /**
     * block size(4 bytes)
     */
    private int blockSize;
    /**
     * block header(84 bytes)
     */
    private BlockHeaderRecord blockHeader;
    /**
     * transaction count in block(4 bytes)
     */
    private int transactionCount;
    /**
     * transaction list(variable bytes)
     */
    private List<TransactionRecord> transactionRecordList;

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public BlockHeaderRecord getBlockHeader() {
        return blockHeader;
    }

    public void setBlockHeader(BlockHeaderRecord blockHeader) {
        this.blockHeader = blockHeader;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public List<TransactionRecord> getTransactionRecordList() {
        return transactionRecordList;
    }

    public void setTransactionRecordList(List<TransactionRecord> transactionRecordList) {
        this.transactionRecordList = transactionRecordList;
    }

    @Override
    public String toString() {
        return "BlockRecord{" +
                "blockSize=" + blockSize +
                ", blockHeader=" + blockHeader +
                ", transactionCount=" + transactionCount +
                ", transactionRecordList=" + transactionRecordList +
                '}';
    }
}
