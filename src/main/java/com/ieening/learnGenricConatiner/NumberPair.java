package com.ieening.learnGenricConatiner;

public class NumberPair<U extends Number, V extends Number> extends PairTwoGenric<U, V> {
    public NumberPair(U first, V second) {
        super(first, second);
    }

    public double sum() {
        return getFirst().doubleValue() + getSecond().doubleValue();
    }
}
