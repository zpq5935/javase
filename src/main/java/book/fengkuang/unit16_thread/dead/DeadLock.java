package book.fengkuang.unit16_thread.dead;

class  A{
	public  synchronized void afn(B b){
		System.out.println("当前线程："+Thread.currentThread().getName()+" 类a方法afn");
		try{
			Thread.sleep(100);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("当前线程："+Thread.currentThread().getName()+" 企图调用b的last方法()");
		b.last();
		
	}
	public  synchronized  void last(){
		System.out.println("类A的方法last()");
	}
}
class B{
	public  synchronized void bfn(A a){
		System.out.println("当前线程："+Thread.currentThread().getName()+" 类b方法bfn");
		try{
			Thread.sleep(100);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("当前线程："+Thread.currentThread().getName()+" 企图调用a的last方法()");
		a.last();;
	}
	public  synchronized  void last(){
		System.out.println("类B的方法last()");
	}
}
public class DeadLock implements Runnable{
	private A a = new A();
	private B b = new B();
	public void init(){
		Thread.currentThread().setName("主线程");
		b.bfn(a);
		System.out.println("进入主线程之后");
	}
	public void run(){
		Thread.currentThread().setName("副线程");
		a.afn(b);
		System.out.println("进入副线程之后");
	}
	
	public static void main(String[] args) {
		DeadLock deadLock = new DeadLock();
		new Thread(deadLock).start();
		deadLock.init();
	}
}
