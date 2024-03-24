package com.ieening.learnConcurrency.threadNotifyWait.StartSimultaneously;

public class Racer extends Thread {
    FireFlag fireFlag;
    String name;

    public Racer(String name, FireFlag fireFlag) {
        this.fireFlag = fireFlag;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            fireFlag.waitForFire();
            System.out.println(name + " start run...");
        } catch (InterruptedException e) {
        }
    }

}
