package book.school.thread;

/**
 * 临界资源、同步资源
 * 临界区、临界代码
 * @author zcp
 *	1.同步语句
 *	Synchronized(对象)
 *	{
 *		临界代码段
 *	}
 *	2.同步方法
 *	public synchronized 返回类型	方法名()
 *	{
 *		方法体
 *	}
 */
class MyBank {
	private static int sum = 2000;
	public synchronized static void take(int k){
		int temp = sum;
		temp-=k;
		try{
			Thread.sleep((int)(1000*Math.random()));
		}catch(InterruptedException e){e.printStackTrace();}
		sum = temp;
		System.out.println("sum="+sum);
	}
}
class Customer extends Thread{
	@Override
	public void run() {
		for(int i=0; i<4; i++)
			MyBank.take(100);
	}
}
public class test_synchronized {	
	public static void main(String[] args) {
		new Customer().start();
		new Customer().start();
	}
}
