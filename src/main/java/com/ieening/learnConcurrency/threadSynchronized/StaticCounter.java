package com.ieening.learnConcurrency.threadSynchronized;

public class StaticCounter {
    private static int count = 0;

    public static synchronized void incr() {
        count++;
    }

    public static synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        StaticCounter.incr();
                    }
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(StaticCounter.getCount());
    }
}
