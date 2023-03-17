package book.fengkuang.unit16_thread.threadpool;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * TODO
 *
 * @author zhangchaopei
 * @version 1.0
 * @date 2020-9-10 18:27
 */
public class CallableTest {
    /**
     * @param
     * @return void
     * @desc 测试有返回值的线程池
     * @author zhangchaopei
     * @date 2020-9-10 18:28
     */
    @Test
    public void testFuture() throws ExecutionException, InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> result = executorService.submit(new MyTask(10, 22));
        System.out.println(result.get());// 这个方法是阻塞的
        System.out.println(simpleDateFormat.format(new Date()));
//        System.out.println(result.cancel(true));
    }

    class MyTask implements Callable<Integer> {
        private int left;
        private int right;

        MyTask(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public Integer call() throws Exception {
//            Thread.sleep(2000);
            return left + right;
        }
    }
}
