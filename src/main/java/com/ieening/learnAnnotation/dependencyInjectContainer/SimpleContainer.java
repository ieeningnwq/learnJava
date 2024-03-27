package com.ieening.learnAnnotation.dependencyInjectContainer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleContainer<T> {
    private static Map<Class<?>, Object> instances = new ConcurrentHashMap<>();

    public static <T> T getInstance(Class<T> cls) {
        boolean singleton = cls.isAnnotationPresent(SimpleSingleton.class);
        if (!singleton) {
            return createInstance(cls);
        }
        Object object = instances.get(cls);
        if (object != null) {
            return (T) object;
        }
        synchronized (cls) {
            object = instances.get(cls);
            if (object == null) {
                object = createInstance(cls);
                instances.put(cls, object);
            }
        }
        return (T) object;

    };

    private static <T> T createInstance(Class<T> cls) {
        try {
            T obj = cls.getConstructor().newInstance();
            for (Field field : cls.getDeclaredFields()) {
                if (field.isAnnotationPresent(SimpleInject.class)) {
                    if (!field.canAccess(obj)) {
                        field.setAccessible(true);
                    }
                    Class<?> fieldType = field.getType();
                    field.set(obj, getInstance(fieldType));
                }
            }
            return obj;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

}
