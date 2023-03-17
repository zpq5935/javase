package jvm.unit2;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 Java堆 OutOfMemoryError
 * @author zhangchaopei
 * @version 1.0
 * @date 2020-10-4 11:02
 */
public class HeapOOM {
    byte[] data = new byte[1024];
    /**
     *
     * -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) {
        List list = new ArrayList();
        while (true)
            list.add(new HeapOOM());
    }
}
