package jdk8;

import book.fengkuang.unit10_exception.ExceptionTest;
import book.fengkuang.unit18_reflect.jdkproxy.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import util.RandomUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Stream<Integer> iterate = Stream.of(1, 2, 3, 4, 5, 2, 3, 4, 11);
        iterate.forEach(System.out::println);
        try {
            justThrowException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        .filter(ele -> {
//            System.out.println("this is filter 1:" + ele);
//            if ((ele & 1) == 0)
//                return true;
//            return false;
//        }).distinct().filter(ele -> {
//            System.out.println("this is filter 2:" + ele);
//            return true;
//        })

    }

    private static void justThrowException() throws IOException {
        Objects.requireNonNull(null);
    }

    @Test
    public void justTest() throws Exception {
        List<Student> stuList = getStudents();
        List<String> collect = stuList.stream().map(Student::getName).distinct().collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }

    @Test
    public void testCollectWithGroupBy() throws Exception {
        List<Student> stuList = getStudents();
        System.out.println(Arrays.toString(stuList.toArray()));
        //
        Map<String, List<Student>> collect = stuList.stream().collect(Collectors.groupingBy(Student::getName));
        collect.entrySet().forEach(System.out::println);
    }

    private List<Student> getStudents() throws Exception {
        Map<Field, List> fileRepo = new HashMap<>();
        fileRepo.put(Student.class.getDeclaredField("name"), Arrays.asList("张三", "历史", "zhao五"));
        fileRepo.put(Student.class.getDeclaredField("age"), Arrays.asList(12, 22, 34, 55));
        fileRepo.put(Student.class.getDeclaredField("male"), Arrays.asList(true, false));
        List<List<String>> interests = Arrays.asList(Arrays.asList("game", "cp", "baseball"), Arrays.asList("game", "cp"), Arrays.asList("game", "baseball"));
        fileRepo.put(Student.class.getDeclaredField("interests"), interests);
        return (List<Student>) RandomUtil.random(100, Student.class, fileRepo);
    }

    /**
     * Stream.flatMap 就是使用内部元素返回的流以此构建新的返回流
     *
     * @throws IOException
     */
    @Test
    public void testFlatMap() throws IOException {
        //
        Path path = Paths.get("E:\\tmp\\temp\\README.md");
        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));
        words.forEach(ele -> System.out.println(ele));
        //
    }

    /**
     * Stream.reduce 归约函数
     *
     * @throws IOException
     */
    @Test
    public void testReduce() throws IOException {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        // 1
        Integer reduce = integers.stream().reduce(10, (a, b) -> a + b);
        // 2
        Integer integer = integers.stream().reduce((a, b) -> a + b).get();
        System.out.println(reduce);
        System.out.println(integer);
    }

    /**
     * Stream.anyMatch allMatch归约函数
     *
     * @throws IOException
     */
    @Test
    public void testMatch() throws IOException {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        // 1
        boolean b1 = integers.stream().anyMatch(ele -> ele > 3);
        // 2
        boolean b2 = integers.stream().allMatch(ele -> ele > 0);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(integers.parallelStream().findFirst().get());
        System.out.println(integers.parallelStream().findAny().get());
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Student {
        private String name;
        private Integer age;
        private Boolean male;
        private List<String> interests;


    }
}

