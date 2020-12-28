package com.gc.cryptology;

import com.gc.utils.ConstantUtils;
import com.gc.utils.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/9 22:39
 * @since 1.0.0
 */
public class BaseCodecTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseCodecTest.class);
    private final BaseCodec base58Service = new BaseCodec();
    String initialData = "IF1801@1900.05:10:阿海:Buy:Open:@2017-12-03 15:24:59";
    String base58Data = "6ueqKDQ9byFCHYa1Pvs2PK9imt3v9iYAKFWcm6HdXjvx371M9urSZx9v2BqoiVLCzrTApTxWQQ";
    String base64Data = "SUYxODAxQDE5MDAuMDU6MTA66Zi/5rW3OkJ1eTpPcGVuOkAyMDE3LTEyLTAzIDE1OjI0OjU5";
    @Test
    public void base58Encode_ValidEntry() {
        String base58EncodeData = base58Service.base58Encode(initialData.getBytes(ConstantUtils.DEFAULT_CHARSET));
        assertEquals(base58Data, base58EncodeData);
    }

    @Test
    public void base58Decode_ValidEntry() {
        String base58DecodeData = StringUtils.convertByteArrayToString(base58Service.base58Decode(base58Data));
        LOGGER.debug("base58DecodeData = <{}>", base58DecodeData);
        assertEquals(initialData, base58DecodeData);
    }

    @Test
    public void base64Encode_ValidEntry() {
        String base64EncodeData = base58Service.base64Encode(initialData.getBytes(ConstantUtils.DEFAULT_CHARSET));
        assertEquals(base64Data, base64EncodeData);
    }

    @Test
    public void base64Decode() {
        String base64DecodeData = StringUtils.convertByteArrayToString(base58Service.base64Decode(base64Data));
        LOGGER.debug("base64DecodeData = <{}>", base64DecodeData);
        assertEquals(initialData, base64DecodeData);
    }
}