package book.fengkuang.unit16_thread;

/**
 * 所谓让步给同或高优先级的线程，执行下来发现并没什么软用。。。
 * 
 * @author zpq5935
 *
 */
public class YieldTest implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			if (10 == i) {
				Thread.yield();
			}
		}
	}

	public static void main(String[] args) {
		YieldTest obj = new YieldTest();
		YieldTest obj2 = new YieldTest();
		Thread t1 = new Thread(obj, "高级线程");
		t1.setPriority(Thread.MAX_PRIORITY);
		Thread t2 = new Thread(obj2, "低级线程");
		t2.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start();
	}

}
