package com.ieening.learnDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class GeneralJDKDynamicProxyDemo {
    static interface IServiceA {
        public void sayHello();
    }

    static class ServiceAImpl implements IServiceA {

        @Override
        public void sayHello() {
            System.out.println("hello");
        }

    }

    static interface IServiceB {
        public void fly();
    }

    static class ServiceBImpl implements IServiceB {

        @Override
        public void fly() {
            System.out.println("flying");
        }

    }

    static class SimpleInvocationHandler implements InvocationHandler {
        private Object realObject;

        /**
         * @param realObject
         */
        public SimpleInvocationHandler(Object realObject) {
            this.realObject = realObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("entering " + realObject.getClass().getSimpleName() + "::" + method.getName());
            Object result = method.invoke(realObject, args);
            System.out.println("leaving " + realObject.getClass().getSimpleName() + "::" + method.getName());
            return result;
        }
    }

    private static <T> T getProxy(Class<T> surrogateInterface, T realObject) {
        return (T) Proxy.newProxyInstance(surrogateInterface.getClassLoader(), new Class<?>[] { surrogateInterface },
                new SimpleInvocationHandler(realObject));
    }

    public static void main(String[] args) {
        IServiceA a = new ServiceAImpl();
        IServiceA aProxy = getProxy(IServiceA.class, a);
        aProxy.sayHello();
        byte[] bytes = new byte[30];
        Arrays.fill(bytes, 0, bytes.length, (byte) '*');
        System.out.println(new String(bytes));
        IServiceB b = new ServiceBImpl();
        IServiceB bProxy = getProxy(IServiceB.class, b);
        bProxy.fly();

    }
}
