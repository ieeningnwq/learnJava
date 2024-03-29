package com.ieening.learnDynamicProxy;

public class SimpleStaticProxyDemo {
    static interface IService {
        public void sayHello();
    }

    static class RealService implements IService {

        @Override
        public void sayHello() {
            System.out.println("hello");
        }
    }

    static class TraceProxy implements IService {
        private IService realService;

        /**
         * @param realService
         */
        public TraceProxy(IService realService) {
            this.realService = realService;
        }

        @Override
        public void sayHello() {
            System.out.println("entering sayHello");
            realService.sayHello();
            System.out.println("leaving sayHello");
        }

    }

    public static void main(String[] args) {
        IService reaService = new RealService();
        IService proxyService = new TraceProxy(reaService);
        proxyService.sayHello();
    }
}
