package com.ieening.learnGenricConatiner;

public class PairTwoGenric<U, V> {
    private U first;
    private V second;

    public PairTwoGenric(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public static void main(String[] args) {

    }
}
