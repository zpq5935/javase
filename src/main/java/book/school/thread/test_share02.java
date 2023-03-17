package book.school.thread;


class MyThread04 implements Runnable{
	private int tickets = 10;
	@Override
	public void run() {
		while(tickets>=0){
			System.out.println(Thread.currentThread().getName()+"-剩余票数："+tickets--);
			try{
				Thread.sleep((int)(Math.random()*1000));
			}catch(InterruptedException e){e.printStackTrace();}
		}
	}

}
public class test_share02 {
	public static void main(String[] args) {
		MyThread04 thread01 = new MyThread04();
		new Thread(thread01,"1号售票机").start();
		new Thread(thread01,"35号售票机").start();
	}

}
