package com.gc.consensus;

import com.gc.common.entity.BlockHeaderRecord;
import com.gc.common.entity.BlockRecord;
import com.gc.common.entity.EnumEntity;
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
 * @createDate 2020/12/24 15:32
 * @since 1.0.0
 */
public class ProofOfWorkTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProofOfWorkTest.class);

    private final ProofOfWork proofOfWork = new ProofOfWork();

    String binaryHashString = "00000000000000000000000000000000000000000000000000000000000000" +
            "11101000110000110000000000000000000000000000000000000000000000000000000000000000" +
            "00000000000000000000000000000000000000000000000000000000000000000000000000000000" +
            "0000000000000000000000000000000000";

    String hexHashString = "0000000000000003a30c00000000000000000000000000000000000000000000";

    String binaryTargetString = "00000000000000000000000000000000000000000000000000000000000000";
    int zeroCount = 62;

    String bits = "0x1903a30c";

    @Test
    public void convertBitsToSpecialTarget_Binary_ValidEntry(){
        String binaryTargetString = proofOfWork.convertBitsToSpecialTarget(bits, EnumEntity.RadixType.BIN_RADIX);
        LOGGER.debug("binaryTargetString = <{}>", binaryTargetString);
        binaryTargetString = StringUtils.paddingIterator(
                binaryTargetString,
                ConstantUtils.DEFAULT_ZERO_STRING,
                ConstantUtils.DEFAULT_HASH_BINARY_LENGTH - binaryTargetString.length(),
                true);
        assertEquals(binaryHashString, binaryTargetString);
    }

    @Test
    public void convertBitsToSpecialTarget_Hex_ValidEntry(){
        String hexTargetString = proofOfWork.convertBitsToSpecialTarget(bits, EnumEntity.RadixType.HEX_RADIX);
        LOGGER.debug("hexTargetString = <{}>", hexTargetString);
        hexTargetString = StringUtils.paddingIterator(
                hexTargetString,
                ConstantUtils.DEFAULT_ZERO_STRING,
                ConstantUtils.DEFAULT_HASH_HEX_LENGTH - hexTargetString.length(),
                true);
        assertEquals(hexHashString, hexTargetString);
    }

    @Test
    public void convertHexTargetToBits_ValidEntry(){
        String bitString = proofOfWork.convertHexTargetToBits(hexHashString);
        LOGGER.debug("bitString = <{}>", bitString);
        assertEquals(bits, bitString);
    }

    @Test
    public void convertHexTargetToBits_DefaultDifficultyTarget(){
        String bitString = proofOfWork.convertHexTargetToBits(ConstantUtils.DEFAULT_DIFFICULTY_HEX_TARGET);
        LOGGER.debug("bitString = <{}>", bitString);
        String expectedRes = "0x1d00ffff";
        assertEquals(expectedRes, bitString);
    }

    @Test
    public void convertHexTargetToBits_NormalDifficultyTarget(){
        String hexTargetString = "00000000000404CB000000000000000000000000000000000000000000000000";
        String bitString = proofOfWork.convertHexTargetToBits(hexTargetString);
        LOGGER.debug("bitString = <{}>", bitString);
        String expectedRes = "0x1b0404cb";
        assertEquals(expectedRes, bitString);
    }

    @Test
    public void convertBinaryTargetToBits_ValidEntry(){
        String bitString = proofOfWork.convertHexTargetToBits(hexHashString);
        LOGGER.debug("bitString = <{}>", bitString);
        assertEquals(bits, bitString);
    }

    @Test
    public void checkBlockValid_ValidEntry() {
        assertTrue(proofOfWork.checkBlockValid(binaryTargetString, binaryHashString));
    }

    @Test
    public void testCheckBlockValid_ValidEntry() {
        assertTrue(proofOfWork.checkBlockValid(zeroCount, binaryHashString));
    }

    @Test
    public void calcBlockAverageGenerationTime_ValidEntry() {
        long firstBlockTimeStamp = 1608472377000L;
        long lastBlockTimeStamp  = 1609684377000L;
        BlockRecord firstBlock = new BlockRecord();
        BlockHeaderRecord firstBlockHeader = new BlockHeaderRecord();
        firstBlockHeader.setTimeStamp(firstBlockTimeStamp);
        firstBlock.setBlockHeader(firstBlockHeader);

        BlockRecord lastBlock = new BlockRecord();
        BlockHeaderRecord lastBlockHeader = new BlockHeaderRecord();
        lastBlockHeader.setTimeStamp(lastBlockTimeStamp);
        lastBlock.setBlockHeader(lastBlockHeader);

        long expectedRes = 600L;
        assertEquals(expectedRes, proofOfWork.calcBlockAverageGenerationTime(firstBlock, lastBlock));
    }

}