package com.gc.temp;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author join wick
 * @version 1.0.0
 * @className Test.java
 * @description test
 * @createDate 2020/12/11 16:23
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidAlgorithmParameterException, IOException {

        /*//Get secp256k1 pair - which we can use for both addresses
        ECKeyPair keyPair = ECKeyPair.createECKeyPair();
        System.out.println("Private key: " + keyPair.getPrivateKey() + " - " + keyPair.getPrivateKey().length());
        System.out.println("Public key: " + keyPair.getPublicKey() + " - " + keyPair.getPublicKey().length());

        //Calculate Bitcoin Address
        BtcAddressGen.genBitcoinAddress(keyPair.getPublicKey());

        //Calculate Ethereum Address
        EthAddressGen.genEthereumAddress(keyPair.getPublicKey());*/


        BtcAddressGen.genBitcoinAddress("bd620f9e73ab05e9315c9b65b47350de595dfd367e48b951952cc57f4679889fa6ebc124fa8e820e328be1b2cb3f3fc567f030aa7e88ce1469085c7a3e6a0e65");

    }
}
