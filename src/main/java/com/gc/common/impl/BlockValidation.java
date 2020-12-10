package com.gc.common.impl;

import com.gc.common.inter.DataValidation;

/**
 * @author join wick
 * @version 1.0.0
 * @className BlockValidation.java
 * @description block validation
 * @createDate 2020/12/10 8:53
 * @since 1.0.0
 */
public class BlockValidation<BlockRecord> implements DataValidation<BlockRecord> {
    /**
     * check block validation
     *
     * @param blockRecord BlockRecord
     * @return boolean
     */
    @Override
    public boolean checkValid(BlockRecord blockRecord) {
        return false;
    }
}
