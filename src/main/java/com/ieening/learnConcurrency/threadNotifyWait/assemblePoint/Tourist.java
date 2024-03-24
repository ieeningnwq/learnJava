package com.ieening.learnConcurrency.threadNotifyWait.assemblePoint;

public class Tourist extends Thread {
    AssemblePoint point;
    String name;

    public Tourist(AssemblePoint point, String name) {
        this.point = point;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            // 线程独立运行代码
            System.out.println(name + "begin to work");
            Thread.sleep((int) (Math.random() * 1000));
            // 集合
            System.out.println(name + "work done. Arrived");
            point.await();
            // 集合后线程运行的代码
            System.out.println(name + " work after all arrived");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        int num = 10;
        Tourist[] threads = new Tourist[num];
        AssemblePoint point = new AssemblePoint(num);
        for (int i = 0; i < num; i++) {
            threads[i] = new Tourist(point, String.valueOf(i));
            threads[i].start();
        }
    }
}
