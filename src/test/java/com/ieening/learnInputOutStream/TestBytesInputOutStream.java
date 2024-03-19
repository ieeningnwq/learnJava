package com.ieening.learnInputOutStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

import com.ieening.learnInputOutputStream.Student;

public class TestBytesInputOutStream {
    private String fileName;
    private String content;
    OutputStream output;
    InputStream input;

    @Before
    public void setUp() {
        fileName = "src\\main\\resources\\testFileOutputStreamWrite.txt";
        content = "hello-你好";
    }

    @Test
    public void testFileInputOutputStreamWriteRead() {
        try {
            output = new FileOutputStream(fileName);
            output.write(content.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!Objects.isNull(output)) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            input = new FileInputStream(fileName);
            byte[] buf = new byte[1024];
            int bytesRead = input.read(buf);
            String data = new String(buf, 0, bytesRead, "UTF-8");
            assertEquals(content, data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!Objects.isNull(input)) {

                }
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testByteArrayOutputStream() {
        try {
            input = new FileInputStream(fileName);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, bytesRead);
            }
            String data = output.toString("UTF-8");
            assertEquals(content, data);
            ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testByteArrayInputStream() {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] bytes = content.getBytes("UTF-8");
            input = new ByteArrayInputStream(bytes);
            input.transferTo(output);
            assertEquals(content, output.toString("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null)
                    input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testDataInputOutputStreamStudent() {
        List<Student> students = Arrays
                .asList(new Student[] { new Student("张三", 18, 80.9d), new Student("李四", 17, 67.5d) });
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream("src\\main\\resources\\students.dat"));
            dataOutputStream.writeInt(students.size());
            for (Student student : students) {
                dataOutputStream.writeUTF(student.getName());
                dataOutputStream.writeInt(student.getAge());
                dataOutputStream.writeDouble(student.getScore());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DataInputStream dataInputStream = null;
        List<Student> studentsArray = null;
        try {
            dataInputStream = new DataInputStream(
                    new FileInputStream("src\\main\\resources\\students.dat"));
            int size = dataInputStream.readInt();
            studentsArray = new ArrayList<Student>(size);
            for (int i = 0; i < size; i++) {
                Student s = new Student();
                s.setName(dataInputStream.readUTF());
                s.setAge(dataInputStream.readInt());
                s.setScore(dataInputStream.readDouble());
                studentsArray.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assertArrayEquals(students.toArray(), studentsArray.toArray());
    }

}
