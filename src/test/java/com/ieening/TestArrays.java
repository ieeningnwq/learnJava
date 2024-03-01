package com.ieening;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

public class TestArrays {
    @Test
    public void testArraysToString() {
        int[] intArray = new int[] { 1, 2, 3, 4 };
        assertTrue("[1, 2, 3, 4]".equals(Arrays.toString(intArray)));
        assertTrue(("[I@" + Integer.toHexString(intArray.hashCode())).equals(intArray.toString()));
    }

    @Test
    public void testArraysSortPrimitiveTypeSort() {
        int[] intArray = new int[] { 7, 5, 1, 3, 2, 4, 3, 0 };
        Arrays.sort(intArray);
        assertTrue("[0, 1, 2, 3, 3, 4, 5, 7]".equals(Arrays.toString(intArray)));
    }

    @Test
    public void testArraysSortObject() {
        String[] stringArray = new String[] { "Break", "abc", "hello", "world" };
        Arrays.sort(stringArray);
        assertTrue("[Break, abc, hello, world]".equals(Arrays.toString(stringArray)));
    }

    @Test
    public void testArraysSortComparator() {
        String[] stringArray = new String[] { "Break", "abc", "hello", "world" };
        Arrays.sort(stringArray, String.CASE_INSENSITIVE_ORDER);
        assertTrue("[abc, Break, hello, world]".equals(Arrays.toString(stringArray)));
    }

    @Test
    public void testArraysSortComparatorReverse() {
        String[] stringArray = new String[] { "Break", "abc", "hello", "world" };
        Arrays.sort(stringArray, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o2.compareToIgnoreCase(o1);
            }

        });
        assertTrue("[world, hello, Break, abc]".equals(Arrays.toString(stringArray)));
    }

    @Test
    public void testArraysBinarySearch() {
        int[] intArray = new int[] { -1, 0, 1, 2, 3, 5 };
        System.out.println(Arrays.binarySearch(intArray, 1));
        assertTrue(2 == Arrays.binarySearch(intArray, 1));
        assertTrue(-6 == Arrays.binarySearch(intArray, 4));
    }

    @Test
    public void testArraysCopyOf() {
        int[] originIntArray = new int[] { 1, 2, 3, 4, 5, 6 };
        int[] lengthSubTwoIntArray = Arrays.copyOf(originIntArray, originIntArray.length - 2);
        assertTrue("[1, 2, 3, 4]".equals(Arrays.toString(lengthSubTwoIntArray)));
        int[] lengthPlusTwoIntArray = Arrays.copyOf(originIntArray, originIntArray.length + 2);
        assertTrue("[1, 2, 3, 4, 5, 6, 0, 0]".equals(Arrays.toString(lengthPlusTwoIntArray)));
    }

    @Test
    public void testArraysEquals() {
        assertTrue(Arrays.equals(new String[] { new String("12"), new String("34") }, new String[] { "12", "34" }));
    }
}
