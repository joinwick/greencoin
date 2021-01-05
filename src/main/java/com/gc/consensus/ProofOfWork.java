package com.gc.consensus;

import com.gc.common.entity.BlockRecord;
import com.gc.common.entity.EnumEntity;
import com.gc.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

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
     * convert bits to special format target
     *
     * @param bitString String
     * @param radixType EnumEntity.RadixType
     * @return String
     */
    public String convertBitsToSpecialTarget(String bitString, EnumEntity.RadixType radixType) {
        String[] targetFactor = getTargetFactor(bitString);
        if (CommonUtils.isEmpty(targetFactor) || CommonUtils.isEmpty(radixType)) {
            LOGGER.error("invalid data in method<ProofOfWork: convertBitsToSpecialTarget>");
            return "";
        }
        // get exponent hex string
        String exponentString = targetFactor[0];
        // get coefficient hex string
        String coefficientString = targetFactor[1];
        // get decimal target string
        String decimalTargetString = getDecimalTarget(exponentString, coefficientString);
        // convert decimal to special format
        return ConvertUtils.convertSourceFormatToSpecialFormat(decimalTargetString, 10, Integer.parseInt(radixType.getValue()));
    }

    /**
     * convert hex string to bits
     *
     * @param hexTargetString String
     * @return String
     */
    public String convertHexTargetToBits(String hexTargetString) {
        if (CommonUtils.isEmpty(hexTargetString)) {
            LOGGER.error("empty data in method<ProofOfWork: convertHexTargetToBits>");
            return "";
        }
        // convert to lower case
        hexTargetString = hexTargetString.toLowerCase();
        int nonZeroBeginIndex = 0;
        int nonZeroEndIndex = 0;
        for (int i = 0; i < hexTargetString.length() - 1; i++) {
            char c = hexTargetString.charAt(i);
            if (c != ConstantUtils.DEFAULT_ZERO_CHAR && nonZeroBeginIndex == 0) {
                nonZeroBeginIndex = i;
            }
            // current char is still zero char
            if (c == ConstantUtils.DEFAULT_ZERO_CHAR && nonZeroBeginIndex == 0) {
                continue;
            }
            // the index of last non-zero char
            if (c != ConstantUtils.DEFAULT_ZERO_CHAR) {
                nonZeroEndIndex = i;
            }
        }
        String coefficientString = hexTargetString.substring(nonZeroBeginIndex, nonZeroEndIndex + 1);
        coefficientString = StringUtils.paddingIterator(coefficientString, ConstantUtils.DEFAULT_ZERO_STRING, ConstantUtils.DEFAULT_COEFFICIENT_LENGTH - coefficientString.length(), true);
        BigDecimal targetDecimal = new BigDecimal(new BigInteger(hexTargetString, 16));
        BigDecimal coefficientDecimal = new BigDecimal(new BigInteger(coefficientString, 16));
        double exponent = targetDecimal.divide(coefficientDecimal, RoundingMode.HALF_EVEN).doubleValue();
        double exponentData = Math.log(exponent) / Math.log(ConstantUtils.DEFAULT_POWER_BASE.intValue()) / ConstantUtils.DEFAULT_TARGET_FACTOR_ONE.doubleValue() +
                ConstantUtils.DEFAULT_TARGET_FACTOR_TWO.doubleValue();
        return ConstantUtils.DEFAULT_BITS_PREFIX + Integer.toHexString((int) exponentData) + coefficientString;
    }

    /**
     * calculate next mining period bits,next bits = current bits * actual time / expected time
     *
     * @param currentMiningBitsString String
     * @param averageMiningTime       int
     * @return String
     */
    public String calcNextMiningBits(String currentMiningBitsString, int averageMiningTime) {
        if (CommonUtils.isEmpty(currentMiningBitsString) || averageMiningTime < 0){
            LOGGER.error("invalid data in method<ProofOfWork: calcNextMiningBits>");
            return ConstantUtils.DEFAULT_DIFFICULTY_BITS_TARGET;
        }
        // get current bits with decimal
        String currentDecimalTarget = convertBitsToSpecialTarget(currentMiningBitsString, EnumEntity.RadixType.DEC_RADIX);
        // calculate next bits with decimal
        BigInteger nextBits = BigIntegerUtils.divide(
                BigIntegerUtils.multiply(new BigInteger(currentDecimalTarget), BigInteger.valueOf(averageMiningTime)),
                BigInteger.valueOf(ConstantUtils.DEFAULT_BLOCK_EXPECTED_GENERATION_TIME));
        // convert decimal to hex
        String hexBits = ConvertUtils.convertSourceFormatToSpecialFormat(nextBits.toString(), 10, 16);
        hexBits = StringUtils.paddingIterator(hexBits, ConstantUtils.DEFAULT_ZERO_STRING, ConstantUtils.DEFAULT_HASH_HEX_LENGTH - hexBits.length(), true);
        // get bits target
        return convertHexTargetToBits(hexBits);
    }

    /**
     * calculate average generation time of previous blocks(2020)
     *
     * @param firstBlock BlockRecord
     * @param lastBlock  BlockRecord
     * @return long(seconds)
     */
    public int calcBlockAverageGenerationTime(BlockRecord firstBlock, BlockRecord lastBlock) {
        if (CommonUtils.isEmpty(firstBlock) || CommonUtils.isEmpty(lastBlock)) {
            LOGGER.error("invalid data in method<ProofOfWork: getBlockAverageGenerationTime>");
            return 0;
        }
        long firstBlockGenerationTimeStamp = firstBlock.getBlockHeader().getTimeStamp();
        long lastBlockGenerationTimeStamp = lastBlock.getBlockHeader().getTimeStamp();
        long timeDiffSeconds = (lastBlockGenerationTimeStamp - firstBlockGenerationTimeStamp) / 1000L;
        long difficultyAdjustLowerLimit = ConstantUtils.DEFAULT_DIFFICULTY_ADJUST_PERIOD / ConstantUtils.DEFAULT_DIFFICULTY_ADJUST_FACTOR;
        long difficultyAdjustUpperLimit = ConstantUtils.DEFAULT_DIFFICULTY_ADJUST_PERIOD * ConstantUtils.DEFAULT_DIFFICULTY_ADJUST_FACTOR;
        // prevent too high difficulty
        if (timeDiffSeconds < difficultyAdjustLowerLimit) {
            timeDiffSeconds = difficultyAdjustLowerLimit;
        }
        // prevent too low difficulty
        else if (timeDiffSeconds > difficultyAdjustUpperLimit) {
            timeDiffSeconds = difficultyAdjustUpperLimit;
        }
        // calculate block average generation time
        return (int) (timeDiffSeconds / ConstantUtils.DEFAULT_BLOCK_COUNT);
    }

    /**
     * convert bits to binary target
     *
     * @param bitString String
     * @return String[]
     */
    private String[] getTargetFactor(String bitString) {
        if (CommonUtils.isEmpty(bitString)) {
            LOGGER.error("empty data in method<ProofOfWork: getTargetFactor>");
            return new String[0];
        }
        bitString = bitString.toLowerCase();
        if (bitString.length() < ConstantUtils.DEFAULT_VALID_TARGET_LENGTH ||
                !bitString.startsWith(ConstantUtils.DEFAULT_BITS_PREFIX)) {
            LOGGER.error("invalid data in method<ProofOfWork: getTargetFactor>");
            return new String[0];
        }
        String[] targetFactor = new String[2];
        targetFactor[0] = bitString.substring(2, 4);
        targetFactor[1] = bitString.substring(4, 10);
        return targetFactor;
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
                        BigIntegerUtils.multiply(ConstantUtils.DEFAULT_TARGET_FACTOR_ONE,
                                BigIntegerUtils.subtract(exponent, ConstantUtils.DEFAULT_TARGET_FACTOR_TWO))));
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
