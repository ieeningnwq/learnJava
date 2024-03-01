package com.ieening;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestStringBasics {
    @Test
    public void testNewString() {
        String newConstant = "constant string";
        String newConstructor = new String("constructor new string");
        String addString = newConstant + " " + newConstructor;
        assertTrue("constant string constructor new string".equals(addString));
    }

    @Test
    public void testConstantPool() {
        String name1 = "测试字符串常量池";
        String name2 = "测试字符串常量池";
        assertTrue(name1 == name2);
    }

    @Test
    public void testNotConstantPool() {
        String name1 = new String("测试字符串常量池");
        String name2 = new String("测试字符串常量池");
        assertFalse(name1 == name2);
    }

    @Test
    public void testCreateConstantPool() {
        String s1 = "helloworld"; // 1、常量池中
        String s2 = "hello" + "world"; // 2、常量池中
        final String s3 = "hello";
        final String s4 = "world";
        String s5 = s3 + s4; // 3、指向"..."的 final 常量 + 指向"..."的 final 常量在常量池
        String s6 = new String("hello");
        String s7 = new String("world");
        String s8 = s6 + s7;
        String s9 = s8.intern();// 4、字符串对象.intern() 的结果都在常量池
        assertTrue(s1 == s2 && s1 == s5 && s1 == s9);

        String s10 = "hello";
        String s11 = "world";
        String s12 = s10 + s11;
        assertFalse(s1 == s12);
        String s13 = s10 + "world";
        assertFalse(s1 == s13);
        String s14 = s6 + "world";
        assertFalse(s1 == s14);
        String s15 = String.valueOf(new char[] { 'h', 'e', 'l', 'l', 'o', 'w', 'o', 'r', 'l', 'd' });
        assertFalse(s1 == s15);
        String s16 = "hello".concat("world");
        assertFalse(s1 == s16);
    }

    @Test
    public void testStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hello");
        stringBuilder.append("world");
        assertFalse("helloworld" == stringBuilder.toString());
        assertTrue("helloworld".equals(stringBuilder.toString()));
    }

}
