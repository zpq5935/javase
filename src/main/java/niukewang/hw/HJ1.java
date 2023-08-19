package niukewang.hw;

import java.util.Scanner;

public class HJ1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String string = in.nextLine();
        int length = 0;
        for (char c : string.toCharArray()) {
            if (c == ' ') length = 0;
            else length++;
        }
        System.out.println(length);
    }

}
