package book.fengkuang.unit16_thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
    public static void main(String[] args) {
        Account account = new Account("账户1", 1000);
        for (int i = 0; i < 10; i++) {
            DrawThread drawThread = new DrawThread("老庄" + i, account, 300);
            drawThread.start();
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

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

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

    public void draw(double drawAmout) {
        lock.readLock().lock();
        if (this.getBalance() > drawAmout) {
            System.out.println(Thread.currentThread().getName() + "取钱成功！取出：" + drawAmout);
            this.setBalance(this.getBalance() - drawAmout);
            System.out.println("\t余额为：" + this.getBalance());
        } else {
            System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
        }
		lock.readLock().unlock();
	}
}