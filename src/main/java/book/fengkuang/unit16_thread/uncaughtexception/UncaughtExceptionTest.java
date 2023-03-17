package book.fengkuang.unit16_thread.uncaughtexception;

class ExceptionHandler01 implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("处理器1-->" + "线程:" + t + "异常:" + e);

	}
}

class ExceptionHandler02 implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("处理器2-->" + "线程:" + t + "异常:" + e);

	}
}

class ExceptionHandler03 implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("处理器2-->" + "线程:" + t + "异常:" + e);

	}
}
class MyThread extends Thread {
	public void run() {
		int a = 5 / 0;
		System.out.println("MyThread--OVER");
	};
}

public class UncaughtExceptionTest {
	public static void main(String[] args) {
		MyThread thread01 = new MyThread();
		ThreadGroup threadGroup = new ThreadGroup("父线程组");// 以主线程所在线程组为父线程主创建线程主
//		thread01.setUncaughtExceptionHandler(new ExceptionHandler02());
//		MyThread.setDefaultUncaughtExceptionHandler(new ExceptionHandler01());
		thread01.start();
	}
}
