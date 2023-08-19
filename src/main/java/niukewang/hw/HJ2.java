package niukewang.hw;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class HJ2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Set<Integer> nums = new HashSet<>();

        int count = in.nextInt();
        while (count-- > 0) {
            nums.add(in.nextInt());
        }
        nums.stream().sorted().collect(Collectors.toList()).forEach(System.out::println);
    }

}
