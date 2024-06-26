package com.ieening.learnAnnotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class MethodAnnotations {
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface QueryParam {
        String value();
    }

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface DefaultValue {
        String value() default "";
    }

    public void hello(@QueryParam("action") String action,
            @QueryParam("sort") @DefaultValue("asc") String sort) {
    }

    public static void main(String[] args) throws Exception {
        Class<?> cls = MethodAnnotations.class;
        Method method = cls.getMethod("hello",
                new Class[] { String.class, String.class });
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println("annotations for parameter " + (i + 1));
            Annotation[] annotationArr = annotations[i];
            for (Annotation annotation : annotationArr) {
                if (annotation instanceof QueryParam) {
                    QueryParam qp = (QueryParam) annotation;
                    System.out.println(qp.annotationType()
                            .getSimpleName() + ":" + qp.value());
                } else if (annotation instanceof DefaultValue) {
                    DefaultValue dv = (DefaultValue) annotation;
                    System.out.println(dv.annotationType()
                            .getSimpleName() + ":" + dv.value());
                }
            }
        }
    }
}
