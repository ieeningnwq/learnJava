package com.ieening.learnConcurrency.threadNotifyWait.StartSimultaneously;

public class FireFlag {
    private volatile boolean fired = false;

    public synchronized void waitForFire() throws InterruptedException {
        while (!fired) {
            wait();
        }
    }

    public synchronized void fire() {
        fired = true;
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        FireFlag fireFlag = new FireFlag();
        Thread[] racers = new Racer[] { new Racer("朱七", fireFlag), new Racer("王二", fireFlag), new Racer("沈三", fireFlag),
                new Racer("赵四", fireFlag) };
        for (Thread racer : racers) {
            racer.start();
        }
        System.out.println("Racers ready to fire");
        Thread.sleep(2000);
        System.out.println("Racers start to fire");
        fireFlag.fire();
    }
}
