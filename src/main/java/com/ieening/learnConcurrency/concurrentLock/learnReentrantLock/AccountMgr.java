package com.ieening.learnConcurrency.concurrentLock.learnReentrantLock;

import java.util.Random;

public class AccountMgr {
    public static class NoEnoughMoneyException extends Exception {
    }

    public static void transferMayDeadLock(Account from, Account to, double money) throws NoEnoughMoneyException {
        from.lock();
        try {
            to.lock();
            try {
                if (from.getMoney() >= money) {
                    from.reduce(money);
                    to.add(money);
                } else {
                    throw new NoEnoughMoneyException();
                }
            } finally {
                to.unlock();
            }
        } finally {
            from.unlock();
        }
    }

    public static boolean tryTransfer(Account from, Account to, double money) throws NoEnoughMoneyException {
        if (from.tryLock()) {
            try {
                if (to.tryLock()) {
                    try {
                        if (from.getMoney() >= money) {
                            from.reduce(money);
                            to.add(money);
                        } else {
                            throw new NoEnoughMoneyException();
                        }
                        return true;
                    } finally {
                        to.unlock();
                    }
                }
            } finally {
                from.unlock();
            }
        }
        return false;
    }

    public static void transfer(Account from, Account to, double money) throws NoEnoughMoneyException {
        boolean success = false;
        do {
            success = tryTransfer(from, to, money);
        } while (!success);
    }

    public static void SimulateDeadLock() {
        final int accountNum = 10;
        final Account[] accounts = new Account[accountNum];
        final Random random = new Random();
        for (int i = 0; i < accountNum; i++) {
            accounts[i] = new Account(random.nextInt(10000));
        }
        int threadNum = 100;
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread() {
                public void run() {
                    int loopNum = 100;
                    for (int j = 0; j < loopNum; j++) {
                        int m = random.nextInt(accountNum);
                        int n = random.nextInt(accountNum);
                        if (m != n) {
                            int money = random.nextInt(10);
                            try {
                                AccountMgr.transferMayDeadLock(accounts[m], accounts[n], money);
                            } catch (NoEnoughMoneyException e) {
                            }
                        }
                    }
                };
            };
            threads[i].start();
        }
    }

    public static void main(String[] args) {
        SimulateDeadLock();
    }
}
