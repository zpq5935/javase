package interview.feizhu;


import java.util.concurrent.Semaphore;
// 第4题
public class Sol4 {

    private int n;
    private Semaphore aSema = new Semaphore(1);
    private Semaphore bSema = new Semaphore(0);
    private Semaphore cSema = new Semaphore(0);

    public Sol4(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        Sol4 sol4 = new Sol4(10);
        new Thread(() -> {
            try {
                sol4.print1A();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                sol4.print1B();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                sol4.print1C();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void print1A() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            aSema.acquire();
            System.out.print('A');
            bSema.release();
        }

    }

    public void print1B() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            bSema.acquire();
            System.out.print('B');
            cSema.release();
        }
    }

    public void print1C() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            cSema.acquire();
            System.out.print('C');
            aSema.release();
        }
    }

}
