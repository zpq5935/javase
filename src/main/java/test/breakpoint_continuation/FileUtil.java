package test.breakpoint_continuation;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.*;

/**
 * 文件下载工具
 *
 * @author zhangchaopei
 * @version 1.0
 * @date 2020-9-13 8:47
 * /
 * <p>
 * <p>
 * /**
 * 文件下载工具
 * @author zhangchaopei
 * @version 2.0
 * 使用 Callable 接口添加计时功能，输出提示更友好,每个线程内下载处最后一次有问题
 * @date 2020-9-13 8:47
 * @desc 时间效率对比，发现多线程耗时居然还更多，会不会是因为循环里面的打印语句要计算某些值的原因
 * @author zhangchaopei
 * @date 2020-9-14 8:45
 * <p>
 * 文件下载工具
 * @author zhangchaopei
 * @version 2.0
 * 使用 Callable 接口添加计时功能，输出提示更友好,每个线程内下载处最后一次有问题
 * @date 2020-9-13 8:47
 * @desc 时间效率对比，发现多线程耗时居然还更多，会不会是因为循环里面的打印语句要计算某些值的原因
 * @author zhangchaopei
 * @date 2020-9-14 8:45
 */

/**
 * 文件下载工具
 *
 * @author zhangchaopei
 * @author zhangchaopei
 * @version 2.0
 * 时间效率对比，发现多线程耗时居然还更多，会不会是因为循环里面的打印语句要计算某些值的原因
 * @date 2020-9-14 8:45
 * @desc 时间效率对比，发现多线程耗时居然还更多，会不会是因为循环里面的打印语句要计算某些值的原因（未用jdk8的块读，下次更新：1、jdk8的channel，2、断点续传）
 * 每次数据量    1024k       1024*1024k      1024*1024k*1024k        1024*1024k*1024*1024k
 * 单线程       32091ms     2860ms          5066ms                   没错但是下载不了
 * 多线程       61171ms     2595ms
 * @date 2020-9-14 8:45
 */

public class FileUtil {
    static String fileName = "bxj.mp4";
    static File srcFile = new File("E:\\tmp\\" + fileName);
    static File destFile = new File("E:\\tmp\\download\\" + fileName);

    /*
     * @param
     * @return void
     * @desc 对比下面多线程下载的时间:
     * @author zhangchaopei
     * @date 2020-9-14 8:02
     */
    @Test
    public void testDownlaod() throws IOException {
        long startTime = System.currentTimeMillis();
        RandomAccessFile source = new RandomAccessFile(srcFile, "r");
        RandomAccessFile dest = new RandomAccessFile(destFile, "rw");
        dest.setLength(srcFile.length());
        int length = 0;
        byte[] bytes = new byte[1024 * 1024 * 1024];
        while ((length = source.read(bytes)) > 0) {
            System.out.printf("写:%d\n", length);
            dest.write(bytes, 0, length);
        }
        source.close();
        dest.close();
        long endTime = System.currentTimeMillis();
        System.err.println("总耗时:" + (endTime - startTime));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FileUtil fileUtil = new FileUtil();
        fileUtil.download(srcFile, 4, destFile);

    }

    /**
     * @param srcFile    源文件
     * @param threadSize 线程数
     * @param destFile   目的文件
     * @return void
     * @desc 多线程下载文件，提供目的文件选择
     * @author zhangchaopei
     * @date 2020-9-13 8:49
     */
    public void download(String srcFile, int threadSize, String destFile) {
    }

    /**
     * @param srcFile    源文件
     * @param threadSize 线程数
     * @param destFile   目的文件
     * @return void
     * @desc 多线程下载文件，提供目的文件选择:
     * @author zhangchaopei
     * @date 2020-9-13 8:49
     */

    public void download(File srcFile, int threadSize, File destFile) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        System.err.printf("源文件:%s,大小:%-20d", srcFile.getAbsolutePath(), srcFile.length());
        ExecutorService executorService = Executors.newFixedThreadPool(threadSize);
        long oneThreadSize = srcFile.length() / threadSize;
        Future<FileDownloadInfo>[] futureArr = new Future[threadSize];
        for (int i = 0; i < threadSize; i++) {
            futureArr[i] = executorService.submit(new FileDownloadTask2(i, srcFile, destFile,
                    (int) (i * oneThreadSize), (int) oneThreadSize));
        }
        long downSize = 0;
        for (int i = 0; i < threadSize; i++) {
            downSize += futureArr[i].get().getSize();
        }
        executorService.shutdown();
        System.err.printf("共耗时:%d,预计下载:%d,总下载:%d\n", System.currentTimeMillis() - startTime, srcFile.length(),
                downSize);

    }

    public void download(String srcFile, String destFile) {
        download(srcFile, 4, destFile);
    }

}

