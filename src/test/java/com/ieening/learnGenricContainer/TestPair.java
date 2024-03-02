package com.ieening.learnGenricContainer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import com.ieening.learnGenricConatiner.DynamicArray;
import com.ieening.learnGenricConatiner.NumberPair;
import com.ieening.learnGenricConatiner.Pair;
import com.ieening.learnGenricConatiner.PairObject;
import com.ieening.learnGenricConatiner.PairTwoGenric;

public class TestPair {
    @Test
    public void testPairInteger() {
        int first = 1;
        int second = 2;
        Pair<Integer> minmax = new Pair<Integer>(first, second);
        assertTrue(first == minmax.getFirst());
        assertTrue(second == minmax.getSecond());
    }

    @Test
    public void testPairString() {
        String first = "name";
        String second = "ieening";
        Pair<String> minmax = new Pair<String>(first, second);
        assertTrue(first == minmax.getFirst());
        assertTrue(second == minmax.getSecond());
    }

    @Test
    public void testPairTwoGenric() {
        String first = "age";
        int second = 28;
        PairTwoGenric<String, Integer> pairTwoGenric = new PairTwoGenric<>(first, second);
        assertTrue(first == pairTwoGenric.getFirst());
        assertTrue(second == pairTwoGenric.getSecond());
    }

    @Test
    public void testPairObject() {
        String first = "age";
        int second = 28;
        PairObject pairObject = new PairObject(first, second);

        assertTrue(first == (String) pairObject.getFirst());
        assertTrue(second == (Integer) pairObject.getSecond());
    }

    public static <T> int indexOf(T[] arr, T elm) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elm)) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void testIndexOf() {
        assertTrue(-1 == indexOf(new Integer[] { 1, 3, 5 }, 10));
        assertTrue(0 == indexOf(new String[] { "hello", "你好", "嘛" }, "hello"));
    }

    @Test
    public void testNumberPairSum() {
        int first = 12;
        double second = 14.0;
        NumberPair<Integer, Double> numberPair = new NumberPair<>(first, second);
        assertTrue((first + second) == numberPair.sum());
    }

    public static <T extends Comparable<T>> T max(T[] arr) {
        if (Objects.isNull(arr) || arr.length == 0) {
            return null;
        }
        T max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(max) > 0) {
                max = arr[i];
            }
        }
        return max;
    }

    @Test
    public void testComparableMax() {
        assertTrue(7.1 == max(new Double[] { 1.2, 4.3, 0.9, 7.1 }));
    }

    @Test
    public void testDynamicArrayAddAll(){
        DynamicArray<Number> numbers = new DynamicArray<>();
        DynamicArray<Integer> ints = new DynamicArray<>();
        ints.add(100);
        ints.add(34);
        numbers.addAll(ints);
        assertArrayEquals(numbers.getElementData(), ints.getElementData());
    }
}
