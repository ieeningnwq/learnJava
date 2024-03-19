package com.ieening.learnSerializable;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class TestSerializableInterface {
    List<Student> students = Arrays
            .asList(new Student[] { new Student("张三", 18, 80.9d), new Student("李四", 17, 67.5d),
                    new Student("王五", 17, 67.5d), new Student("赵六", 17, 67.5d) });

    String studentsDataFileName = "src\\main\\resources\\serializable\\students.dat";

    @Test
    public void testWriteStudents() throws FileNotFoundException, IOException {
        long startTime = new Date().getTime();

        File file = new File(studentsDataFileName);
        if (file.exists()) {
            file.delete();
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(studentsDataFileName)))) {
            outputStream.writeInt(students.size());
            for (Student student : students) {
                outputStream.writeObject(student);
            }
        }
        File f = new File(studentsDataFileName);
        assertTrue(f.lastModified() > startTime);
    }

    @Test
    public void testReadStudents() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Student> list = null;
        try (ObjectInputStream oInputStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(studentsDataFileName)));) {
            int size = oInputStream.readInt();
            list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add((Student) oInputStream.readObject());
            }
        }
        assertArrayEquals(students.toArray(), list.toArray());
    }

    @Test
    public void testWriteStudentsList() throws FileNotFoundException, IOException {
        long startTime = new Date().getTime();

        File file = new File(studentsDataFileName);
        if (file.exists()) {
            file.delete();
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(studentsDataFileName)))) {
            outputStream.writeObject(students);
        }
        File f = new File(studentsDataFileName);
        assertTrue(f.lastModified() > startTime);
    }

    @Test
    public void testReadStudentsList() throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Student> list = null;
        try (ObjectInputStream oInputStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(studentsDataFileName)));) {
            list = (List<Student>) oInputStream.readObject();
        }
        assertArrayEquals(students.toArray(), list.toArray());
    }
}
