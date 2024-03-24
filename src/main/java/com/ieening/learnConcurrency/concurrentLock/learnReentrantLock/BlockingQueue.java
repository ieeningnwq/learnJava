package com.ieening.learnConcurrency.concurrentLock.learnReentrantLock;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<E> {
    private Queue<E> queue = null;
    private int MAX_SIZE;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public BlockingQueue(int size) {
        this.MAX_SIZE = size;
        queue = new ArrayDeque<>(MAX_SIZE);
    }

    public void put(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (queue.size() == MAX_SIZE) {
                notFull.await();
            }
            queue.add(e);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            E e = queue.poll();
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }
}
