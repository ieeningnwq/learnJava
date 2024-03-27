package com.ieening.learnAnnotation.dependencyInjectContainer;

public class SimpleContainerDemo<T> {
    static class ServiceA {
        @SimpleInject
        ServiceB b;

        /**
         * 
         */
        public ServiceA() {
        }

        public void callB() {
            b.action();
        }

        /**
         * @return the b
         */
        public ServiceB getB() {
            return b;
        }

        /**
         * @param b the b to set
         */
        public void setB(ServiceB b) {
            this.b = b;
        }
    }

    @SimpleSingleton
    static class ServiceB {
        public ServiceB() {
        }

        public void action() {
            System.out.println("I am B");
        }

    }

    public static void main(String[] args) {
        ServiceB b = SimpleContainer.getInstance(ServiceB.class);
        ServiceA a = SimpleContainer.getInstance(ServiceA.class);
        a.callB();
        assert b == a.b;
    }
}
