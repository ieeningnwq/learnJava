package com.ieening.learnDynamicFunctional;

public class SimpleMapperDemo {
    static class Student {
        String name;
        int age;
        Double score;

        /**
         * 
         */
        public Student() {
        }

        /**
         * @param name
         * @param age
         * @param score
         */
        public Student(String name, int age, Double score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Student [name=" + name + ", age=" + age + ", score=" + score + "]";
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
         * @return the age
         */
        public int getAge() {
            return age;
        }

        /**
         * @param age the age to set
         */
        public void setAge(int age) {
            this.age = age;
        }

        /**
         * @return the score
         */
        public Double getScore() {
            return score;
        }

        /**
         * @param score the score to set
         */
        public void setScore(Double score) {
            this.score = score;
        }

    }

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        Student zhangsan = new Student("张三", 18, 89d);
        String str = SimpleMapper.toString(zhangsan);
        Student zhangsan2 = (Student) SimpleMapper.fromString(str);
        System.out.println(zhangsan2);
    }
}
