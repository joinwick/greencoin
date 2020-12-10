package com.gc.cryptology;

import com.gc.common.entity.EnumEntity;
import com.gc.utils.CommonUtils;
import com.gc.utils.ConstantUtils;
import com.gc.utils.StringUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @className HashService.java
 * @description hash service
 * @createDate 2020/12/8 17:56
 * @since 1.0.0
 */
public class HashService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HashService.class);

    /**
     * get hex string
     *
     * @param data String
     * @return String
     */
    public String getHexString(String data, EnumEntity.HashAlgorithm hashAlgorithm) {
        if (CommonUtils.isEmpty(data)) {
            LOGGER.error("empty data");
            return "";
        }
        byte[] initialData = data.getBytes(ConstantUtils.DEFAULT_CHARSET);
        byte[] hashData = getHashedData(initialData, hashAlgorithm);
        return StringUtils.convertByteToHexString(hashData);
    }

    /**
     * generate hash data
     *
     * @param initialData   byte[]
     * @param hashAlgorithm EnumEntity.HashAlgorithm
     * @return byte[]
     */
    private byte[] getHashedData(byte[] initialData, EnumEntity.HashAlgorithm hashAlgorithm) {
        Digest digest;
        byte[] shaMessage;
        switch (hashAlgorithm) {
            case SHA256:
                digest = new SHA256Digest();
                digest.update(initialData, 0, initialData.length);
                break;
            case RIPEMD160:
                digest = new RIPEMD160Digest();
                digest.update(initialData, 0, initialData.length);
                break;
            default:
                LOGGER.debug("unsupported hash algorithm");
                return new byte[0];
        }
        shaMessage = new byte[digest.getDigestSize()];
        digest.doFinal(shaMessage, 0);
        return shaMessage;
    }

}
