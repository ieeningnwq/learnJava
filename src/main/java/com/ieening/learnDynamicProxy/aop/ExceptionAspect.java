package com.ieening.learnDynamicProxy.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect({ ServiceA.class })
public class ExceptionAspect {
    public static void exception(Object object, Method method, Object[] args, Throwable e) {
        System.err.println("exception when calling: " + method.getName() + ", " + Arrays.toString(args));
    }
}
