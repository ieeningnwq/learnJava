package com.ieening.learnFunctionalProgramming;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class TestFunctionalDataProcessing {
    private List<Student> students;

    @Before
    public void setUp() {
        students = Arrays.asList(
                new Student[] { new Student("zhangsan", 89d, "一年级"), new Student("lisi", 89d, "二年级"),
                        new Student("wangwu", 98d, "一年级"),
                        new Student("zhuyi", 100d, "二年级"), new Student("zhaoer", 80d, "三年级"),
                        new Student("shenliu", 99d, "二年级"),
                        new Student("xiaoqi", 59, "一年级"), new Student("xiaoxiao", 59, "二年级") });
    }

    @Test
    public void testBasicFilter() {
        List<Student> above90ListNormal = new ArrayList<>();
        for (Student student : students) {
            if (student.getScore() > 90) {
                above90ListNormal.add(student);
            }
        }

        List<Student> above90ListStream = students.stream().filter(t -> t.getScore() > 90).collect(Collectors.toList());

        assertArrayEquals(above90ListNormal.toArray(), above90ListStream.toArray());
    }

    @Test
    public void testBasicTransfer() {
        List<String> studentsNameNormal = new ArrayList<>(students.size());
        for (Student student : students) {
            studentsNameNormal.add(student.getName());
        }

        List<String> studentsNameStream = students.stream().map(Student::getName).collect(Collectors.toList());
        assertArrayEquals(studentsNameStream.toArray(), studentsNameNormal.toArray());
    }

    @Test
    public void testBasicFilterAndTransfer() {
        List<String> studentsNameNormal = new ArrayList<>(students.size());
        for (Student student : students) {
            if (student.getScore() > 90) {
                studentsNameNormal.add(student.getName());
            }
        }

        List<String> studentsNameStream = students.stream().filter(t -> t.getScore() > 90).map(Student::getName)
                .collect(Collectors.toList());
        assertArrayEquals(studentsNameStream.toArray(), studentsNameNormal.toArray());
    }

    @Test
    public void testBasicDistinct() {
        List<String> list = Arrays.asList(new String[] { "abc", "def", "hello", "Abc" });
        List<String> retList = list.stream().filter(s -> s.length() <= 3).map(String::toLowerCase).distinct()
                .collect(Collectors.toList());
        System.out.println(retList);
    }

    @Test
    public void testBasicSort() {
        List<Student> studentList = students.stream().filter(t -> t.getScore() > 90)
                .sorted(Comparator.comparing(Student::getScore).reversed().thenComparing(Student::getName))
                .collect(Collectors.toList());
        System.out.println(studentList);
    }

    @Test
    public void testBasicSkipAndLimit() {
        List<Student> studentList = students.stream().sorted(Comparator.comparing(Student::getScore).reversed()).skip(2)
                .limit(3).collect(Collectors.toList());
        System.out.println(studentList);
    }

    @Test
    public void testBasicPeek() {
        students.stream().filter(t -> t.getScore() > 90).peek(System.out::println).map(Student::getName)
                .collect(Collectors.toList());
    }

    @Test
    public void testMapToDouble() {
        double expected = 0;
        for (Student student : students) {
            expected += student.getScore();
        }
        double sum = students.stream().mapToDouble(Student::getScore).sum();
        assertEquals(expected, sum, 0);
    }

    @Test
    public void testFlatMap() {
        List<String> words = Arrays.asList("public void testFlatMap", "()");
        List<String> collect = words.stream().flatMap(word -> Arrays.stream(word.split("\\s+")))
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void testMax() {
        Optional<Student> highestScoreStudent = students.stream().max(Comparator.comparing(Student::getScore));
        if (highestScoreStudent.isPresent()) {
            System.out.println(highestScoreStudent.get());
        }
    }

    @Test
    public void testCount() {
        long numNormal = 0;
        for (Student student : students) {
            if (student.getScore() > 90) {
                numNormal += 1;
            }
        }
        long numStream = students.stream().filter(t -> t.getScore() > 90).count();
        assertEquals(numNormal, numStream);
    }

    @Test
    public void testAllMAtch() {
        boolean allPassNormal = true;
        for (Student student : students) {
            if (student.getScore() < 60) {
                allPassNormal = false;
                break;
            }
        }

        boolean allPassStream = students.stream().allMatch(t -> t.getScore() >= 60);
        assertEquals(allPassNormal, allPassStream);
    }

    @Test
    public void testFindAny() {
        Optional<Student> any = students.stream().filter(t -> t.getScore() >= 60).findAny();
        if (any.isPresent()) {
            System.out.println(any.get().getScore());
            assertTrue(any.get().getScore() >= 60);
        }
    }

    @Test
    public void testForEach() {
        students.stream().filter(t -> t.getScore() > 90).forEach(System.out::println);
    }

    @Test
    public void testToArray() {
        Student[] studentArray = students.stream().filter(t -> t.getScore() > 90).toArray(Student[]::new);
        System.out.println(Arrays.toString(studentArray));
    }

    @Test
    public void testReduceAccumulator() {
        Optional<Student> reduceStudent = students.stream().reduce((u, t) -> {
            if (u.getScore() >= t.getScore()) {
                return u;
            } else {
                return t;
            }
        });
        if (reduceStudent.isPresent()) {
            System.out.println(reduceStudent.get().getScore());
        }
    }

    @Test
    public void testReduceAccumulatorCombiner() {
        double sumScoreNormal = 0d;
        for (Student student : students) {
            sumScoreNormal += student.getScore();
        }
        double sumScore = students.stream().reduce(0d, (sum, t) -> sum += t.getScore(), (sum1, sum2) -> sum2 + sum1);
        assertEquals(sumScoreNormal, sumScore, 0);
    }

    @Test
    public void testToMap() {
        Map<String, Double> nameScoreMap = students.stream()
                .collect(Collectors.toMap(Student::getName, Student::getScore));
        System.out.println(nameScoreMap);
        students.stream().collect(Collectors.toMap(Student::getName, t -> t));
        students.stream().collect(Collectors.toMap(Student::getName, Function.identity()));
    }

    @Test(expected = IllegalStateException.class)
    public void testToMapWithSimilar() {
        Stream.of("abc", "cde", "asd", "abc")
                .collect(Collectors.toMap(Function.identity(), t -> t.length()));
    }

    @Test
    public void testToMapWithBinaryOperator() {
        Map<String, Integer> stringLengthMap = Stream.of("abc", "cdeasa", "asdaaaaaaaaa", "abc")
                .collect(Collectors.toMap(Function.identity(), t -> t.length(), (oldV, newV) -> newV));
        System.out.println(stringLengthMap);
    }

    @Test
    public void testStringCollector() {
        String prefix = "[", suffix = "]";
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(prefix);
        for (Student student : students) {
            if (sBuilder.length() > prefix.length()) {
                sBuilder.append(", ");
            }
            sBuilder.append(student.getName());
        }
        sBuilder.append(suffix);
        String namesNormal = sBuilder.toString();

        String namesStream = students.stream().map(t -> t.getName()).collect(Collectors.joining(", ", "[", "]"));
        assertEquals(namesNormal, namesStream);
    }

    @Test
    public void testGroupBy() {
        Map<String, List<Student>> gradesNormal = new HashMap<>();
        for (Student student : students) {
            String grade = student.getGrade();
            List<Student> container = gradesNormal.get(grade);
            if (container == null) {
                container = new ArrayList<>();
                gradesNormal.put(grade, container);
            }
            container.add(student);
        }

        Map<String, List<Student>> grades = students.stream().collect(Collectors.groupingBy(Student::getGrade));
        assertEquals(gradesNormal, grades);
    }

    @Test
    public void testGroupByCountMaxMin() {
        students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.counting()));
        Map<String, Optional<Student>> gradeTopStudentOptionalMap = students.stream().collect(
                Collectors.groupingBy(Student::getGrade, Collectors.maxBy(Comparator.comparing(Student::getScore))));
        System.out.println(gradeTopStudentOptionalMap);
        Map<String, Student> gradeTopStudentMap = students.stream().collect(
                Collectors.groupingBy(Student::getGrade, Collectors
                        .collectingAndThen(Collectors.maxBy(Comparator.comparing(Student::getScore)), Optional::get)));
        System.out.println(gradeTopStudentMap);
    }

    @Test
    public void testGroupByStatistic() {
        Map<String, DoubleSummaryStatistics> gradeScoreStat = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade, Collectors.summarizingDouble(Student::getScore)));
        System.out.println(gradeScoreStat);
    }

    @Test
    public void testMapping() {
        Map<String, List<String>> gradeNameMap = students.stream().collect(
                Collectors.groupingBy(Student::getGrade, Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println(gradeNameMap);
    }

    @Test
    public void testCollectingAndThen() {
        Map<String, List<Student>> sortedGradesStudentMap = students.stream().collect(
                Collectors.groupingBy(Student::getGrade, Collectors.collectingAndThen(Collectors.toList(), t -> {
                    t.sort(Comparator.comparing(Student::getScore).reversed());
                    return t;
                })));
        System.out.println(sortedGradesStudentMap);

        Map<String, List<Student>> failStudentGradeMap = students.stream().collect(
                Collectors.groupingBy(Student::getGrade, Collectors.collectingAndThen(Collectors.toList(), t -> {
                    return t.stream().filter(r -> r.getScore() < 60).collect(Collectors.toList());
                })));
        System.out.println(failStudentGradeMap);

        Map<String, List<Student>> failStudentGradeMapFiltering = students.stream().collect(Collectors
                .groupingBy(Student::getGrade, Collectors.filtering(t -> t.getScore() < 60, Collectors.toList())));
        System.out.println(failStudentGradeMapFiltering);

        Map<String, List<Student>> studentGradeTopTwoMap = students.stream()
                .sorted(Comparator.comparing(Student::getScore).reversed()).collect(
                        Collectors.groupingBy(Student::getGrade,
                                Collectors.collectingAndThen(Collectors.toList(), r -> {
                                    return r.stream().skip(0).limit(2).collect(Collectors.toList());
                                })));
        System.out.println(studentGradeTopTwoMap);
    }

    @Test
    public void testPartitioningBy() {
        Map<Boolean, List<Student>> byPass = students.stream()
                .collect(Collectors.partitioningBy(t -> t.getScore() > 60));
        System.out.println(byPass);
        Map<Boolean, Double> byPassAverage = students.stream().collect(
                Collectors.partitioningBy(t -> t.getScore() >= 60, Collectors.averagingDouble(Student::getScore)));
        System.out.println(byPassAverage);
    }

    @Test
    public void testMultilevelPartitioning(){
        Map<String, Map<Boolean, List<Student>>> multiGroup = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.partitioningBy(t->t.getScore()>=60)));
        System.out.println(multiGroup);
    }

}
