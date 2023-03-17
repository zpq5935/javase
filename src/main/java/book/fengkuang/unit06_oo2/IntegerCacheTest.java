package book.fengkuang.unit06_oo2;

public class IntegerCacheTest {
	public static void main(String[] args) {
		Integer i1 = new Integer(9);
		Integer i2 = Integer.valueOf(9);
		Integer i3 = Integer.valueOf(9);
		System.out.println(i1 == i2);
		System.out.println(i3 == i2);
		//
		Integer i4 = Integer.valueOf(200);
		Integer i5 = Integer.valueOf(200);
		System.out.println(i4 == i5);
	}
}
