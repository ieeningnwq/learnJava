package com.ieening.learnConcurrency.threadNotifyWait.ProducerConsumer;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class Producer extends Thread {
    BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Instant endTime = Instant.now().plusSeconds(1);
        while (Instant.now().isBefore(endTime)) {
            try {
                String task = sdf.format(Date.from(Instant.now()));
                queue.put(task);
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException | TimeoutException e) {
            }
        }
        System.out.println("exit produce task");
    }

}
