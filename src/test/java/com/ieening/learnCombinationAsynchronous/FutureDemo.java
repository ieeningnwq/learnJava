package com.ieening.learnCombinationAsynchronous;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
    private static Random rnd = new Random();
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    static int delayRandom(int min, int max) {
        int millis = max > min ? rnd.nextInt(max - min) : 0;
        try {
            Thread.sleep(min + millis);
        } catch (InterruptedException e) {
        }
        return millis;
    }

    static Callable<Integer> externalTask = () -> {
        int time = delayRandom(20, 2000);
        return time;
    };

    public static Future<Integer> callExternalService() {
        return executor.submit(externalTask);
    }

    public static void master() {
        // 执行异步任务
        Future<Integer> asyncRet = callExternalService();
        // 执行其他任务......
        // 获取异步任务的结果，处理可能的异常
        try {
            Integer ret = asyncRet.get();
            System.out.println(ret);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    public static void main(String[] args) {
        master();
    }
}
