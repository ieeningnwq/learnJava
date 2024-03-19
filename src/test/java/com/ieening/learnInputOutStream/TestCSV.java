package com.ieening.learnInputOutStream;

import static org.junit.Assert.assertArrayEquals;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import com.ieening.learnInputOutputStream.Student;

public class TestCSV {
    String fileName = "src\\main\\resources\\csv\\students.csv";
    List<Student> students = Arrays
            .asList(new Student[] { new Student("张三", 18, 80.9d), new Student("李四", 17, 67.5d),
                    new Student("王五", 17, 67.5d) });
    CSVFormat format = CSVFormat.DEFAULT.withHeader("name", "age", "score");

    @Test
    public void testCsvWriteRead() {
        try (FileWriter writer = new FileWriter(fileName);
                CSVPrinter printer = new CSVPrinter(writer, format)) {
            for (Student student : students) {
                printer.printRecord(student.getName(), student.getAge(), student.getScore());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCsvRead() {
        ArrayList<Student> studentList = new ArrayList<>();
        try (Reader reader = new FileReader(fileName)) {
            for (CSVRecord record : format.withFirstRecordAsHeader().parse(reader)) {
                studentList.add(new Student(record.get("name"), Integer.parseInt(record.get("age")),
                        Double.parseDouble(record.get("score"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertArrayEquals(students.toArray(), studentList.toArray());
    }
}
