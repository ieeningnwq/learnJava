package com.ieening.learnConcurrency.concurrentLock.learnReentrantLock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            public void run() {
                LockSupport.park(); // 放弃 CPU
                System.out.println("exit");
            };
        };
        t.start();
        System.out.println("main sleeped");
        Thread.sleep(1000);
        LockSupport.unpark(t);
    }
}
