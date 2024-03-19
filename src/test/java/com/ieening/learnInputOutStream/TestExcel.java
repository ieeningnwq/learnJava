package com.ieening.learnInputOutStream;

import static org.junit.Assert.assertArrayEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.ieening.learnInputOutputStream.Student;

public class TestExcel {
    String fileName = "src\\main\\resources\\excel\\students.xlsx";
    List<Student> students = Arrays
            .asList(new Student[] { new Student("张三", 18, 80.9d), new Student("李四", 17, 67.5d),
                    new Student("王五", 17, 67.5d) });

    @Test
    public void testPOIWrite() throws IOException {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet("student");
            for (int i = 0; i < students.size(); i++) {
                XSSFRow row = sheet.createRow(i);
                Student student = students.get(i);
                row.createCell(0).setCellValue(student.getName());
                row.createCell(1).setCellValue(student.getAge());
                row.createCell(2).setCellValue(student.getScore());
            }
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                wb.write(fileOut);
            }
        }
    }

    @Test
    public void testPOIRead() throws IOException {
        List<Student> studentsList = new ArrayList<>();
        try (
                FileInputStream is = new FileInputStream(fileName);
                Workbook wb = new XSSFWorkbook(is)) {
            Sheet sheet = wb.getSheet("student");
            for (Row row : sheet) {
                studentsList.add(new Student(row.getCell(0).getStringCellValue(),
                        (int) row.getCell(1).getNumericCellValue(), row.getCell(2).getNumericCellValue()));
            }
        }
        assertArrayEquals(students.toArray(), studentsList.toArray());
    }
}
