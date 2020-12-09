package com.gc.cryptology;

import com.gc.common.entity.EnumEntity;
import com.gc.utils.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @className HashServiceTest.java
 * @description
 * @createDate 2020/12/9 15:58
 * @since 1.0.0
 */
public class HashServiceTest {
    private final HashService hashService = new HashService();
    String initialData = "上海";

    @Test
    public void getHexString_EmptyData() {
        initialData = "";
        String res = hashService.getHexString(initialData, EnumEntity.HashAlgorithm.SHA256);
        assertTrue(StringUtils.isEmpty(res));
    }

    @Test
    public void getHexString_ValidData(){
        String expectedRes = "4906a49adc5c472b34483976104ec1d76992745367cc22d5fbc2a4db8e8e1ed7";
        String res = hashService.getHexString(initialData, EnumEntity.HashAlgorithm.SHA256);
        assertEquals(expectedRes, res);
    }
}