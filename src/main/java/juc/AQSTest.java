package juc;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {
    @Test
    public void t1() throws IOException, NoSuchFieldException, IllegalAccessException {
        Lock lock = new ReentrantLock();
        Field sync = ReentrantLock.class.getDeclaredField("sync");
        sync.setAccessible(true);
        AbstractQueuedSynchronizer abstractQueuedSynchronizer = (AbstractQueuedSynchronizer) sync.get(lock);
        System.out.println("hasQueuedThreads: " + abstractQueuedSynchronizer.hasQueuedThreads());

        new Thread(() -> {
            try {
                System.out.println("hasQueuedThreads: " + abstractQueuedSynchronizer.hasQueuedThreads());
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " in");
                Thread.sleep(1000 * 5);
                System.out.println(Thread.currentThread().getName() + " out");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "get1").start();
        new Thread(() -> {
            try {
                System.out.println("hasQueuedThreads: " + abstractQueuedSynchronizer.hasQueuedThreads());
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " in");
                Thread.sleep(1000 * 5);
                System.out.println(Thread.currentThread().getName() + " out");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "get2").start();
//        new Thread(() -> {
//            try {
//                System.out.println("hasQueuedThreads: " + abstractQueuedSynchronizer.hasQueuedThreads());
//                lock.lock();
//                System.out.println(Thread.currentThread().getName() + " in");
//                Thread.sleep(1000 * 5);
//        System.out.println(Thread.currentThread().getName() + " out");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        }, "get3").start();

        new Thread(() -> {
            try {
                while (true) {
                    System.out.println("hasQueuedThreads: " + abstractQueuedSynchronizer.hasQueuedThreads());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }, "get2").start();
        System.in.read();
    }

    /**
     * LockSupport.park
     *
     * @throws IOException
     */
    @Test
    public void t2() throws IOException, InterruptedException {
        Object lock = new Object();
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println(1);
//            LockSupport.unpark(Thread.currentThread());
                    System.out.println(2);
                    //
//                    LockSupport.park(this);
                    lock.wait();
                    //
                    System.out.println("someone call me:" + Thread.currentThread().getName());
                    System.out.println(Thread.interrupted());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "get1");
        thread.start();

        Thread.sleep(10);

        synchronized (lock) {
            // 以下 2种操作均会将线程从 wait唤醒
        LockSupport.unpark(thread);
//            thread.interrupt();
//            lock.notifyAll();
        }


        System.in.read();
    }
}
