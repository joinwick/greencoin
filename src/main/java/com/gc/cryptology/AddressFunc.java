package com.gc.cryptology;

import com.gc.common.entity.EnumEntity;
import com.gc.utils.CommonUtils;
import com.gc.utils.ConstantUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author join wick
 * @version 1.0.0
 * @description address generation
 * @createDate 2020/12/11 14:34
 * @since 1.0.0
 */
public class AddressFunc {

    private final HashFunc hashFunc;
    private final BaseCodec baseFunc;

    public AddressFunc() {
        hashFunc = new HashFunc();
        baseFunc = new BaseCodec();
    }

    /**
     * generate green coin address
     *
     * @param publicKey String
     * @return String
     * @throws IOException IOException
     */
    public String genAddress(String publicKey) throws IOException {
        // 1.gen initial address
        byte[] initialAddressBytes = genInitialAddress(publicKey);
        if (CommonUtils.isEmpty(initialAddressBytes)) {
            return null;
        }
        // 2.calculate check sum
        byte[] checkBytes = genCheckedValue(initialAddressBytes);
        // 3.add check sum to the end of initial address
        byte[] gcAddressBytes = CommonUtils.mergeArray(initialAddressBytes, checkBytes);
        // 4.encode with base58
        return baseFunc.base58Encode(gcAddressBytes);
    }

    /**
     * get initial address by public key
     *
     * @param publicKey String
     * @return byte[]
     * @throws IOException IOException
     */
    private byte[] genInitialAddress(String publicKey) throws IOException {
        if (CommonUtils.isEmpty(publicKey)) {
            return new byte[0];
        }
        // convert default key prefix
        byte[] prefixBytes = DatatypeConverter.parseHexBinary(ConstantUtils.DEFAULT_GC_ACCOUNT_PREFIX);
        byte[] initialPublicKeyBytes = baseFunc.base64Decode(publicKey);
        // 1.add default prefix 0x04 for public key, convert to byte array
//        byte[] publicKeyBytes = DatatypeConverter.parseHexBinary(StringUtils.appendByCondition(publicKey, ConstantUtils.DEFAULT_GC_ACCOUNT_PREFIX, true));
        byte[] publicKeyBytes = CommonUtils.mergeArray(prefixBytes, initialPublicKeyBytes);
        // 2.hash byte array using sha256
        byte[] sha256PublicKeyBytes = hashFunc.getHashedData(publicKeyBytes, EnumEntity.HashAlgorithm.SHA256);
        // 3.hash byte array using riped160
        byte[] riped160PublicKeyBytes = hashFunc.getHashedData(sha256PublicKeyBytes, EnumEntity.HashAlgorithm.RIPEMD160);

        // 4.add default prefix 0x00 for riped160PublicKeyBytes as main network identifier
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write((byte) 0);
        byteArrayOutputStream.write(riped160PublicKeyBytes);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * gen checked value by double sha256
     *
     * @param initialAddressBytes byte[]
     * @return byte[]
     */
    private byte[] genCheckedValue(byte[] initialAddressBytes) {
        // 1.hash byte array using sha256
        byte[] sha256CheckBytes = hashFunc.getHashedData(initialAddressBytes, EnumEntity.HashAlgorithm.SHA256);
        // 2.hash byte array using SHA256
        byte[] riped160CheckBytes = hashFunc.getHashedData(sha256CheckBytes, EnumEntity.HashAlgorithm.SHA256);
        // 3.extract checked value
        return Arrays.copyOfRange(riped160CheckBytes, 0, ConstantUtils.DEFAULT_ACCOUNT_FIX_LENGTH);
    }

    /**
     * check address validation
     *
     * @param publicKey      String
     * @param accountAddress String
     * @return boolean
     * @throws IOException IOException
     */
    public boolean addressValidation(String publicKey, String accountAddress) throws IOException {
        if (CommonUtils.isEmpty(publicKey) || CommonUtils.isEmpty(accountAddress)) {
            return false;
        }
        // get initial address by public key
        byte[] initialAddressBytes = genInitialAddress(publicKey);
        // get check sum
        byte[] checkBytesFromKey = genCheckedValue(initialAddressBytes);
        // decode account address
        byte[] address = baseFunc.base58Decode(accountAddress);
        byte[] checkBytesFromAddress = new byte[ConstantUtils.DEFAULT_ACCOUNT_FIX_LENGTH];
        System.arraycopy(address, address.length - ConstantUtils.DEFAULT_ACCOUNT_FIX_LENGTH, checkBytesFromAddress, 0, ConstantUtils.DEFAULT_ACCOUNT_FIX_LENGTH);
        return Arrays.equals(checkBytesFromKey, checkBytesFromAddress);
    }


}
