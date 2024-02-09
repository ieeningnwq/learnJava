package com.ieening;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestFibonacciTest {
    @Test
    public void testFibonacciTest() {
        assertTrue(FibonacciTest.calculateFibonacciRecursion(0) == 1);
        assertTrue(FibonacciTest.calculateFibonacciRecursion(1) == 1);
        assertTrue(FibonacciTest.calculateFibonacciRecursion(2) == 2);
        assertTrue(FibonacciTest.calculateFibonacciRecursion(3) == 3);
        assertTrue(FibonacciTest.calculateFibonacciRecursion(4) == 5);
    }
}
