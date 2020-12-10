package com.gc.cryptology;

import com.gc.common.entity.EnumEntity;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * @author join wick
 * @version 1.0.0
 * @className Secp256k1Service.java
 * @description Secp256k1 service
 * @createDate 2020/12/10 15:51
 * @since 1.0.0
 */
public class Secp256k1Service {
    private static final Logger LOGGER = LoggerFactory.getLogger(Secp256k1Service.class);

    // get ecc parameters for secp256k1
    private static final X9ECParameters ECC_PARAMETERS = CustomNamedCurves.getByName(EnumEntity.ECCAlgorithm.SECP256K1.getValue());

    private static final ECDomainParameters CURVE_PARAMETER = new ECDomainParameters(
            ECC_PARAMETERS.getCurve(),
            ECC_PARAMETERS.getG(),
            ECC_PARAMETERS.getN(),
            ECC_PARAMETERS.getH());

    public BigInteger[] getSignature(byte[] hashValue, byte[] privateKey, byte[] bytes){

    }
}
