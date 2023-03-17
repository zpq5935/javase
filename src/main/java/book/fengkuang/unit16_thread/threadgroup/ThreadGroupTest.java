package book.fengkuang.unit16_thread.threadgroup;

class MYThread extends Thread {
	public MYThread(String name) {
		super(name);
	}

	public MYThread(ThreadGroup threadGroup, String name) {
		super(threadGroup, name);
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(getName() + " 线程的变量i：" + i);
		}
	}
}

public class ThreadGroupTest {
	public static void main(String[] args) {
		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		System.out.println("主线程组的名字：" + threadGroup.getName());
		System.out.println("主线程组是否为后台线程：" + threadGroup.isDaemon());
		new MYThread("主线程组的新线程").start();
		//
		ThreadGroup tg = new ThreadGroup("新线程组");
		tg.setDaemon(true);
		System.out.println("新线程组是否为后台线程：" + tg.isDaemon());
		MYThread therad1 = new MYThread(tg, "新线程组的线程甲");
		therad1.start();
		new MYThread(tg, "新线程组的线程乙").start();
		;
	}
}
