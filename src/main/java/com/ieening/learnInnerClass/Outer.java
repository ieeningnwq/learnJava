package com.ieening.learnInnerClass;

public class Outer {
    private static int shared = 100;
    private int a = 101;

    public class InnerOuter {
        public void innerMethod() {
            System.out.println("outer a " + a);
            Outer.this.action();
        }
    }

    private void action() {
        System.out.println("action");
    }

    public void testInnerOuter() {
        InnerOuter inner = new InnerOuter();
        inner.innerMethod();
    }

    public static class StaticInner {
        public void innerMethod() {
            System.out.println("inner " + shared);
        }
    }

    public void testStaticInner() {
        StaticInner staticInner = new StaticInner();
        staticInner.innerMethod();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.testStaticInner();

        Outer.InnerOuter innerOuter = outer.new InnerOuter();
        innerOuter.innerMethod();
    }
}
