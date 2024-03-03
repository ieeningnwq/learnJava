package com.ieening.learnGenricContainer;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestArrayList {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void TestArrayListIterate() {
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(123);
        intList.add(456);
        intList.add(789);
        Integer[] ints = new Integer[3];
        int i = 0;
        for (Integer a : intList) {
            ints[i++] = a;
        }
        assertArrayEquals(intList.toArray(), ints);
    }

    @Test
    public void testArrayListReverseTraverse() {
        Integer[] ints = new Integer[] { 123, 45, 678 };

        ArrayList<Integer> intList = new ArrayList<>();
        for (int i = 0; i < ints.length; i++) {
            intList.add(ints[i]);
        }

        ListIterator<Integer> it = intList.listIterator(intList.size());
        Integer[] intsReverse = new Integer[3];
        int index = 0;
        while (it.hasPrevious()) {
            intsReverse[index++] = it.previous();
        }
        assertArrayEquals(intsReverse, new Integer[] { 678, 45, 123 });
    }

    @Test
    public void testArrayListRemoveException() {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(12);
        intList.add(102);
        intList.add(130);
        exception.expect(ConcurrentModificationException.class);
        for (Integer integer : intList) {
            if (integer < 100) {
                intList.remove(integer);
            }
        }
    }

    @Test
    public void testIteratorRemove() {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(12);
        intList.add(102);
        intList.add(130);

        Iterator<Integer> iterator = intList.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer >= 100) {
                iterator.remove();
            }
        }
        assertArrayEquals(new Integer[] { 12 }, intList.toArray());
    }

    @Test
    public void testArrayToArrayList() {
        Integer[] intArray = new Integer[] { 1, 2, 3, 4, 5 };
        ArrayList<Integer> intArrayList = new ArrayList<>(Arrays.asList(intArray));
        assertArrayEquals(intArray, intArrayList.toArray());
    }
}
