package book.fengkuang.unit07_basicClassLib;

import java.util.Arrays;
import java.util.Random;

public class testRandom {
	public static void main(String[] args) {
		Random random = new Random(System.currentTimeMillis());
		byte[] bytes = new byte[16];
		random.nextBytes(bytes);
		System.out.println(String.valueOf(bytes));
		System.out.println(Arrays.toString(bytes));
	}
}
