package com.ieening.learnConcurrency.threadBasics;

public class HelloThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " hello word");
    }

    public static void main(String[] args) throws InterruptedException {
        HelloThread thread = new HelloThread();
        thread.start();
        thread.run();
        thread.join();
    }
}
