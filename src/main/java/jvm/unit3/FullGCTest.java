package jvm.unit3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zhangchaopei
 * @version 1.0
 * @date 2020-10-5 11:11
 */
public class FullGCTest {
    private final static int _1M = 1024 * 1024;

    /**
     * VM 参数：-verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * 测试堆内新生代的 Edon内存不够时，minor gc
     */
    public void testGc() throws InterruptedException, IOException {
//        System.gc();
        int length = 20;
        byte[][] data = new byte[length][];
        for (int i = 0; i < length; i++)
            data[i] = new byte[_1M * 1];
        System.in.read();
    }

    public void test() {
        byte[] data = new byte[_1M * 11];
//        int length = 1;
//        byte[][] data = new byte[length][];
//        for (int i = 0; i < length; i++)
//            data[i] = new byte[_1M * 11];
    }


    /**
     * 1、System.gc()
     * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public void testSystemGC() {
        System.gc();
    }
    //2、分配失败，年轻代进入老年代时老年代不足（满足年龄要求或大对象）

    //3、内存担保时，老年代剩余内存不足
    //4、历代进入老年代平均大小大于当前老年代剩余大小

    /**
     * -verbose:gc -Xms50m -Xmx50m -Xmn30m -XX:+PrintGCDetails -XX:SurvivorRatio=3  15:5:5
     */
    public void testAvgBigger() {
        List<byte[]> data = new ArrayList<>();
        while (true) {
            addSize(data, 1);
        }
    }

    private void addSize(List<byte[]> data, int size) {
        data.add(new byte[size]);
        new Thread(() -> {
            byte[] gbg;
            for (int i = 0; i < 14; i++)
                gbg = new byte[_1M];
        }).start();
    }
    //5、方法区空间不足

}
