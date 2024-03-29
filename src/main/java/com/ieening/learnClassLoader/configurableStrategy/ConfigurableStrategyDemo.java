package com.ieening.learnClassLoader.configurableStrategy;

import java.lang.reflect.InvocationTargetException;

public class ConfigurableStrategyDemo {
    public static IService createService(String serviceFullQualifiedName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Class<?> cls = Class.forName(serviceFullQualifiedName);
        return (IService) cls.getConstructor().newInstance();

    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        IService service;
        service = createService("com.ieening.learnClassLoader.configurableStrategy.ServiceA");
        service.action();
        service = createService("com.ieening.learnClassLoader.configurableStrategy.ServiceB");
        service.action();
    }
}
