package com.ieening.learnConcurrency.threadNotifyWait.ProducerConsumer;

import java.time.Instant;
import java.util.concurrent.TimeoutException;

public class Consumer extends Thread {
    BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Instant endTime = Instant.now().plusSeconds(1);

        while (Instant.now().isBefore(endTime)) {
            try {
                queue.take();
                Thread.sleep((int) Math.random() * 100);
            } catch (InterruptedException | TimeoutException e) {
            }
        }
        System.out.println("exit handle task");
    }
}
