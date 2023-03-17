package book.school.thread;

public class test_join01 {
	public static void main(String[] args) {
		MyThread02 thread01 = new MyThread02("zpq");
		MyThread02 thread02 = new MyThread02("zcp5935");
		Thread t1 = new Thread(thread01);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		Thread t2 = new Thread(thread02);
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("主线程！！！");
	}
}

class MyThread02 implements Runnable {
	private String who;

	public MyThread02(String str) {
		who = str;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep((int) (1000 * Math.random()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(who + "正在运行！！！");
		}

	}
}
