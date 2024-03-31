package com.ieening.learnFunctionalProgramming;

import java.util.Objects;

public class Student {
    String name;
    double score;
    String grade;

    /**
     * @param name
     * @param score
     * @param grade
     */
    public Student(String name, double score, String grade) {
        this.name = name;
        this.score = score;
        this.grade = grade;
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

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, grade);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Student))
            return false;
        Student other = (Student) obj;
        return Objects.equals(name, other.name)
                && Double.doubleToLongBits(score) == Double.doubleToLongBits(other.score)
                && Objects.equals(grade, other.grade);
    }



    @Override
    public String toString() {
        return "Student [name=" + name + ", score=" + score + ", grade=" + grade + "]";
    }

    

}
