package com.ieening.learnConcurrency.threadSynchronized;

public class DeadLockDemo {
    private static Object lockA = new Object();
    private static Object lockB = new Object();

    private static Thread startThreadA() {
        Thread aThread = new Thread() {
            @Override
            public void run() {
                synchronized (lockA) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                    synchronized (lockB) {
                    }
                }
            }
        };
        aThread.start();
        return aThread;
    }

    private static Thread startThreadB() {
        Thread bThread = new Thread() {
            @Override
            public void run() {
                synchronized (lockB) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                    synchronized (lockA) {
                    }
                }
            }
        };
        bThread.start();
        return bThread;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread aThread = startThreadA();
        Thread bThread = startThreadB();
        aThread.join();
        bThread.join();
    }
}
