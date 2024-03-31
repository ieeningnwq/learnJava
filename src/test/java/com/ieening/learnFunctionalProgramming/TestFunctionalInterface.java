package com.ieening.learnFunctionalProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Test;

/**
 * TestFunctionalInterface
 */
public class TestFunctionalInterface {

    public static <E> List<E> filter(List<E> list, Predicate<E> pred) {
        List<E> retList = new ArrayList<>();
        for (E e : list) {
            if (pred.test(e)) {
                retList.add(e);
            }
        }
        return retList;
    }

    @Test
    public void testPredicateInterface() {
        List<Student> students = Arrays.asList(
                new Student[] { new Student("zhangsan", 89d,"一年级"), new Student("lisi", 89d,"一年级"), new Student("wangwu", 98d,"一年级") });
        students = filter(students, t -> t.getScore() > 90);
        System.out.println(students.toString());
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        List<R> retList = new ArrayList<>(list.size());
        for (T e : list) {
            retList.add(mapper.apply(e));
        }
        return retList;
    }

    @Test
    public void testFunctionInterface() {
        List<Student> students = Arrays.asList(
                new Student[] { new Student("zhangsan", 89d,"一年级"), new Student("lisi", 89d,"一年级"), new Student("wangwu", 98d,"一年级") });
        List<String> names = map(students, t -> t.getName());
        System.out.println(names);
        students = map(students, t -> new Student(t.getName().toUpperCase(), t.getScore(),t.getGrade()));
        System.out.println(students);
    }

    public static <E> void foreach(List<E> list, Consumer<E> consumer) {
        for (E e : list) {
            consumer.accept(e);
        }
    }

    @Test
    public void testConsumerInterface() {
        List<Student> students = Arrays.asList(
                new Student[] { new Student("zhangsan", 89d,"一年级"), new Student("lisi", 89d,"一年级"), new Student("wangwu", 98d,"一年级") });
        foreach(students, t -> t.setName(t.getName().toUpperCase()));
        System.out.println(students);
    }
}