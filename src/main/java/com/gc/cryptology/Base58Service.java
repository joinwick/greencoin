package com.gc.cryptology;

import com.gc.utils.CommonUtils;
import com.gc.utils.ConstantUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * @author join wick
 * @version 1.0.0
 * @className Base58Service.java
 * @description base58 encode & decode service
 * @createDate 2020/12/8 21:50
 * @since 1.0.0
 */
public class Base58Service {
    private static final Logger LOGGER = LoggerFactory.getLogger(Base58Service.class);

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
            stringBuilder.insert(0, ConstantUtils.BASE58_CHARACTERS.charAt(tempBigInteger[1].intValue()));
            bigInteger = tempBigInteger[0];
        }
        stringBuilder.insert(0, ConstantUtils.BASE58_CHARACTERS.charAt(bigInteger.intValue()));
        for (byte b : data) {
            if (b == 0) {
                stringBuilder.insert(0, ConstantUtils.BASE58_CHARACTERS.charAt(0));
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }

    /**
     * base58 decode
     *
     * @param data String
     * @return byte[]
     */
    public byte[] base58Decode(String data) {
        byte[] decodeByte;
        if (CommonUtils.isEmpty(data)) {
            LOGGER.debug("empty data");
            return new byte[0];
        }
        BigInteger bigInteger = BigInteger.ZERO;
        int dataLength = data.length();
        for (int i = dataLength - 1; i >= 0; i--) {
            int base58Index = ConstantUtils.BASE58_CHARACTERS.indexOf(data.charAt(i));
            if (base58Index == -1) {
                LOGGER.error("not base58 encoded data");
                throw new IllegalArgumentException("invalid base58 data");
            }
            bigInteger = bigInteger.add(
                    ConstantUtils.BASE58_LENGTH.pow(dataLength - 1 - i).
                            multiply(BigInteger.valueOf(base58Index)));
        }
        byte[] bytes = bigInteger.toByteArray();
        boolean zeroOneFlag = bytes.length > 1 && bytes[0] == 0 && bytes[1] < 0;
        int fillZeroCount = 0;
        while (fillZeroCount < dataLength && data.charAt(fillZeroCount) == ConstantUtils.BASE58_CHARACTERS.charAt(0)){
            fillZeroCount ++;
        }
        decodeByte = new byte[bytes.length - (zeroOneFlag ? 1 : 0) + fillZeroCount];
        System.arraycopy(bytes, zeroOneFlag ? 1 : 0, decodeByte, fillZeroCount, decodeByte.length - fillZeroCount);
        return decodeByte;
    }

}