class FileDownloadTask implements Runnable {
    // 任务编号
    private int taskID;
    // 要下载的文件
    private File srcFile;
    // 目的文件
    private File destFile;
    // 调过的字节数
    private int skipSize;
    // 下载的字节数
    private int oneThreadSize;

    public FileDownloadTask(int taskID, File srcFile, File destFile, int skipSize, int oneThreadSize) {
        this.taskID = taskID;
        this.srcFile = srcFile;
        this.destFile = destFile;
        this.skipSize = skipSize;
        this.oneThreadSize = oneThreadSize;
    }

    @Override
    public void run() {
        System.out.printf("任务编号%d开始下载->[文件：%s，跳过：%15d，待下载：%d]\n", taskID, srcFile.getAbsolutePath(), skipSize,
                oneThreadSize);
        byte[] bytes = new byte[1024];
        int len = 0;
        long allLength = 0;
        try {
            RandomAccessFile srcRandomAccessFile = new RandomAccessFile(srcFile, "rw");
            RandomAccessFile destRandomAccessFile = new RandomAccessFile(destFile, "rw");
            int readTime = (oneThreadSize % bytes.length == 0) ? oneThreadSize / bytes.length :
                    oneThreadSize / bytes.length + 1;
            srcRandomAccessFile.skipBytes(skipSize);
            destRandomAccessFile.skipBytes(skipSize);
            for (int i = 0; i < readTime; i++) {
                len = srcRandomAccessFile.read(bytes);
                allLength += len;
                System.out.printf("任务编号%d->已下载：%15d...\n", taskID, allLength);
                destRandomAccessFile.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("任务编号%d下载完成->文件：%s，跳过：%d，已下载：%d\n", taskID, srcFile.getAbsolutePath(), skipSize, allLength);

    }
}

/**
 * @author zhangchaopei
 * @desc 使用Callable带计时功能
 * @date 2020-9-13 9:57
 */
class FileDownloadTask2 implements Callable<FileDownloadInfo> {
    // 任务编号
    private int taskID;
    // 要下载的文件
    private File srcFile;
    // 目的文件
    private File destFile;
    // 调过的字节数
    private int skipSize;
    // 下载的字节数
    private int oneThreadSize;

    public FileDownloadTask2(int taskID, File srcFile, File destFile, int skipSize, int oneThreadSize) {
        this.taskID = taskID;
        this.srcFile = srcFile;
        this.destFile = destFile;
        this.skipSize = skipSize;
        this.oneThreadSize = oneThreadSize;
    }


    @Override
    public FileDownloadInfo call() throws Exception {
        long startTime = System.currentTimeMillis();
        System.err.printf("任务编号%d开始下载->[文件:%s,跳过:%-15d,待下载:%-15d]\n", taskID, srcFile.getAbsolutePath(), skipSize,
                oneThreadSize);
        byte[] bytes = new byte[1024 * 1024 * 1024];
        int len = 0;
        long allLength = 0;
        try {
            RandomAccessFile srcRandomAccessFile = new RandomAccessFile(srcFile, "rw");
            RandomAccessFile destRandomAccessFile = new RandomAccessFile(destFile, "rw");
            // RandomAccessFile的setLength方法是关键。。。
            destRandomAccessFile.setLength(srcFile.length());
            srcRandomAccessFile.skipBytes(skipSize);
            destRandomAccessFile.skipBytes(skipSize);
            // 下载次数
            int readTime = oneThreadSize / bytes.length;
            // 尾次下载数据量，算是个哨兵？？？
            int remain = oneThreadSize % bytes.length;
            // 放在首次
            for (int i = 0; i < readTime; i++) {
                len = srcRandomAccessFile.read(bytes);
                allLength += len;
                System.out.printf("任务编号%d->预计:%-15d,已下载:%-15d\n", taskID, oneThreadSize, allLength);
                destRandomAccessFile.write(bytes, 0, len);
            }
            len = srcRandomAccessFile.read(bytes);
            allLength += len;
            destRandomAccessFile.write(bytes, 0, remain);
            System.out.printf("任务编号%d->预计:%-15d,已下载:%-15d\n", taskID, oneThreadSize, allLength);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.printf("任务编号%d下载完成->文件:%s,跳过:%-15d,已下载:%-15d\n", taskID, srcFile.getAbsolutePath(), skipSize,
                allLength);
        long endTime = System.currentTimeMillis();
        FileDownloadInfo info = new FileDownloadInfo(endTime - startTime, allLength);
        return info;
    }
}

class FileDownloadInfo {
    // 耗时
    private long time;
    // 下载文件大小
    private long size;

    FileDownloadInfo(long time, long size) {
        this.time = time;
        this.size = size;
    }

    public FileDownloadInfo() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}