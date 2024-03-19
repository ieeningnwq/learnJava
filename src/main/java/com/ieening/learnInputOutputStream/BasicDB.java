package com.ieening.learnInputOutputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class BasicDB implements AutoCloseable {
    private static final int MAX_DATA_LENGTH = 1020;
    // 补白字节
    private static final byte[] ZERO_BYTES = new byte[MAX_DATA_LENGTH];
    // 数据文件扩展名
    private static final String DATA_SUFFIX = ".data";
    // 元数据文件扩展名，包括索引和空白空间数据
    private static final String META_SUFFIX = ".meta";

    Map<String, Long> indexMap; // 索引信息，键->值 在 .data文件中的位置
    Queue<Long> gaps; // 空白空间，值为在 .data文件中的位置

    RandomAccessFile db; // 值数据文件
    File metaFile; // 元数据文件

    public BasicDB(String path, String name) throws IOException {
        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }
        File dataFile = new File(path + name + DATA_SUFFIX);
        metaFile = new File(path + name + META_SUFFIX);
        db = new RandomAccessFile(dataFile, "rw");
        if (metaFile.exists()) {
            loadMeta();
        } else {
            indexMap = new HashMap<>();
            gaps = new ArrayDeque<>();
        }
    }

    private void loadMeta() throws IOException {
        try (DataInputStream dInputStream = new DataInputStream(
                new BufferedInputStream(new FileInputStream(metaFile)))) {
            loadIndex(dInputStream);
            loadGaps(dInputStream);
        }
    }

    private void loadGaps(DataInputStream dInputStream) throws IOException {
        int size = dInputStream.readInt();
        gaps = new ArrayDeque<>(size);
        for (int i = 0; i < size; i++) {
            long index = dInputStream.readLong();
            gaps.add(index);
        }
    }

    private void loadIndex(DataInputStream dInputStream) throws IOException {
        int size = dInputStream.readInt();
        indexMap = new HashMap<>((int) (size / 0.75f) + 1, 0.75f);
        for (int i = 0; i < size; i++) {
            String key = dInputStream.readUTF();
            long index = dInputStream.readLong();
            indexMap.put(key, index);
        }
    }

    public void put(String key, byte[] value) throws IOException {
        Long index = indexMap.get(key);
        if (index == null) {
            index = nextAvailablePos();
            indexMap.put(key, index);
        }
        writeData(index, value);
    }

    private void writeData(Long pos, byte[] data) throws IOException {
        if (data.length > MAX_DATA_LENGTH) {
            throw new IllegalArgumentException(
                    "maximum allowed length is " + MAX_DATA_LENGTH + ", data length is " + data.length);
        }
        db.seek(pos);
        db.write(data.length);
        db.write(data);
        db.write(ZERO_BYTES, 0, MAX_DATA_LENGTH - data.length);
    }

    private Long nextAvailablePos() throws IOException {
        if (gaps.isEmpty()) {
            return db.length();
        } else {
            return gaps.poll();
        }
    }

    public byte[] get(String key) throws IOException {
        Long index = indexMap.get(key);
        if (index != null) {
            return getData(index);
        }
        return null;
    }

    private byte[] getData(long pos) throws IOException {
        db.seek(pos);
        int length = db.readInt();
        byte[] data = new byte[length];
        db.readFully(data);
        return data;
    }

    public long remove(String key) {
        Long index = indexMap.remove(key);
        if (index != null) {
            gaps.offer(index);
        }
        return index;
    }

    public void flush() throws IOException {
        saveMeta();
        db.getFD().sync();
    }

    private void saveMeta() throws IOException {
        try (DataOutputStream dOutputStream = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(metaFile)))) {
            saveIndex(dOutputStream);
            saveGaps(dOutputStream);
        }
    }

    private void saveGaps(DataOutputStream dOutputStream) throws IOException {
        dOutputStream.writeInt(gaps.size());
        for (Long pos : gaps) {
            dOutputStream.writeLong(pos);
        }
    }

    private void saveIndex(DataOutputStream dOutputStream) throws IOException {
        dOutputStream.writeInt(indexMap.size());
        for (Map.Entry<String, Long> entry : indexMap.entrySet()) {
            dOutputStream.writeUTF(entry.getKey());
            dOutputStream.writeLong(entry.getValue());
        }
    }

    @Override
    public void close() throws Exception {
        flush();
        db.close();
    }
}
