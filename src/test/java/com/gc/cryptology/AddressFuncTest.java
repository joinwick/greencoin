package com.gc.cryptology;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/11 16:31
 * @since 1.0.0
 */
public class AddressFuncTest {
    private final AddressFunc addressService = new AddressFunc();
    String publicKey = "bd620f9e73ab05e9315c9b65b47350de595dfd367e48b951952cc57f4679889fa6ebc124fa8e820e328be1b2cb3f3fc567f030aa7e88ce1469085c7a3e6a0e65";
    String expectedAddress = "1LeBKcSYqpfoE5VXaPCXnTLwiZi6D8NbLX";

    @Test
    public void genAddress_ValidEntry() throws IOException {
        String actualAddress = addressService.genAddress(publicKey);
        assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void addressValidation_ValidEntry() throws IOException {
        boolean validAddress = addressService.addressValidation(publicKey, expectedAddress);
        assertTrue(validAddress);
    }
}