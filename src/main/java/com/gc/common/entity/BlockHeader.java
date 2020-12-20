package com.gc.common.entity;

/**
 * @author join wick
 * @version 1.0.0
 * @className BlockHeader.java
 * @description block header definition
 * @createDate 2020/12/10 8:55
 * @since 1.0.0
 */
public class BlockHeader {
    // block version, indicates the validation rules that this block follows
    private String version;
    // hash value of previous block, SHA256(SHA256(block header))
    private String preBlockHashValue;
    // hash value of merkle root, SHA256(SHA256(merkle root))
    private String merkleRootHashValue;
    // unix time stamp, must larger than the previous 11 blocks middle time, these blocks(time >= 2h) will be refused by other node
    private int timeStamp;
    //
    private int targetZeroCount;
    // used to generate a specific target value
    private long nonce;
}
