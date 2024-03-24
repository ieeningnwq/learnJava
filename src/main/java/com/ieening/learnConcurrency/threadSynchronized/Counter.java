package com.ieening.learnConcurrency.threadSynchronized;

public class Counter {
    private int count;

    public synchronized int getCount() {
        return count;
    }

    public synchronized void incr() {
        count++;
    }
}
