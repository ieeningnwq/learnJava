package com.ieening.learnInputOutStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.ieening.learnInputOutputStream.Student;

public class TestCharsInputOutStream {
    @Test
    public void testTextBytesDifferences() {
        DataOutputStream output = null;
        FileOutputStream outputString = null;
        try {
            output = new DataOutputStream(
                    new FileOutputStream("src\\main\\resources\\testTextBytesDifferencesBytes.dat"));
            outputString = new FileOutputStream("src\\main\\resources\\testTextBytesDifferencesString.dat");
            output.writeInt(123);
            outputString.write(Integer.toString(123).getBytes("utf8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testOutputStreamWriterInputStreamReader() {
        String contet = "hello-123_你好";
        try (Writer writer = new OutputStreamWriter(
                new FileOutputStream("src\\main\\resources\\testOutputStreamWriterInputStreamReader.txt"), "GB2312")) {
            writer.write(contet);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Reader reader = new InputStreamReader(
                new FileInputStream("src\\main\\resources\\testOutputStreamWriterInputStreamReader.txt"), "GB2312")) {
            char[] cbuf = new char[1024];
            int charsReadNum = reader.read(cbuf);
            assertEquals(contet, new String(cbuf, 0, charsReadNum));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCharArrayWriterCharArrayReader() {
        String contet = "hello-123_你好";
        char[] cbuf = new char[1024];
        try (Reader reader = new InputStreamReader(
                new FileInputStream("src\\main\\resources\\testOutputStreamWriterInputStreamReader.txt"),
                "GB2312"); CharArrayWriter writer = new CharArrayWriter()) {
            int charsRead = 0;
            while ((charsRead = reader.read(cbuf)) != -1) {
                writer.write(cbuf, 0, charsRead);
            }
            assertEquals(contet, writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBufferedReaderBuufferedWriter() {
        String fileName = "src\\main\\resources\\testBufferedReaderBuufferedWriter.txt";
        List<Student> students = Arrays
                .asList(new Student[] { new Student("张三", 18, 80.9d), new Student("李四", 17, 67.5d) });
        // 写入 Students 列表
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write(student.getName() + ", " + student.getAge() + ", " + student.getScore());
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 读取
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<Student> studentsReader = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(", ");
                Student s = new Student(fields[0], Integer.parseInt(fields[1]), Double.parseDouble(fields[2]));
                studentsReader.add(s);
                line = reader.readLine();
            }
            assertArrayEquals(students.toArray(), studentsReader.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrintWriter() {
        String fileName = "src\\main\\resources\\testPrintWriter.txt";
        List<Student> students = Arrays
                .asList(new Student[] { new Student("张三", 18, 80.9d), new Student("李四", 17, 67.5d) });
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            for (Student student : students) {
                printWriter.println(student.getName() + ", " + student.getAge() + ", " + student.getScore());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testScanner() {
        String fileName = "src\\main\\resources\\testPrintWriter.txt";
        List<Student> students = Arrays
                .asList(new Student[] { new Student("张三", 18, 80.9d), new Student("李四", 17, 67.5d) });
        List<Student> studentsList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                Student s = new Student();
                try (Scanner scanner = new Scanner(line).useDelimiter(", ");) {
                    s.setName(scanner.next());
                    s.setAge(scanner.nextInt());
                    s.setScore(scanner.nextDouble());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                studentsList.add(s);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertArrayEquals(students.toArray(), studentsList.toArray());
    }
}
