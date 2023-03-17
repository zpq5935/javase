package book.school.thread;

public class test_thread01 extends Thread {
	
	private String who;

	public test_thread01(String str) {
		who = str;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				sleep((int) (1000 * Math.random()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(who + "正在运行");
		}
	}
	
	public static void main(String[] args) {
		test_thread01 thread01 = new test_thread01("Jack");
		test_thread01 thread012 = new test_thread01("ROse");
		thread01.start();
		thread012.start();
		System.out.println("main线程！！！");
	}
}
