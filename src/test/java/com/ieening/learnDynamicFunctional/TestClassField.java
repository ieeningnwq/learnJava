package com.ieening.learnDynamicFunctional;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.Test;

public class TestClassField {
    @Test
    public void testFieldBasic() throws IllegalArgumentException, IllegalAccessException {
        List<String> obj = Arrays.asList(new String[] { "老马", "编程" });
        Class<?> cls = obj.getClass();
        Map<String, String> fieldValueMap = new HashMap<>();
        for (Field f : cls.getDeclaredFields()) {
            f.setAccessible(true);
            Object value = f.get(obj);
            if (value instanceof Object[]) {
                fieldValueMap.put(f.getName(), Arrays.toString((Object[]) value));
            } else {
                fieldValueMap.put(f.getName(), f.get(obj).toString());
            }
        }
        assertEquals("{a=[老马, 编程], serialVersionUID=-2764017481108945198}", fieldValueMap.toString());
    }
}
