package book.school.thread;


class MyThread03 implements Runnable{
	private String who;
	private int tickets = 10;
	@Override
	public void run() {
		while(tickets>=0){
			try{
				Thread.sleep((int)(Math.random()*1000));
			}catch(InterruptedException e){e.printStackTrace();}
			System.out.println(who+"-剩余票数："+tickets--);
		}
	}
	public MyThread03(String str) {
		who = str;
	}
}
public class test_share01 {
	public static void main(String[] args) {
		MyThread03 thread01 = new MyThread03("一号售票机");
		MyThread03 thread02 = new MyThread03("五号售票机");
		new Thread(thread01).start();
		new Thread(thread02).start();
	}
}
