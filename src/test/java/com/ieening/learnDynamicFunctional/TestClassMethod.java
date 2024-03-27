package com.ieening.learnDynamicFunctional;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestClassMethod {
    @Test
    public void testMethodBasic() {
        int aInteger = 123;
        Class<Integer> integerClass = Integer.class;
        try {
            Method method = integerClass.getMethod("parseInt", String.class);
            assertEquals(123, method.invoke(aInteger, String.valueOf(aInteger)));
        } catch (IllegalAccessException
                | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCast() {
        List<Integer> list = new ArrayList<>();
        ArrayList.class.cast(list);
    }

    @Test
    public void testArrayGetComponentType(){
        assertEquals(String.class, String[].class.getComponentType());
    }
}
