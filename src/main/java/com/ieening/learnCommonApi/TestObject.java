package com.ieening.learnCommonApi;

public class TestObject {
    public static void main(String[] args) {
        Object foo = new Object();
        Object stringObj = "小姑";
        Object tesObject = new TestObject();
        Object arrayObject = new int[5]; // 编译通过，此时把数组对象当成一个普通对象赋值给arrayObject
        Object[] objects = new Object[3];
        Object[] strings = new String[3];
        // Object[] intArray = new int[3]; // 编译报错：Type mismatch: cannot convert from
        // int[] to Object[]
        Float f1 = 0.01f;
        Float f2 = 0.1f * 0.1f;
        System.out.println(f1.equals(f2));
        System.out.println(Float.floatToIntBits(f1));
        System.out.println(Float.floatToIntBits(f2));
        Character
    }
}
