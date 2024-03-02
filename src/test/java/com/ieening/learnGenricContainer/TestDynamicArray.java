package com.ieening.learnGenricContainer;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.ieening.learnGenricConatiner.DynamicArray;

public class TestDynamicArray {
    @Test
    public void testToArray() {
        Integer[] ints = new Integer[] { 1, 2, 3, 4, 5 };
        DynamicArray<Integer> intDynamicArray = new DynamicArray<>();
        for (int i : ints) {
            intDynamicArray.add(i);
        }
        assertArrayEquals(ints, intDynamicArray.toArray(Integer.class));
    }
}
