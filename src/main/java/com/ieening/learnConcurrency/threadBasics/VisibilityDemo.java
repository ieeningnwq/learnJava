package com.ieening.learnConcurrency.threadBasics;

public class VisibilityDemo {
    private static boolean shutdown = false;

    static class InnerVisibilityDemo extends Thread {
        @Override
        public void run() {
            while (!shutdown) {
            }
            System.out.println("exit InnerVisibilityDemo");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new InnerVisibilityDemo().start();
        Thread.sleep(1000);
        shutdown = true;
        Thread.sleep(1000);
        System.out.println("exit main");
    }
}
