package juc;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class MapTest {
    CountDownLatch countDownLatch = new CountDownLatch(1);
    final int size = 10000;
    private Map<Integer, Integer> map = new HashMap<>();

    @Test
    public void test1() throws IOException {
        HashMapThread thread0 = new HashMapThread();
        HashMapThread thread1 = new HashMapThread();
        HashMapThread thread2 = new HashMapThread();
        HashMapThread thread3 = new HashMapThread();
        HashMapThread thread4 = new HashMapThread();
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        countDownLatch.countDown();
        while (Thread.activeCount() > 2) {
        }
        int count = 0;
        for (int i = 0; i < size; i++)
            if (map.containsKey(i)) count++;
        System.out.println("count:" + count);
        System.out.println(map.size());
    }


    class HashMapThread extends Thread {

//    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

        @SneakyThrows
        @Override
        public void run() {
            countDownLatch.await();
            for (int i = 0; i < size; i++) {
                map.put(i, i);
            }
            System.out.println(Thread.currentThread().getName() + " over");
        }
    }
}

