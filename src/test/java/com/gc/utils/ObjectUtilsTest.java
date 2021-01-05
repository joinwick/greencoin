package com.gc.utils;

import com.gc.common.entity.BlockHeaderRecord;
import com.gc.common.entity.BlockRecord;
import com.gc.common.entity.TransactionRecord;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/22 16:17
 * @since 1.0.0
 */
public class ObjectUtilsTest {

    @Test
    public void getObjectSize_BasicType_Int() {
        int a = 1;
        assertEquals(16, ObjectUtils.getObjectSize(a));
    }

    @Test
    public void getObjectHeapSize_BasicType_Integer() {
        Integer a = 1;
        assertEquals(16, ObjectUtils.getObjectSize(a));
    }

    @Test
    public void getObjectHeapSize_BasicType_Boolean() {
        boolean a = true;
        assertEquals(16, ObjectUtils.getObjectSize(a));
    }

    @Test
    public void getObjectHeapSize_BasicType_Byte() {
        byte a = 1;
        assertEquals(16, ObjectUtils.getObjectSize(a));
    }

    @Test
    public void getObjectHeapSize_BasicType_Short() {
        short a = 1;
        assertEquals(16, ObjectUtils.getObjectSize(a));
    }

    @Test
    public void getObjectHeapSize_BasicType_Char() {
        char a = '1';
        assertEquals(16, ObjectUtils.getObjectSize(a));
    }

    @Test
    public void getObjectHeapSize_BasicType_Int() {
        int a = 1;
        assertEquals(16, ObjectUtils.getObjectSize(a));
    }

    @Test
    public void getObjectHeapSize_BasicType_Long() {
        long a = 1L;
        assertEquals(24, ObjectUtils.getObjectSize(a));
    }

    @Test
    public void getObjectHeapSize_BasicType_Float() {
        float a = 1.0f;
        assertEquals(16, ObjectUtils.getObjectSize(a));
    }

    @Test
    public void getObjectHeapSize_BasicType_Double() {
        double a = 1.0;
        assertEquals(24, ObjectUtils.getObjectSize(a));
    }

    @Test
    public void getObjectHeapSize_BasicType_BaseObject() {
        BlockHeaderRecord blockHeader = new BlockHeaderRecord();
        blockHeader.setVersion(1);
        blockHeader.setTimeStamp(10L);
        blockHeader.setNonce(1);
        blockHeader.setPreBlockHashValue("123123123123123");
        blockHeader.setMerkleRootHashValue("123123123123123");
        assertEquals(40, ObjectUtils.getObjectSize(blockHeader));
    }

    @Test
    public void getObjectHeapSize_BasicType_ComplexObject() {
        BlockHeaderRecord blockHeader = new BlockHeaderRecord();
        blockHeader.setVersion(1);
        blockHeader.setTimeStamp(10L);
        blockHeader.setNonce(1);
        blockHeader.setPreBlockHashValue("123123123123123");
        blockHeader.setMerkleRootHashValue("123123123123123");

        BlockRecord blockRecord = new BlockRecord();
        blockRecord.setBlockHeader(blockHeader);
        blockRecord.setBlockSize(1357503);
        blockRecord.setTransactionCount(2079);
        List<TransactionRecord> transactionRecordList = new ArrayList<>();
        TransactionRecord transactionRecord = new TransactionRecord();
        transactionRecord.setTransactionID("123123123123123");
        transactionRecordList.add(transactionRecord);
        blockRecord.setTransactionRecordList(transactionRecordList);

        assertEquals(32, ObjectUtils.getObjectSize(blockRecord));
    }

    @Test
    public void getObjectTotalSize_BasicType_BaseObject() {
        BlockHeaderRecord blockHeader = new BlockHeaderRecord();
        blockHeader.setVersion(1);
        blockHeader.setTimeStamp(10L);
        blockHeader.setNonce(1);
        blockHeader.setPreBlockHashValue("123123123123123");
        blockHeader.setMerkleRootHashValue("123123123123123");
        assertEquals(112, ObjectUtils.getObjectTotalSize(blockHeader));
    }

    @Test
    public void getObjectTotalSize_BasicType_ComplexObject() {
        BlockHeaderRecord blockHeader = new BlockHeaderRecord();
        blockHeader.setVersion(1);
        blockHeader.setTimeStamp(10L);
        blockHeader.setNonce(1);
        blockHeader.setPreBlockHashValue("123123123123123");
        blockHeader.setMerkleRootHashValue("123123123123123");

        BlockRecord blockRecord = new BlockRecord();
        blockRecord.setBlockHeader(blockHeader);
        blockRecord.setBlockSize(1357503);
        blockRecord.setTransactionCount(2079);
        List<TransactionRecord> transactionRecordList = new ArrayList<>();
        TransactionRecord transactionRecord = new TransactionRecord();
        transactionRecord.setTransactionID("123123123123123");
        transactionRecordList.add(transactionRecord);
        blockRecord.setTransactionRecordList(transactionRecordList);

        assertEquals(240, ObjectUtils.getObjectTotalSize(blockRecord));
    }

    @Test
    public void getObjectSize_BasicType_BaseObject() {
        BlockHeaderRecord blockHeader = new BlockHeaderRecord();
        blockHeader.setVersion(1);
        blockHeader.setTimeStamp(10L);
        blockHeader.setNonce(1);
        blockHeader.setPreBlockHashValue("123123123123123");
        blockHeader.setMerkleRootHashValue("123123123123123");
        assertEquals(40, ObjectUtils.getObjectSize(blockHeader));
    }


}