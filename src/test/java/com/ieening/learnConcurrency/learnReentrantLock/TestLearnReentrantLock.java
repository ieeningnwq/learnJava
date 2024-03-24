package com.ieening.learnConcurrency.learnReentrantLock;

import java.util.Random;

import org.junit.Test;

import com.ieening.learnConcurrency.concurrentLock.learnReentrantLock.Account;
import com.ieening.learnConcurrency.concurrentLock.learnReentrantLock.AccountMgr;
import com.ieening.learnConcurrency.concurrentLock.learnReentrantLock.AccountMgr.NoEnoughMoneyException;

public class TestLearnReentrantLock {
    @Test
    public void testAccountMgrTransfer() {
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
                                AccountMgr.transfer(accounts[m], accounts[n], money);
                            } catch (NoEnoughMoneyException e) {
                            }
                        }
                    }
                };
            };
            threads[i].start();
        }

    }
}
