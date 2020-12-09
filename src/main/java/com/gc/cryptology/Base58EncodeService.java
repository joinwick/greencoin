package com.gc.cryptology;

import com.gc.utils.ConstantUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * @author join wick
 * @version 1.0.0
 * @className Base58EncodeService.java
 * @description base58 encode service
 * @createDate 2020/12/8 21:50
 * @since 1.0.0
 */
public class Base58EncodeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Base58EncodeService.class);

    public static void main(String[] args) {
        String initialMessage = "IF1801@1900.05:10:阿海:Buy:Open:@2017-12-03 15:24:59";
        byte[] encodeByte = initialMessage.getBytes(StandardCharsets.UTF_8);
        String base58Msg = new Base58EncodeService().base58Encode(encodeByte);
        LOGGER.debug("base58 res: <{}>", base58Msg);
    }

    /**
     * base58 encode
     *
     * @param data byte[]
     * @return String
     */
    public String base58Encode(byte[] data) {
        if (data == null || data.length == 0) {
            return "";
        }
        BigInteger bigInteger = new BigInteger(1, data);
        StringBuilder stringBuilder = new StringBuilder();
        while (bigInteger.compareTo(ConstantUtils.BASE58_LENGTH) > 0) {
            BigInteger[] tempBigInteger = bigInteger.divideAndRemainder(ConstantUtils.BASE58_LENGTH);
            stringBuilder.insert(0, ConstantUtils.getBase58Bytes()[tempBigInteger[1].intValue()]);
            bigInteger = tempBigInteger[0];
        }
        stringBuilder.insert(0, ConstantUtils.getBase58Bytes()[bigInteger.intValue()]);
        for (byte b : data) {
            if (b == 0) {
                stringBuilder.insert(0, ConstantUtils.getBase58Bytes()[0]);
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }

}
