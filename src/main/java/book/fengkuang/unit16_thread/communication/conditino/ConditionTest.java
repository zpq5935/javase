package book.fengkuang.unit16_thread.communication.conditino;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account2 {

	private String accountNo;
	private double balance;
	private boolean flag = false;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	public String getAccount2No() {
		return accountNo;
	}

	public void setAccount2No(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public Account2() {
		super();
	}

	public Account2(String accountNo, double balance) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public void draw(double drawMoney) {
		lock.lock();
		// flag为true则取钱，false则存钱
		try {
			if (flag) {
				System.out.println(Thread.currentThread().getName() + "取钱:" + drawMoney);
				balance -= drawMoney;
				System.out.println("账户余额:" + +balance + "\n----------");
				flag = false;
				// notifyAll();
				condition.signalAll();
			} else {
				// wait();
				condition.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void save(double saveMoney) {
		lock.lock();
		// flag为true则取钱，false则存钱
		try {
			if (!flag) {
				System.out.println(Thread.currentThread().getName() + "存钱:" + saveMoney);
				balance += saveMoney;
				System.out.println("账户余额:" + balance + "\n----------");
				flag = true;
				// notifyAll();
				condition.signalAll();
			} else {
				// wait();
				condition.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

}

class DrawThread2 extends Thread {
	private Account2 account;
	private double money;

	public DrawThread2(String threadName, Account2 account, double money) {
		super(threadName);
		this.account = account;
		this.money = money;
	}

	@Override
	public void run() {
		for (int j = 0; j < 10; j++) {
			account.draw(money);
			System.out.println("j:" + j);
		}
	}
}

class Deposit2 extends Thread {
	private Account2 account;
	private double money;

	public Deposit2(String threadName, Account2 account, double money) {
		super(threadName);
		this.account = account;
		this.money = money;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			account.save(money);
			System.out.println("i:" + i);
		}
	}
}

public class ConditionTest {
	public static void main(String[] args) {
		Account2 account = new Account2("5689-56", 0);
		DrawThread2 drawThread = new DrawThread2("取款者张三", account, 800);
		Deposit2 deposit = new Deposit2("存款者李四", account, 800);
		drawThread.start();
		deposit.start();
	}
}
