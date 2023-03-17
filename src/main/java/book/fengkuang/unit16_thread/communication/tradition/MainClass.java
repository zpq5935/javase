package book.fengkuang.unit16_thread.communication.tradition;

/**
 * 测试存钱、取钱依次执行，不允许连续两次存或是取
 * @author zpq5935
 *
 */
class Account {

	private String accountNo;
	private double balance;
	private boolean flag = false;// 标识是否存过钱

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public Account() {
		super();
	}

	public Account(String accountNo, double balance) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public synchronized void draw(double drawMoney) {
		// flag为true则取钱，false则存钱
		try {
			if (flag) {
				System.out.println(Thread.currentThread().getName() + "取钱:" + drawMoney);
				balance -= drawMoney;
				System.out.println("账户余额:" + +balance + "\n----------");
				flag = false;
				notifyAll();
			} else {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public synchronized void save(double saveMoney) {
		// flag为true则取钱，false则存钱
		try {
			if (!flag) {
				System.out.println(Thread.currentThread().getName() + "存钱:" + saveMoney);
				balance += saveMoney;
				System.out.println("账户余额:" + balance + "\n----------");
				flag = true;
				notifyAll();
			} else {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

/**
 * 取钱线程
 * 
 * @author zpq5935
 *
 */
class DrawThread extends Thread {
	private Account account;
	private double money;

	public DrawThread(String threadName, Account account, double money) {
		super(threadName);
		this.account = account;
		this.money = money;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			account.draw(money);
		}
	}
}

/**
 * 存钱线程
 * 
 * @author zpq5935
 *
 */
class Deposit extends Thread {
	private Account account;
	private double money;

	public Deposit(String threadName, Account account, double money) {
		super(threadName);
		this.account = account;
		this.money = money;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			account.save(money);
		}
	}
}

public class MainClass {
	public static void main(String[] args) {
		Account account = new Account("5689-56", 0);
		DrawThread drawThread = new DrawThread("取款者乙", account, 800);
		Deposit deposit = new Deposit("存款者丙", account, 800);
		drawThread.start();
		deposit.start();
	}
}