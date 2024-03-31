package com.ieening.learnCombinationAsynchronous;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureSingleTaskFlowThenComposeDemo {
    public static void main(String[] args) {
        Supplier<String> taskA = () -> "hello";
        Function<String, CompletableFuture<String>> taskB = (t) -> CompletableFuture.supplyAsync(() -> t.toUpperCase());
        Consumer<String> taskC = (t) -> System.out.println("consume: " + t);
        CompletableFuture.supplyAsync(taskA).thenCompose(taskB).thenAccept(taskC).join();
    }
}
