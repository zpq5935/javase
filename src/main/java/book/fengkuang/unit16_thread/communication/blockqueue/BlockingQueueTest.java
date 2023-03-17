package book.fengkuang.unit16_thread.communication.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer extends Thread{
	private BlockingQueue<String> blockingQueue ;
	private String[]  strArr = new String[]{"Java","Spring","Linux"};
	@Override
	public void run() {
		for (int i = 0; i < 7; i++) {
			try {
				sleep(500);
				System.out.println(getName() + "生产者正在生产元素");
				blockingQueue.put(strArr[i % 3]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(getName() + "生产者完成生产：" + blockingQueue);
		}
	}
	public Producer(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
}
class Consumer extends Thread{
	private BlockingQueue<String> blockingQueue ;
	@Override
	public void run() {
		for (int i = 0; i < 21; i++) {
			try {
				sleep(500);
				System.out.println(getName() + "消费者正在消费元素");
				blockingQueue.take();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(getName() + "消费者完成消费：" + blockingQueue);
		}
	}
	public Consumer(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
}
public class BlockingQueueTest {
	public static void main(String[] args) {
		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);
		new Producer(blockingQueue).start();
		new Producer(blockingQueue).start();
		new Producer(blockingQueue).start();
		new Consumer(blockingQueue).start();
	}
}
