package book.fengkuang.unit16_thread.threadpool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * java8新增，为多cpu准备 ForkJoinPool ForkJoinTask->Future
 * RecursiveAction->ForkJoinTask RecursiveTask<T>->ForkJoinTask
 *
 * @author zpq5935
 */
public class ForkJoinPoolTest {
    @Test
    public void tt2() throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new PrintAction(0, 300));
        pool.awaitTermination(2, TimeUnit.SECONDS);
    }

    @Test
    public void tt() throws InterruptedException, ExecutionException {
        int[] array = new int[300];
        int sum = 0;
        for (int i = 0; i < 300; i++) {
            array[i] = i;
            sum += i;
        }
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> task = pool.submit(new PrintTask(0, 300, array));
        System.out.println(task.get());
        System.out.println(sum);
    }

    /**
     * 打野0-300，限定每个线程只能打印50个数字，将任务分解成小任务 继承RecursiveAction，无返回
     *
     * @author zpq5935
     */
    private class PrintAction extends RecursiveAction {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private final static int THRESHOLD = 50;
        private int start;
        private int end;

        public PrintAction(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start < THRESHOLD) {
                for (int i = start; i < end; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            } else {
                int middle = (start + end) / 2;
                PrintAction left = new PrintAction(start, middle);
                PrintAction right = new PrintAction(middle, end);
                left.fork();
                right.fork();
            }

        }
    }

    /*
     *
     * 测试可以返回值的RecursiveTask
     */
    private class PrintTask extends RecursiveTask<Integer> {
        private static final long serialVersionUID = 1L;
        private final static int THRESHOLD = 50;
        private int start;
        private int end;
        private int[] array;

        public PrintTask(int start, int end, int[] array) {
            super();
            this.start = start;
            this.end = end;
            this.array = array;
        }

        @Override
        protected Integer compute() {
            if (end - start < THRESHOLD) {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    sum += array[i];
                }
                return sum;
            } else {
                int middle = (start + end) / 2;
                PrintTask left = new PrintTask(start, middle, array);
                PrintTask right = new PrintTask(middle, end, array);
                left.fork();
                right.fork();
                return left.join() + right.join();
            }
        }

    }
}
