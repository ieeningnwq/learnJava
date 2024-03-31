package com.ieening.learnCombinationAsynchronous;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureSingleTaskFlowThenRunDemo {
    public static void main(String[] args) {
        Runnable taskA = () -> System.out.println("task A");
        Runnable taskB = () -> System.out.println("task B");
        Runnable taskC = () -> System.out.println("task C");
        CompletableFuture.runAsync(taskA).thenRun(taskB).thenRun(taskC).join();
    }
}
