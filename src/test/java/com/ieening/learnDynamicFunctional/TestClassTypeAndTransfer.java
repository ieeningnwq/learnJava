package com.ieening.learnDynamicFunctional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * TestClassTypeAndTransfer
 */
public class TestClassTypeAndTransfer {

    @Test
    public void testInstanceofIsInstance() {
        Object object = new Object();
        assertTrue(object instanceof Object);
        assertTrue(Object.class.isInstance(object));
    }

    @Test
    public void testClassForName() throws ClassNotFoundException {
        String name = "[[Ljava.lang.String;";
        assertEquals(String[][].class, Class.forName(name));
    }

    @Test(expected = ClassNotFoundException.class)
    public void testForNameInt() throws ClassNotFoundException{
        Class.forName("int");
    }

}