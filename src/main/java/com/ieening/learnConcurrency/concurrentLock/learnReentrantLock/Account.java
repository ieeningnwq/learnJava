package com.ieening.learnConcurrency.concurrentLock.learnReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private Lock lock = new ReentrantLock();
    private volatile double money;

    public Account(double initialMoney) {
        this.money = money;
    }

    public void add(double money) {
        lock();
        try {
            this.money += money;
        } finally {
            unlock();
        }
    }

    public void reduce(double money) {
        lock();
        try {
            this.money -= money;
        } finally {
            unlock();
        }
    }

    public double getMoney() {
        return money;
    }

    void lock() {
        lock.lock();
    }

    void unlock() {
        lock.unlock();
    }

    boolean tryLock() {
        return lock.tryLock();
    }
}
