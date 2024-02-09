package com.ieening;

public class FibonacciTest {
    public static int calculateFibonacciRecursion(int index) {
        if (index == 0 || index == 1) {
            return 1;
        } else {
            return calculateFibonacciRecursion(index - 2) + calculateFibonacciRecursion(index - 1);
        }
    }

}
