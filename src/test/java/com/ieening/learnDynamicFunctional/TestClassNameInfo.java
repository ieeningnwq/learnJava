package com.ieening.learnDynamicFunctional;

import static org.junit.Assert.assertEquals;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestClassNameInfo {
    @Test
    public void testIntClassNameInfo() {
        Class<Integer> intClass = int.class;
        assertEquals("int", intClass.getName());
        assertEquals("int", intClass.getSimpleName());
        assertEquals("int", intClass.getCanonicalName());
        assertEquals(null, intClass.getPackage());
        assertEquals("java.lang", intClass.getPackageName());
    }

    @Test
    public void testIntArrayClassNameInfo() {
        Class<? extends int[]> intArrayClass = int[].class;
        assertEquals("[I", intArrayClass.getName());
        assertEquals("int[]", intArrayClass.getSimpleName());
        assertEquals("int[]", intArrayClass.getCanonicalName());
        assertEquals(null, intArrayClass.getPackage());
        assertEquals("java.lang", intArrayClass.getPackageName());
    }

    @Test
    public void testIntTwoDimensionArrayClassNameInfo() {
        Class<? extends int[][]> intTwoDimensionArrayClass = int[][].class;
        assertEquals("[[I", intTwoDimensionArrayClass.getName());
        assertEquals("int[][]", intTwoDimensionArrayClass.getSimpleName());
        assertEquals("int[][]", intTwoDimensionArrayClass.getCanonicalName());
        assertEquals(null, intTwoDimensionArrayClass.getPackage());
        assertEquals("java.lang", intTwoDimensionArrayClass.getPackageName());
    }

    @Test
    public void testStringClassNameInfo() {
        Class<String> stringClass = String.class;
        assertEquals("java.lang.String", stringClass.getName());
        assertEquals("String", stringClass.getSimpleName());
        assertEquals("java.lang.String", stringClass.getCanonicalName());
        assertEquals("package java.lang", stringClass.getPackage().toString());
        assertEquals("java.lang", stringClass.getPackageName());
    }

    @Test
    public void testStringArrayClassNameInfo() {
        Class<? extends String[]> stringArrayClass = String[].class;
        assertEquals("[Ljava.lang.String;", stringArrayClass.getName());
        assertEquals("String[]", stringArrayClass.getSimpleName());
        assertEquals("java.lang.String[]", stringArrayClass.getCanonicalName());
        assertEquals(null, stringArrayClass.getPackage());
        assertEquals("java.lang", stringArrayClass.getPackageName());
    }

    @Test
    public void testHashMapClassNameInfo() {
        Class<HashMap> hashMapClass = HashMap.class;
        assertEquals("java.util.HashMap", hashMapClass.getName());
        assertEquals("HashMap", hashMapClass.getSimpleName());
        assertEquals("java.util.HashMap", hashMapClass.getCanonicalName());
        assertEquals("package java.util", hashMapClass.getPackage().toString());
        assertEquals("java.util", hashMapClass.getPackageName());
    }

    @Test
    public void testMapPointEntryClassNameInfo() {
        Class<Map.Entry> mapPointEntryClass = Map.Entry.class;
        assertEquals("java.util.Map$Entry", mapPointEntryClass.getName());
        assertEquals("Entry", mapPointEntryClass.getSimpleName());
        assertEquals("java.util.Map.Entry", mapPointEntryClass.getCanonicalName());
        assertEquals("package java.util", mapPointEntryClass.getPackage().toString());
        assertEquals("java.util", mapPointEntryClass.getPackageName());
    }

    @Test
    public void testVoidClassNameInfo() {
        Class<Void> voidClass = void.class;
        assertEquals("void", voidClass.getName());
        assertEquals("void", voidClass.getSimpleName());
        assertEquals("void", voidClass.getCanonicalName());
        assertEquals(null, voidClass.getPackage());
        assertEquals("java.lang", voidClass.getPackageName());
    }

    @Test
    public void testEnumClassNameInfo() {
        Class<? extends Month> monthAprilClass = Month.APRIL.getClass();
        assertEquals("java.time.Month", monthAprilClass.getName());
        assertEquals("Month", monthAprilClass.getSimpleName());
        assertEquals("java.time.Month", monthAprilClass.getCanonicalName());
        assertEquals("package java.time", monthAprilClass.getPackage().toString());
        assertEquals("java.time", monthAprilClass.getPackageName());

        Class<? extends Month> monthClass = Month.class;
        assertEquals("java.time.Month", monthClass.getName());
        assertEquals("Month", monthClass.getSimpleName());
        assertEquals("java.time.Month", monthClass.getCanonicalName());
        assertEquals("package java.time", monthClass.getPackage().toString());
        assertEquals("java.time", monthClass.getPackageName());
    }
}
