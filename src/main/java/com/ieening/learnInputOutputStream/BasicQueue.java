package com.ieening.learnInputOutputStream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;

public class BasicQueue {
    private static final int MAX_MSG_NUM = 1020 * 1024; // 队列最多消息个数，实际个数还会减 1
    private static final int MAX_MSG_BODY_SIZE = 1020; // 消息体最大长度
    private static final int MSG_SIZE = MAX_MSG_BODY_SIZE + 4; // 每条消息占用的空间
    private static final int DATA_FILE_SIZE = MAX_MSG_NUM * MSG_SIZE; // 队列消息体数据文件大小
    private static final int META_SIZE = 8; // 队列元数据文件大小（head + tail）
    // 数据文件扩展名
    private static final String DATA_SUFFIX = ".data";
    // 元数据文件扩展名，包括索引和空白空间数据
    private static final String META_SUFFIX = ".meta";

    private MappedByteBuffer dataByteBuffer;
    private MappedByteBuffer metaByteBuffer;

    public BasicQueue(String path, String queueName) throws IOException {
        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }
        try (
                RandomAccessFile dataAccessFile = new RandomAccessFile(path + queueName + DATA_SUFFIX, "rw");
                RandomAccessFile metaAccessFile = new RandomAccessFile(path + queueName + META_SUFFIX, "rw")) {
            dataByteBuffer = dataAccessFile.getChannel().map(MapMode.READ_WRITE, 0, DATA_FILE_SIZE);
            metaByteBuffer = metaAccessFile.getChannel().map(MapMode.READ_WRITE, 0, META_SIZE);
        }
    }

    // deque assistant methods

    /**
     * get queue head
     * 
     * @return
     */
    private int head() {
        return metaByteBuffer.getInt(0);
    }

    /**
     * set queue head
     * 
     * @param newHead new queue head position
     */
    private void head(int newHead) {
        metaByteBuffer.putInt(0, newHead);
    }

    /**
     * get queue tail
     * 
     * @return
     */
    private int tail() {
        return metaByteBuffer.getInt(4);
    }

    /**
     * set queue tail
     * 
     * @param newTail new queue tail position
     */
    private void tail(int newTail) {
        metaByteBuffer.putInt(newTail);
    }

    /**
     * queue is empty
     * 
     * @return
     */
    private boolean isEmpty() {
        return head() == tail();
    }

    /**
     * queue is full
     * 
     * @return
     */
    private boolean isFull() {
        return (tail() + MSG_SIZE % DATA_FILE_SIZE) == head();
    }

    /**
     * data enqueue
     * 
     * @param data to enqueue data
     */
    public void enqueue(byte[] data) {
        if (data.length > MAX_MSG_BODY_SIZE) {
            throw new IllegalArgumentException(
                    "msg size is " + data.length + " , while maximum allowed length is " + MAX_MSG_BODY_SIZE);
        }
        if (isFull()) {
            throw new IllegalStateException("queue is full");
        }
        int tail = tail();
        dataByteBuffer.position(tail);
        dataByteBuffer.putInt(data.length);
        dataByteBuffer.put(data);
        if (tail + MSG_SIZE > +DATA_FILE_SIZE) {
            tail(0);
        } else {
            tail(tail + MSG_SIZE);
        }
    }

    /**
     * to dequeue data
     * 
     * @return data dequeued
     */
    public byte[] dequeue() {
        if (isEmpty()) {
            return null;
        }
        int head = head();
        dataByteBuffer.position(head);
        int length = dataByteBuffer.getInt();
        byte[] data = new byte[length];
        dataByteBuffer.get(data);
        if (head + MSG_SIZE >= DATA_FILE_SIZE) {
            head(0);
        } else {
            head(head + MSG_SIZE);
        }
        return data;
    }
}
