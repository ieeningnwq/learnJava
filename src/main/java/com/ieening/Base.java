package com.ieening;

public class Base {
    private String member;

    public Base(String member) {
        this.member = member;
    }

    public Base() {
        test();
    }

    public void test() {

    }

    public long sum(int a, long b) {
        System.out.println("base_int_long");
        return a + b;
    }

}

/**
 * Child
 */
class Child extends Base {
    private int a = 123;

    @Override
    public void test() {
        System.out.println(a);
    }

    public long sum(int a, long b) {
        System.out.println("child_int_long");
        return a + b;
    }

    public static void main(String[] args) {
        Child c = new Child();
        int a = 2;
        int b = 3;
        c.sum(a, b);
    }

}