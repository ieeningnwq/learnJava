package com.ieening.learnCombinationAsynchronous;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class CompletableFutureTwoStageFlowDemo {
    public static void main(String[] args) {
        Supplier<String> taskA = () -> "taskA";
        CompletableFuture<String> taskB = CompletableFuture.supplyAsync(() -> "taskB");
        BiFunction<String, String, String> taskC = (a, b) -> a + ", " + b;
        String ret = CompletableFuture.supplyAsync(taskA)
                .thenCombineAsync(taskB, taskC).join();
        System.out.println(ret);
    }
}
