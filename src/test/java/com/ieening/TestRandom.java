package com.ieening;

import static org.junit.Assert.assertArrayEquals;

import java.util.Random;

import org.junit.Test;

public class TestRandom {

    @Test
    public void testSeed() {
        long randomSeed = Double.valueOf(Math.random() * 1000000).longValue();

        Random firstRandom = new Random(randomSeed);
        Random secondRandom = new Random(randomSeed);

        int[] firstRandomIntArray = new int[10];
        int[] secondRandomIntArray = new int[10];

        for (int i = 0; i < firstRandomIntArray.length; i++) {
            firstRandomIntArray[i] = firstRandom.nextInt();
        }
        for (int i = 0; i < secondRandomIntArray.length; i++) {
            secondRandomIntArray[i] = secondRandom.nextInt();
        }

        assertArrayEquals(firstRandomIntArray, secondRandomIntArray);
    }

}
