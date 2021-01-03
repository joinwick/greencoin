package com.gc.consensus;

import com.gc.utils.BigIntegerUtils;
import com.gc.utils.CommonUtils;
import com.gc.utils.ConstantUtils;
import com.gc.utils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * @author join wick
 * @version 1.0.0
 * @description proof of work
 * @createDate 2020/12/23 16:27
 * @since 1.0.0
 */
public class ProofOfWork {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProofOfWork.class);

    /**
     * convert bits to binary target
     * @param bitString String
     * @return  String
     */
    public String convertBitsToBinaryTarget(String bitString) {
        if (CommonUtils.isEmpty(bitString)) {
            LOGGER.error("empty data in method<ProofOfWork: convertBitsToBinaryTarget>");
            return "";
        }
        if (bitString.length() < ConstantUtils.DEFAULT_VALID_TARGET_LENGTH) {
            LOGGER.error("invalid data in method<ProofOfWork: convertBitsToBinaryTarget>");
            return "";
        }
        String exponentHexString = bitString.substring(2, 4);
        String coefficientHexString = bitString.substring(4, 10);
        String decimalTargetString = getDecimalTarget(exponentHexString, coefficientHexString);
        // convert decimal to binary
        return ConvertUtils.convertSourceFormatToSpecialFormat(decimalTargetString, 10, 2);
    }

    /**
     * get target(decimal system),formula is as follows:
     * coefficientHexString * (2^(8 * (exponentHexString - 3)))
     *
     * @param exponentHexString    String
     * @param coefficientHexString String
     * @return String
     */
    private String getDecimalTarget(String exponentHexString, String coefficientHexString) {
        if (CommonUtils.isEmpty(exponentHexString) || CommonUtils.isEmpty(coefficientHexString)) {
            LOGGER.error("empty data input in method<ProofOfWork: getDecimalTarget>");
            return "";
        }
        BigInteger exponent = new BigInteger(ConvertUtils.convertSourceFormatToSpecialFormat(exponentHexString, 16, 10));
        BigInteger coefficient = new BigInteger(ConvertUtils.convertSourceFormatToSpecialFormat(coefficientHexString, 16, 10));
        // get target with decimal format
        BigInteger decimalTarget = BigIntegerUtils.multiply(coefficient,
                BigIntegerUtils.pow(ConstantUtils.DEFAULT_POWER_BASE,
                        BigIntegerUtils.multiply(BigInteger.valueOf(8),
                                BigIntegerUtils.subtract(exponent, BigInteger.valueOf(3)))));
        return decimalTarget.toString();
    }

    /**
     * check block hash validation
     *
     * @param binaryTargetString String,zero part string(binary format)
     * @param binaryHashString   String,hash string(binary format)
     * @return boolean
     */
    public boolean checkBlockValid(String binaryTargetString, String binaryHashString) {
        if (CommonUtils.isEmpty(binaryTargetString) || CommonUtils.isEmpty(binaryHashString)) {
            LOGGER.error("empty data input in method<ProofOfWork: checkBlockValid>");
            return false;
        }
        return binaryHashString.indexOf(binaryTargetString) == 0;
    }

    /**
     * check block hash validation
     *
     * @param zeroCount        int,target(zero count)
     * @param binaryHashString String,hash string(binary format)
     * @return boolean
     */
    public boolean checkBlockValid(int zeroCount, String binaryHashString) {
        if (CommonUtils.isEmpty(binaryHashString)) {
            LOGGER.error("empty data in method<ProofOfWork: checkBlockValid>");
            return false;
        }
        for (int i = 0; i < zeroCount; i++) {
            if (binaryHashString.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }


}
