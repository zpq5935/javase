package book.fengkuang.unit07_basicClassLib;

import java.math.BigDecimal;

public class testBigDecimal {
	public static void main(String[] args) {
		BigDecimal b1 = new BigDecimal("0.05");
		BigDecimal b2 = new BigDecimal(0.05);
		BigDecimal b3 = BigDecimal.valueOf(0.01);
		System.out.println(b1 + " " + b2 + " " + b3);
		//
		System.out.println(b1.add(b3));
		System.out.println(b2.add(b3));
	}
}
