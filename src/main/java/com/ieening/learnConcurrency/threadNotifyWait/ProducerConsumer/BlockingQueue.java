package com.ieening.learnConcurrency.threadNotifyWait.ProducerConsumer;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

public class BlockingQueue<E> {
    private Queue<E> queue = null;
    private int MAX_SIZE = 5;

    public BlockingQueue(int size) {
        MAX_SIZE = size;
        queue = new ArrayDeque<>();
    }

    public synchronized void put(E e) throws InterruptedException, TimeoutException {
        while (queue.size() == MAX_SIZE) {
            wait(1000);
            throw new TimeoutException("full queue timeout");
        }
        queue.add(e);
        System.out.println("produce task: " + e + " queue:" + queue.toString());
        notifyAll();
    }

    public synchronized E take() throws InterruptedException, TimeoutException {
        while (queue.isEmpty()) {
            wait(1000);
            throw new TimeoutException("empty queue timeout");
        }
        E e = queue.poll();
        System.out.println("handle task: " + e + " queue:" + queue.toString());
        notifyAll();
        return e;
    }

    public static void main(String[] args) {
        BlockingQueue<String> queue = new BlockingQueue<>(5);
        new Producer(queue).start();
        new Consumer(queue).start();
    }
}
