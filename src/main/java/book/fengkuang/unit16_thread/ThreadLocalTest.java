package book.fengkuang.unit16_thread;

/**
 * 线程局部变量 局部变量-顾名思义，每一个线程对该变量都有自己的副本
 * 这可以避免线程共享冲突，但是没法线程通信，要考虑好适用场景
 * 
 * @author zpq5935
 *
 */
public class ThreadLocalTest {
	public static void main(String[] args) {
		Account account = new Account("初始账户");
		new ThreadTest("县城甲", account).start();
		new ThreadTest("县城乙", account).start();
	}
}

class Account {
	private  ThreadLocal<String> name = new ThreadLocal<>();

	public Account(String name) {
		super();
		this.name.set(name);
		System.out.println("---"+name);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

}

class ThreadTest extends Thread {
	private Account account;

	public ThreadTest(String name, Account account) {
		super(name);
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			if (6 == i) {
				account.setName(getName());
			}
			System.out.println(account.getName() + i);
		}
	}
}