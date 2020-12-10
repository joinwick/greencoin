package com.gc.cryptology;

import com.gc.utils.CommonUtils;
import com.gc.utils.ConstantUtils;
import com.gc.utils.ExpListUtils;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author join wick
 * @version 1.0.0
 * @className Secp256k1.java
 * @description secp256k1
 * @createDate 2020/12/10 16:09
 * @since 1.0.0
 */
public class Secp256k1 {

    Secp256k1() {
        ExpListUtils.initExpList();
    }

    public BigInteger[] getSignature(byte[] hashValue, byte[] privateKey, byte[] bytes) {
        byte[] randomBytes = fixLength(bytes, 32);
        if (randomBytes[0] < 0){
            randomBytes = Arrays.copyOf(randomBytes, 31);
            randomBytes = fixLength(randomBytes, 32);
        }
        BigInteger randomBigInteger = new BigInteger(1, randomBytes);
        BigInteger[] signatureRes = new BigInteger[2];

        BigInteger r = mutiplyG(randomBigInteger)[0];
        BigInteger r_x_priv = r.multiply(new BigInteger(1, privateKey)).mod(ConstantUtils.GC_ORDER_NUM);
        BigInteger zähler   = (new BigInteger(1, hashValue).add(r_x_priv)).mod(ConstantUtils.GC_ORDER_NUM);
        BigInteger k_inverse = 	randomBigInteger.modInverse(ConstantUtils.GC_ORDER_NUM);
        signatureRes[0] = r;
        signatureRes[1] = k_inverse.multiply(zähler).mod(ConstantUtils.GC_ORDER_NUM);

        return signatureRes;
    }

    /**
     *
     * @param factor
     * @return
     */
    private BigInteger[] mutiplyG(BigInteger factor){
        BigInteger[] voher = ExpListUtils.getNullVector();
        BigInteger[] value = new BigInteger[2];
        for (int i = 0; i <= 255; i++){
            if (factor.testBit(i)){
                value =
            }
        }

    }

    /**
     * m = (Q[1]-P[1])/(Q[0]-P[0])
     * n = P[1] - m*P[0]
     * x = m² - x1 -x2
     * y = -(m*x + n)
     * @param po1
     * @param po2
     * @return
     */
    private BigInteger[] addition(BigInteger[] po1, BigInteger[] po2){
        BigInteger[] nullVector = new BigInteger[2];
        nullVector[0] = new BigInteger("0",16); nullVector[1]  = new BigInteger("0",16);
        if(po1[0].equals(BigInteger.ZERO) && po1[1].equals(BigInteger.ZERO)) return po2;
        if(po2[0].equals(BigInteger.ZERO) && po2[1].equals(BigInteger.ZERO)) return po1;
        if(po2[0].equals(po1[0])) return nullVector;
        BigInteger[] erg = new BigInteger[2];
        BigInteger m = Math_Modulo.div(Math_Modulo.sub(po2[1],po1[1]) , Math_Modulo.sub(po2[0],po1[0]));
        BigInteger n = Math_Modulo.sub(po1[1] , Math_Modulo.mul(m,po1[0]));
        erg[0] = Math_Modulo.sub(Math_Modulo.sub(Math_Modulo.mul(m,m) ,(po1[0])) , (po2[0]));
        erg[1] = Math_Modulo.neg(Math_Modulo.add(Math_Modulo.mul(m,erg[0]) , n));
        return erg;
    }

    /**
     * fix with default value(0) when not enough data
     *
     * @param bytes  byte[]
     * @param length int
     * @return byte[]
     */
    private byte[] fixLength(byte[] bytes, int length) {
        if (CommonUtils.isEmpty(bytes)) {
            return new byte[0];
        }
        int bytesLength = bytes.length;
        if (bytesLength < length) {
            byte[] tempBytes = new byte[length];
            System.arraycopy(bytes, 0, tempBytes, length - bytesLength, bytesLength);
            return tempBytes;
        }
        if (bytesLength > length){
            return Arrays.copyOf(bytes, length);
        }
        return bytes;
    }
}
