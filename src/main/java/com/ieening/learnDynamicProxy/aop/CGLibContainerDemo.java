package com.ieening.learnDynamicProxy.aop;

import java.lang.reflect.InvocationTargetException;

public class CGLibContainerDemo {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

        ServiceA a = CGlibContainer.getInstance(ServiceA.class);
        a.callB();
        ServiceA secondA = CGlibContainer.getInstance(ServiceA.class);
        assert a == secondA;
        secondA.callThrowException();
    }
}
