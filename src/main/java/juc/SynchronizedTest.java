package juc;

import org.junit.Test;

import java.util.function.IntConsumer;

public class SynchronizedTest {

    /**
     * 循环打印 https://leetcode-cn.com/problems/fizz-buzz-multithreaded/
     */
    @Test
    public void t1() throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(16);
        new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> {
                    System.out.print("buzz,");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> {
                    System.out.print("fizz,");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> {
                    System.out.print("fizzbuzz,");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.number(value -> System.out.print(value));
                System.out.print(",");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}

class FizzBuzz {
    private int n;

    Object lock = new Object();//
    int number = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        synchronized (lock) {
            do {
                while (!(number % 15 != 0 && number % 3 == 0)) {
                    if (number > n) return;
                    lock.wait();
                }
                printFizz.run();
                number++;
                lock.notifyAll();
            } while (number <= n);

        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        synchronized (lock) {
            do {
                while (!(number % 15 != 0 && number % 5 == 0)) {
                    if (number > n) return;
                    lock.wait();
                }
                printBuzz.run();
                number++;
                lock.notifyAll();
            } while (number <= n);

        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        synchronized (lock) {
            do {
                while (number % 15 != 0) {
                    if (number > n) return;
                    lock.wait();
                }
                printFizzBuzz.run();
                number++;
                lock.notifyAll();
            } while (number <= n);

        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        synchronized (lock) {
            do {
                while (number % 3 == 0 || number % 5 == 0) {
                    if (number > n) return;
                    lock.wait();
                }
                printNumber.accept(number);
                number++;
                lock.notifyAll();
            } while (number <= n);

        }
    }
}
