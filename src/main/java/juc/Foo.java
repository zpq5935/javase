package juc;

import java.util.concurrent.CountDownLatch;

/**
 * 顺序打印
 */
public class Foo {
    CountDownLatch lock1 = new CountDownLatch(1);
    CountDownLatch lock2 = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();


        new Thread(() -> {
            try {
                foo.third(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("third");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.second(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("second");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.first(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("first");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }


    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        lock1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        lock1.await();
        printSecond.run();
        lock2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        lock2.await();
        printThird.run();
    }
}
