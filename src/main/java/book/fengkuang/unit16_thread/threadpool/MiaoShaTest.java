package book.fengkuang.unit16_thread.threadpool;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 秒杀测试
 *
 * @author zhangchaopei
 * @version 1.0
 * @date 2020-9-10 19:51
 */
public class MiaoShaTest {
    // 手机数量
    private static int mobileCount = 10;

    Object lock = new Object();

    @Test
    public void tt() throws InterruptedException, IOException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            MiaoShaTask task = new MiaoShaTask("客户" + i);
            threadPool.submit(task);
        }
        threadPool.shutdown();
        System.in.read();
    }

    private class MiaoShaTask implements Runnable {

        private String name;

        public MiaoShaTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + "，正在参与抢手机活动");
            synchronized (lock) {
                if (mobileCount > 0) {
                    // 此处休眠1毫秒，即可表现出手机的超发
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mobileCount--;
                    System.out.println("/********恭喜，" + name + "抢到手机一部********/");
                } else {
                    System.err.println("抱歉，" + name + "未能抢到手机");
                }
            }
        }
    }
}

