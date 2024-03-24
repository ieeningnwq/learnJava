package com.ieening.learnConcurrency.threadNotifyWait.AsynchronousResult;

/**
 * Callable
 */
public interface Callable<T> {
    T call() throws Exception;
}