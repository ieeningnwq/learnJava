package com.ieening;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestNumberBinaryRepresentation {

    @Test
    public void testPositiveIntegerBinaryRepresentation() {
        assertTrue("01111111111111111111111111111111"
                .equals(StringUtils.leftPad(Integer.toBinaryString(2_147_483_647), 32, "0")));
        assertTrue("00000000000000000000000000000000"
                .equals(StringUtils.leftPad(Integer.toBinaryString(0), 32, "0")));
        assertTrue("00000000000000000000000000000010"
                .equals(StringUtils.leftPad(Integer.toBinaryString(2), 32, "0")));
        assertTrue("00000000000000000000000000000011"
                .equals(StringUtils.leftPad(Integer.toBinaryString(3), 32, "0")));
        assertTrue("00000000000000000000000000001010"
                .equals(StringUtils.leftPad(Integer.toBinaryString(10), 32, "0")));
    }

    @Test
    public void testNegativeIntegerBinaryRepresentation() {
        assertTrue("00000001".equals(Integer.toBinaryString(((byte) 1 & 0xFF) + 0x100).substring(1)));
        assertTrue("11111111".equals(Integer.toBinaryString(((byte) -1 & 0xFF) + 0x100).substring(1)));
        assertTrue("10000001".equals(Integer.toBinaryString(((byte) -127 & 0xFF) + 0x100).substring(1)));
    }

    @Test
    public void testFloatBinaryRepresentation() {
        assertTrue("1000011001100100010000000000000"
                .equals(Integer.toBinaryString(Float.floatToIntBits(178.125f))));
        assertTrue("100000001100110010001000000000000000000000000000000000000000000"
                .equals(Long.toBinaryString(Double.doubleToLongBits(178.125d))));
    }

    public static void recover(String str) throws UnsupportedEncodingException {
        String[] charsets = new String[] {
                "windows-1252", "GB18030", "Big5", "UTF-8" };
        for (int i = 0; i < charsets.length; i++) {
            for (int j = 0; j < charsets.length; j++) {
                if (i != j) {
                    String s = new String(str.getBytes(charsets[i]), charsets[j]);
                    System.out.println("---- 原来编码(A)假设是： "
                            + charsets[j] + ", 被错误解读为了(B): " + charsets[i]);
                    System.out.println(s);
                    System.out.println();
                }
            }
        }
    }

    @Test
    public void testEncodeRecover() throws UnsupportedEncodingException {
        String originString = "认真学习Java";
        String newStr = new String(originString.getBytes("UTF-8"), "GB18030");
        recover(newStr);
    }

    @Test
    public void testBitwiseShiftOperation() {
        assertTrue(-12 == -3 << 2);
        assertTrue(-2 == -3 >> 1);
        assertTrue(2147483646 == -3 >>> 1);
    }

    @Test
    public void testBitwiseLogicalOperation() {
        assertTrue(2 == (6 & 3));
        assertTrue(7 == (6 | 3));
        assertTrue(5 == (6 ^ 3));
        assertTrue(-7 == ~6);
    }
}
