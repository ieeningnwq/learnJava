package com.ieening.learnCombinationAsynchronous;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureMultiStageFlowDemo {

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

    public static void main(String[] args) {
        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
            delayRandom(100, 1000);
            return "helloA";
        }, executor);
        CompletableFuture<Void> taskB = CompletableFuture.runAsync(() -> {
            delayRandom(2000, 3000);
        }, executor);
        CompletableFuture<Void> taskC = CompletableFuture.runAsync(() -> {
            delayRandom(30, 100);
            throw new RuntimeException("task C exception");
        }, executor);
        CompletableFuture.allOf(taskA, taskB, taskC).whenComplete((result, ex) -> {
            if(ex != null) {
                System.out.println(ex.getMessage());
            }
            if(! taskA.isCompletedExceptionally()) {
                System.out.println("task A " + taskA.join());
            }
        });
    }
}
