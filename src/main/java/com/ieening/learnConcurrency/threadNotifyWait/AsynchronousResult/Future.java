package com.ieening.learnConcurrency.threadNotifyWait.AsynchronousResult;

public interface Future<T> {
    T get() throws Exception;
}
