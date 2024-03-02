package com.ieening.learnGenricContainer;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ieening.learnGenricConatiner.Pair;

public class TestGenric {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testGenricClass() {
        Pair<Integer> p1 = new Pair<Integer>(1, 100);
        Pair<String> p2 = new Pair<String>("hello", "world");
        assertTrue(Pair.class == p1.getClass());
        assertTrue(Pair.class == p2.getClass());
    }

    @Test
    public void testArrayStoreException() {
        Integer[] ints = new Integer[10];
        Object[] objs = ints;
        exception.expect(ArrayStoreException.class);
        objs[0] = "hello";
    }
}
