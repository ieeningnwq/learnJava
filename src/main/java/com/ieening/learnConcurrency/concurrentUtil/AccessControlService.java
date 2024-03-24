package com.ieening.learnConcurrency.concurrentUtil;

import java.util.concurrent.Semaphore;

public class AccessControlService {
    public static class ConcurrentLimitException extends RuntimeException {

    }

    private static final int Max_PERMITS = 100;
    private Semaphore permits = new Semaphore(Max_PERMITS, true);

    public boolean login(String name, String password) {
        if (!permits.tryAcquire()) {
            throw new ConcurrentLimitException();
        }
        // other verify
        return true;
    }

    public void logout(String name) {
        permits.release();
    }
}
