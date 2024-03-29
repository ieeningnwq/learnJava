package com.ieening.learnDynamicProxy.aop;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.proxy.Enhancer;

public class CGlibContainer<T> {
    // 每个类的每个切点的方法列表
    private static Map<Class<?>, Map<InterceptPoint, List<Method>>> interceptMethodsMap = new ConcurrentHashMap<>();
    // 扫描获得所有带有 @Aspect 注解的类，这里为简单起见，将它们写在代码中，忽略扫描的过程
    private static Class<?>[] aspects = new Class<?>[] {
            ServiceLogAspect.class, ExceptionAspect.class };

    static {
        init();
    }

    private static Map<Class<?>, Object> instances = new ConcurrentHashMap<>();

    private static void init() {
        for (Class<?> aspectClass : aspects) {
            Aspect aspect = aspectClass.getAnnotation(Aspect.class);
            if (aspect != null) {
                Method before = gerMethod(aspectClass, "before",
                        new Class<?>[] { Object.class, Method.class, Object[].class });
                Method after = gerMethod(aspectClass, "after",
                        new Class<?>[] { Object.class, Method.class, Object[].class, Object.class });
                Method exception = gerMethod(aspectClass, "exception",
                        new Class<?>[] { Object.class, Method.class, Object[].class, Throwable.class });
                Class<?>[] interceptedAttr = aspect.value();
                for (Class<?> intercepted : interceptedAttr) {
                    addInterceptMethod(intercepted, InterceptPoint.BEFORE, before);
                    addInterceptMethod(intercepted, InterceptPoint.AFTER, after);
                    addInterceptMethod(intercepted, InterceptPoint.EXCEPTION, exception);
                }
            }

        }
    }

    private static void addInterceptMethod(Class<?> cls, InterceptPoint point, Method method) {
        if (method == null) {
            return;
        }
        Map<InterceptPoint, List<Method>> map = interceptMethodsMap.get(cls);
        if (map == null) {
            map = new HashMap<>();
            interceptMethodsMap.put(cls, map);
        }
        List<Method> methods = map.get(point);
        if (methods == null) {
            methods = new ArrayList<>();
            map.put(point, methods);
        }
        methods.add(method);
    }

    private static Method gerMethod(Class<?> aspectClass, String name, Class<?>[] parameterTypes) {
        try {
            return aspectClass.getMethod(name, parameterTypes);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T getInstance(Class<T> cls) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        T instance = null;
        boolean singleton = cls.isAnnotationPresent(SimpleSingleton.class);
        if (!singleton) {
            instance = createInstance(cls);
        } else {
            Object object = instances.get(cls);
            if (object == null) {
                synchronized (cls) {
                    object = createInstance(cls);
                    instances.put(cls, object);
                }
            }
            instance = (T) object;
        }
        return instance;

    };

    private static <T> T createInstance(Class<T> cls) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        T instance = null;
        if (!interceptMethodsMap.containsKey(cls)) {
            instance = (T) cls.getConstructor().newInstance();
        } else {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(cls);
            enhancer.setCallback(new AspectInterceptor());
            instance = (T) enhancer.create();
        }
        for (Field field : cls.getDeclaredFields()) {
            if (field.isAnnotationPresent(SimpleInject.class)) {
                if (!field.canAccess(instance)) {
                    field.setAccessible(true);
                }
                Class<?> fieldClass = field.getType();
                field.set(instance, getInstance(fieldClass));
            }
        }
        return instance;
    }

    public static List<Method> getInterceptMethods(Class<?> cls, InterceptPoint point) {
        Map<InterceptPoint, List<Method>> map = interceptMethodsMap.get(cls);
        if (map == null) {
            return Collections.emptyList();
        }
        List<Method> methods = map.get(point);
        if (methods == null) {
            return Collections.emptyList();
        }
        return methods;
    }

}
