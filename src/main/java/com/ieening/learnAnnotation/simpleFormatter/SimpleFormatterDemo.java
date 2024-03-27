package com.ieening.learnAnnotation.simpleFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleFormatterDemo {
    static class Student {
        @Label("姓名")
        String name;
        @Label("出生日期")
        @Format(pattern = "yyyy/MM/dd")
        Date born;
        @Label("分数")
        double score;

        /**
         * @param name
         * @param born
         * @param score
         */
        public Student(String name, Date born, double score) {
            this.name = name;
            this.born = born;
            this.score = score;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the born
         */
        public Date getBorn() {
            return born;
        }

        /**
         * @param born the born to set
         */
        public void setBorn(Date born) {
            this.born = born;
        }

        /**
         * @return the score
         */
        public double getScore() {
            return score;
        }

        /**
         * @param score the score to set
         */
        public void setScore(double score) {
            this.score = score;
        }
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Student zhangsan = new Student("张三", sdf.parse("1990-12-12"), 80.9d);
        System.out.println(SimpleFormatter.format(zhangsan));
    }
}
