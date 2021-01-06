package com.gc.common.entity;

/**
 * @author join wick
 * @version 1.0.0
 * @className BlockHeader.java
 * @description block header definition
 * @createDate 2020/12/10 8:55
 * @since 1.0.0
 */
public class BlockHeaderRecord {
    /**
     * block version, indicates the validation rules that this block follows[4 bytes]
     */
    private int version;
    /**
     * hash value of previous block, SHA256(SHA256(block header))[32 bytes]
     */
    private String preBlockHashValue;
    /**
     * hash value of merkle root, SHA256(SHA256(merkle root))[32 bytes]
     */
    private String merkleRootHashValue;
    /**
     * unix time stamp with milliseconds, must larger than the previous 11 blocks middle time,
     * these blocks(time >= 2h) will be refused by other node[8 bytes]
     */
    private long timeStamp;
    /**
     * difficulty of mining, encoding by special algorithm[4 bytes]
      */
    private int targetZeroCount;
    /**
     * used to generate a specific target value[4 bytes]
     */
    private int nonce;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPreBlockHashValue() {
        return preBlockHashValue;
    }

    public void setPreBlockHashValue(String preBlockHashValue) {
        this.preBlockHashValue = preBlockHashValue;
    }

    public String getMerkleRootHashValue() {
        return merkleRootHashValue;
    }

    public void setMerkleRootHashValue(String merkleRootHashValue) {
        this.merkleRootHashValue = merkleRootHashValue;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getTargetZeroCount() {
        return targetZeroCount;
    }

    public void setTargetZeroCount(int targetZeroCount) {
        this.targetZeroCount = targetZeroCount;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }


}
