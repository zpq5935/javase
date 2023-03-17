package book.fengkuang.unit06_oo2;

class Singleton01 {
	private static Singleton01 instance = null;

	private Singleton01() {
	}

	public static Singleton01 getInstance() {
		if (instance == null) {
			instance = new Singleton01();
			return instance;
		} else {
			return instance;
		}
	}
}

public class SingletonTest {
	public static void main(String[] args) {
		Singleton01 s1 = Singleton01.getInstance();
		Singleton01 s2 = Singleton01.getInstance();
		System.out.println(s1 == s2);
	}
}
