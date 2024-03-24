package com.ieening.learnConcurrency.concurrentTimer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerFixedDelay {
    static class LongRunningTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            System.out.println("long running finished");
        }

    }

    static class FixedDelayTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
            ;
        }

    }

    public static void main(String[] args) {
        ScheduledExecutorService timerExecutorService = Executors.newScheduledThreadPool(10);
        timerExecutorService.schedule(new LongRunningTask(), 10, TimeUnit.MILLISECONDS);
        timerExecutorService.scheduleWithFixedDelay(new FixedDelayTask(), 100, 1000, TimeUnit.MILLISECONDS);
    }
}
