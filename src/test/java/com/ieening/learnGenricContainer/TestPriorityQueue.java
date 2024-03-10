package com.ieening.learnGenricContainer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Test;

import com.ieening.learnGenricConatiner.Task;

public class TestPriorityQueue {
    @Test
    public void testPriorityQueueDeleteOrderAscend() {
        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(10);
        pq.add(22);
        pq.addAll(Arrays.asList(new Integer[] {
                11, 12, 34, 2, 7, 4, 15, 12, 8, 6, 19, 13 }));
        int[] intArray = new int[pq.size()];
        int arrayIndex = 0;
        while (pq.peek() != null) {
            intArray[arrayIndex++] = pq.poll();
        }
        assertArrayEquals(new int[] { 2, 4, 6, 7, 8, 10, 11, 12, 12, 13, 15, 19, 22, 34 }, intArray);
    }

    @Test
    public void testPriorityQueueDeleteOrderdescend() {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(10);
        pq.add(22);
        pq.addAll(Arrays.asList(new Integer[] {
                11, 12, 34, 2, 7, 4, 15, 12, 8, 6, 19, 13 }));
        int[] intArray = new int[pq.size()];
        int arrayIndex = 0;
        while (pq.peek() != null) {
            intArray[arrayIndex++] = pq.poll();
        }
        assertArrayEquals(new int[] { 34, 22, 19, 15, 13, 12, 12, 11, 10, 8, 7, 6, 4, 2 }, intArray);
    }

    @Test
    public void testPriorityQueueTask() {
        Queue<Task> tasks = new PriorityQueue<Task>(11, Task.taskComparator);
        tasks.offer(new Task(20, "写日记"));
        tasks.offer(new Task(10, "看电视"));
        tasks.offer(new Task(100, "写代码"));
        String[] taskStringExpectedArray = new String[] { "Task [priority=100, name=写代码]",
                "Task [priority=20, name=写日记]",
                "Task [priority=10, name=看电视]" };
        String[] taskStringActualArray = new String[tasks.size()];
        int arrayIndex = 0;
        Task task;
        while ((task = tasks.poll()) != null) {
            taskStringActualArray[arrayIndex++] = task.toString();
        }
        assertArrayEquals(taskStringExpectedArray, taskStringActualArray);
    }

    @Test
    public void testPriorityQueueOffer() {
        Queue<Integer> queue = new PriorityQueue<>(Arrays.asList(21, 6, 9, 10, 8, 13, 17, 12, 14, 4, 16));
        queue.offer(3);
        assertEquals(12, queue.size());
    }

    @Test
    public void TestPriorityQueueInitFromCollection() {
        Queue<Integer> queue = new PriorityQueue<>(Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1));
        assertEquals(Integer.valueOf(1), queue.poll());
    }

    @Test
    public void testPriorityQueuePoll() {
        Queue<Integer> queue = new PriorityQueue<>(Arrays.asList(21, 6, 9, 10, 8, 13, 17, 12, 14, 4, 16));
        int polledNum = queue.poll();
        assertEquals(4, polledNum);
    }
}
