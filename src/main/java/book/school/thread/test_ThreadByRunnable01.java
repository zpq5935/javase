package book.school.thread;

public class test_ThreadByRunnable01 {
	public static void main(String[] args) {
		MyThread thread01 = new MyThread("zpq");
		MyThread thread02 = new MyThread("zcp5935");
		new Thread(thread01).start();
		new Thread(thread02).start();
		System.out.println("主线程！！！");
	}
}

class MyThread implements Runnable {
	private String who;

	public MyThread(String str) {
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
