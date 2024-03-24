package com.ieening.learnConcurrency.concurrentUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    static class Tourist extends Thread {
        CyclicBarrier barrier;

        public Tourist(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                // simulate single thread run independently
                Thread.sleep((int) Math.random() * 1000);
                // assemble point A
                barrier.await();
                System.out.println(getName() + " arrive A " + System.currentTimeMillis());
                // simulate work after assembling at A
                Thread.sleep((int) Math.random() * 1000);
                // assemble point B
                barrier.await();
                System.out.println(getName() + " arrive B " + System.currentTimeMillis());

            } catch (BrokenBarrierException | InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) {
        int num = 3;
        Tourist[] tourists = new Tourist[num];
        CyclicBarrier barrier = new CyclicBarrier(num, new Runnable() {

            @Override
            public void run() {
                System.out.println("all arrived " + System.currentTimeMillis() + " executed by "
                        + Thread.currentThread().getName());
            }
        });

        for (int i = 0; i < num; i++) {
            tourists[i] = new Tourist(barrier);
            tourists[i].start();
        }
    }
}

