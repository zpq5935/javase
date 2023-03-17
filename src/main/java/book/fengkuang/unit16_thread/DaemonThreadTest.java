package book.fengkuang.unit16_thread;

public class DaemonThreadTest extends Thread{
	@Override
	public void run() {
		super.run();
		for(int i=0; i<1000; i++){
			System.out.println(getName()+" "+i);
		}
	}
	public static void main(String[] args) {
		DaemonThreadTest td1 = new DaemonThreadTest();
		td1.setDaemon(true);
		td1.start();
		for(int i=0; i<10; i++){
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
	}
}
