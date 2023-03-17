package leetcode.muliti_thread;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode.cn/problems/building-h2o/
 * 题目简述：hho，必须2氢1氧输出完成才能下一组（2氢1氧的顺序可随意）
 */
public class Sol4HHO {
    public static void main(String[] args) throws IOException {
        new Sol4HHO().beginWithTwoThread("HHOHHOHHO", new SolWithSynchronized());

    }

    public void beginWithTwoThread(String input, HHO sol) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicInteger oCount = new AtomicInteger(1);
        AtomicInteger hCount = new AtomicInteger(1);
        new Thread(() -> {
            try {
                countDownLatch.await();
                for (int i = 0; i < input.length() / 3 * 2; i++) {
                    sol.hydrogen(() -> {
                        System.out.print("H");
                    });
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread H-" + hCount.getAndIncrement()).start();
        new Thread(() -> {
            try {
                countDownLatch.await();
                for (int i = 0; i < input.length() / 3; i++) {
                    sol.oxygen(() -> {
                        System.out.print("O");
                    });
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread O-" + oCount.getAndIncrement()).start();
        countDownLatch.countDown();
//        System.in.read();
//        System.out.println();
    }

    public void beginWithMultiThread(String input, HHO sol) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicInteger oCount = new AtomicInteger(1);
        AtomicInteger hCount = new AtomicInteger(1);
        for (Character character : input.toCharArray()) {
            if (character.equals('H')) {
                new Thread(() -> {
                    try {
                        countDownLatch.await();
                        sol.hydrogen(() -> {
                            System.out.print("H");
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "Thread H-" + hCount.getAndIncrement()).start();
            } else {
                new Thread(() -> {
                    try {
                        countDownLatch.await();
                        sol.oxygen(() -> {
                            System.out.print("O");
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "Thread O-" + oCount.getAndIncrement()).start();
            }

        }
        countDownLatch.countDown();
//        System.in.read();
//        System.out.println();
    }


    interface HHO {
        void hydrogen(Runnable releaseHydrogen) throws InterruptedException;

        void oxygen(Runnable releaseHydrogen) throws InterruptedException;

    }

    static class SolWithSynchronized implements HHO {
        private int hCount = 0;
        private int oCount = 0;
        private Object lock = new Object();

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            synchronized (lock) {
                if (hCount >= 2) {
                    lock.wait();
                }
                hCount++;
                if (hCount == 2 && oCount == 1) {
                    hCount = 0;
                    oCount = 0;
                }
                releaseHydrogen.run();
                lock.notifyAll();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.

        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            synchronized (lock) {
                if (oCount >= 1) {
                    lock.wait();
                }
                oCount++;
                if (hCount == 2 && oCount == 1) {
                    hCount = 0;
                    oCount = 0;
                }
                releaseOxygen.run();
                lock.notifyAll();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
        }
    }

    static class SolWithSemaphoreAndCycle implements HHO {
        private Semaphore hSema = new Semaphore(2);
        private Semaphore oSema = new Semaphore(1);
        private CyclicBarrier cb = new CyclicBarrier(3);


        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hSema.acquire();
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            releaseHydrogen.run();
            hSema.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            oSema.acquire();
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            releaseOxygen.run();
            oSema.release();
        }

    }
}
