package com.ieening.learnDynamicProxy.aop;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AspectInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;
        // 执行 before 方法
        List<Method> beforeMethods = CGlibContainer.getInterceptMethods(obj.getClass().getSuperclass(),
                InterceptPoint.BEFORE);
        for (Method beforeMethod : beforeMethods) {
            beforeMethod.invoke(null, new Object[] { obj, method, args });
        }
        try {
            // 调用原始方法
            result = proxy.invokeSuper(obj, args);
            // 调用 after 方法
            List<Method> afterMethods = CGlibContainer.getInterceptMethods(obj.getClass().getSuperclass(),
                    InterceptPoint.AFTER);
            for (Method afterMethod : afterMethods) {
                afterMethod.invoke(null, new Object[] { obj, method, args, result });
            }
        } catch (Exception e) {
            // 执行 Exception 方法
            List<Method> exceptionMethods = CGlibContainer.getInterceptMethods(obj.getClass().getSuperclass(),
                    InterceptPoint.EXCEPTION);
            for (Method exceptionMethod : exceptionMethods) {
                exceptionMethod.invoke(null, new Object[] { obj, method, args, e });
            }
            throw e;
        }
        return result;
    }

}
