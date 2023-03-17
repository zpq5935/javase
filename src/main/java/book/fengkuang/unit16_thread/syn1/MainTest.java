package book.fengkuang.unit16_thread.syn1;

public class MainTest {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			Account account = new Account("0012", 1000);
			new DrawThread("甲", account, 600).start();
			new DrawThread("乙", account, 600).start();
		}
	}
}

class DrawThread extends Thread {
	private Account account;
	private double drawAmout;

	public DrawThread(String threadName, Account account, double drawAmout) {
		super(threadName);
		this.account = account;
		this.drawAmout = drawAmout;
	}

	@Override
	public void run() {
		account.draw(drawAmout);

	}
}

class Account {
	// 账号id
	private String accountNo;
	// 余额
	private double balance;

	public Account() {

	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Account(String accountNo, double balance) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public int hashCode() {
		return accountNo.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj != null && obj.getClass() == Account.class) {
			Account target = (Account) obj;
			return target.getAccountNo().equals(accountNo);
		}
		return false;
	}

	public synchronized void draw(double drawAmout) {
		if (this.getBalance() > drawAmout) {
			System.out.println(Thread.currentThread().getName() + "取钱成功！取出：" + drawAmout);
			/*
			 * try { Thread.sleep(1); } catch (Exception e) {
			 * e.printStackTrace(); }
			 */
			this.setBalance(this.getBalance() - drawAmout);
			System.out.println("余额为：" + this.getBalance());
		} else {
			System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
		}
	}
}