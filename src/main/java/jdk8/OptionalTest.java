package jdk8;

import java.util.Optional;

/**
 * 测试使用Optional类
 * 
 * @author 22517
 *
 */
public class OptionalTest {
    public static void main(String[] args) {
        String name = null;
        Optional<String> optional = Optional.of(name);
        System.out.println(optional.get());
    }
}
