package com.ieening.learnConcurrency.threadBasics;

import java.util.ArrayList;
import java.util.List;

public class ShareMemoryDemo {
    private static int shared = 0;

    private static void incrShared() {
        shared++;
    }

    static class InnerShareMemoryDemo extends Thread {
        List<String> list;

        public InnerShareMemoryDemo(List<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            incrShared();
            list.add(Thread.currentThread().getName());
        }

    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        Thread iDemo1 = new InnerShareMemoryDemo(list);
        Thread iDemo2 = new InnerShareMemoryDemo(list);
        iDemo1.start();
        iDemo2.start();
        iDemo1.join();
        iDemo2.join();
        System.out.println(shared);
        System.out.println(list);

    }
}
