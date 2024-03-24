package com.ieening.learnConcurrency.threadNotifyWait.EndSimultaneously;

public class Worker extends Thread {
    Latch latch;
    String name;

    public Worker(Latch latch, String name) {
        this.latch = latch;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 10000));
        } catch (Exception e) {
        }
        System.out.println(name + " worked done");
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        int workerNum = 10;
        Latch latch = new Latch(workerNum);
        Worker[] workers = new Worker[workerNum];
        for (int i = 0; i < workerNum; i++) {
            workers[i] = new Worker(latch, String.valueOf(i));
            workers[i].start();
        }
        latch.await();
        System.out.println("all workers worked done");
    }

}
