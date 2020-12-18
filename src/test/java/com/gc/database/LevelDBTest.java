package com.gc.database;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/16 9:05
 * @since 1.0.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LevelDBTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LevelDBTest.class);
    private final LevelDB levelDB = LevelDB.getInstance();
    String key = "上海";
    String value = "你好!";

    @Before
    public void setUp() {
        // level db init
        levelDB.initDB();
    }

    @After
    public void tearDown() {
        // release db resource
        levelDB.closeDB();
    }

    @Test
    public void test001_insert_ValidEntry() {
        boolean insertRes = levelDB.insert(key, value);
        assertTrue(insertRes);
    }

    @Test
    public void test002_query() {
        String queryRes = levelDB.query(key);
        assertEquals(value, queryRes);
    }

    @Test
    public void test003_getAllKeys() {
        int count = levelDB.getAllKeys().size();
        LOGGER.debug("key size <{}>", count);
        assertTrue(count > 0);
    }

    @Test
    public void test004_delete() {
        boolean deleteRes = levelDB.delete(key);
        assertTrue(deleteRes);
    }
}