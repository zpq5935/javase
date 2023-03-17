package book.fengkuang.unit16_thread.threadpool;

import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 简单测试线程池<br>
 * 阿里手册建议使用 ThreadPoolExecutor 而不是 Executors 创建线程池
 *
 * @author zpq5935
 */
public class ExecutorsTest {
    public static void main(String[] args) {

    }


    /**
     * newFixedThreadPool
     * -Xms20m -Xmx20m
     *
     * @return void
     * @author
     * @date 2020-9-10 15:53
     */
    @Test
    public void testFixedThreadPool() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
//        Runnable comman = () -> {
//            System.out.println("线程：【" + Thread.currentThread().getName() + "】，");
//        };
//        for (int i = 0; i < 10; i++)
//            threadPool.submit(comman);

        for (int i = 0; i < 100000000; i++) {
            threadPool.execute(() -> {
                System.out.println("start");
                String payload = IntStream.rangeClosed(1, 1000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID().toString();

                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(payload);
                }
            });
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    /**
     * newCachedThreadPool
     *
     * @return
     * @params []
     * @author zhangchaopei
     * @date 2020-9-10 15:56
     */
    @Test
    public void testCachedThreadPool() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Runnable comman = () -> {
            System.out.println("线程：【" + Thread.currentThread().getName() + "】，");
        };
        for (int i = 0; i < 10; i++)
            threadPool.submit(comman);
        threadPool.shutdown();
    }

    /**
     * newSingleThreadExecutor
     */
    @Test
    public void testSingleThreadPool() throws IOException {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Runnable comman = () -> {
            System.out.println("线程：【" + Thread.currentThread().getName() + "】");
        };
        int num = Integer.MAX_VALUE;
        for (int i = 0; i < num; i++)
            threadPool.submit(comman);
        System.in.read();
    }

    /**
     * newSingleThreadScheduledExecutor
     *
     * @param
     * @return void
     * @desc 测试调度线程池
     * @author zhangchaopei
     * @date 2020-9-10 16:33
     */
    @Test
    public void testScheduleExecutorsService() throws InterruptedException {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++)
            threadPool.scheduleAtFixedRate(() -> {
                System.out.println(Thread.currentThread().getName() + " Start");
                String timeStr = new SimpleDateFormat("HH:mm:ss").format(new Date());
                System.out.println(timeStr + "线程：【" + Thread.currentThread().getName() + "】，");
                System.out.println(Thread.currentThread().getName() + " End");

            }, 2, 2, TimeUnit.SECONDS);
        Thread.sleep(60 * 1000);
    }


    /**
     * @return void
     * @desc 使用ThreadPoolExecutor创建线程池
     * @params []
     * @author zhangchaopei
     * @date 2020-9-10 15:57
     */
    @Test
    public void testThreadPoolExecutor() throws IOException {
        ExecutorService executorService =
                new ThreadPoolExecutor(3, 5, 3000,
                        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(6), Executors.defaultThreadFactory(), (Runnable r, ThreadPoolExecutor executor) -> {
                    System.out.println("Task " + r.toString() +
                            " rejected from " +
                            executor.toString());
                });
        for (int i = 0; i < 15; i++) {
            int finalI = i;
            executorService.submit(() -> {
                System.out.println(String.format("int=%d,ThreadName=%s,Time=%s,Start", finalI, Thread.currentThread().getName(), LocalTime.now()));
                Thread.sleep(2000);
                System.out.println(String.format("int=%d,ThreadName=%s,Time=%s,End", finalI, Thread.currentThread().getName(), LocalTime.now()));
                throw new RuntimeException("13333");
            });
        }
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(executorService);
        }, 1, 3, TimeUnit.SECONDS);
        System.in.read();
    }


}
