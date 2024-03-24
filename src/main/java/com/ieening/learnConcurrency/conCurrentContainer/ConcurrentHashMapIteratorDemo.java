package com.ieening.learnConcurrency.conCurrentContainer;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapIteratorDemo {
    public static void testWeakAgreement() {
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("a", "abstract");
        map.put("c", "call");
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (Entry<String, String> entry : map.entrySet()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                    System.out.println(entry.getKey() + ", " + entry.getValue());
                }
            }
        };
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
        map.put("b", "bug");

    }

    public static void main(String[] args) {
        testWeakAgreement();
    }
}
