package com.gc.common.entity;

/**
 * @author join wick
 * @version 1.0.0
 * @className BlockRecord.java
 * @description block definition
 * @createDate 2020/12/10 8:54
 * @since 1.0.0
 */
public class BlockRecord {
    private BlockHeader blockHeader;
    private BlockBody blockBody;

    public BlockHeader getBlockHeader() {
        return blockHeader;
    }

    public void setBlockHeader(BlockHeader blockHeader) {
        this.blockHeader = blockHeader;
    }

    public BlockBody getBlockBody() {
        return blockBody;
    }

    public void setBlockBody(BlockBody blockBody) {
        this.blockBody = blockBody;
    }
}
