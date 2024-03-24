package com.ieening.learnConcurrency.threadInterrupt;

public class InterruptRunnableDemo extends Thread {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // 单次循环代码
        }
        System.out.println("done");
    }

    // 其它代码

}
