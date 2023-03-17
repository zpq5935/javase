package book.fengkuang.unit16_thread.communication.conditino;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 测试java5提供的Lock、ReadWriteLock
 * @author zpq5935
 *
 */
class Account {
	private String accountNo;
	private double amount;
	private final ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public Account(String accountNo, double amount) {
		super();
		this.accountNo = accountNo;
		this.amount = amount;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void draw(double amount) {
		lock.lock();
		try {
			System.out.print(Thread.currentThread().getName() + " 欲取钱：" + amount + "---->结果：");
			if (amount > this.amount) {
				System.out.println(" 余额不足！现金：" + this.amount + "  劳资先不取钱了，看看有钱再拿吧");
				condition.signalAll();
				condition.await();
			} else {
				System.out.print(" 取钱成功！现金：" + this.amount);
				this.setAmount(this.amount - amount);
				System.out.println("取钱后，余额：" + this.amount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void save(double saveAmout) {
		lock.lock();
		System.out.print(Thread.currentThread().getName() + " 正打算存钱");
		try {
			if (this.getAmount() > 3000) {
				System.out.println("---->哎！那个混小子还算有点良心，钱还够那我就先存了t_t");
				condition.signalAll();
				condition.await();
			} else {
				System.out.print("---->" + Thread.currentThread().getName() + " 存钱：" + saveAmout);
				this.setAmount(this.getAmount() + saveAmout);
				System.out.println("---->余额：" + this.getAmount());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}

class DrawThread extends Thread {
	private Account accout;
	private double drawAmout;

	public DrawThread(String threadName, Account account, double drawAmout) {
		super(threadName);
		this.drawAmout = drawAmout;
		this.accout = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 7; i++)
			accout.draw(drawAmout);
	}
}

class SaveThread extends Thread {
	private Account accout;
	private double saveAmout;

	public SaveThread(String threadName, Account account, double saveAmout) {
		super(threadName);
		this.saveAmout = saveAmout;
		this.accout = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			accout.save(saveAmout);
		}

	}
}

public class ConditionTest2 {
	public static void main(String[] args) {
		Account account = new Account("1102", 10000);
		DrawThread thread1 = new DrawThread("取钱者甲", account, 3000);
		SaveThread thread2 = new SaveThread("存钱者老母亲", account, 1000);
		thread1.start();
//		thread2.start();
	}
}
