package jvm.unit3;

public class JvmArgTest {
    /**
     * -verbose:gc -XX:PretenureSizeThreshold=1m -Xms20m -Xmx20m -Xmn18m -XX:SurvivorRatio=1  -XX:+PrintGCDetails
     * 6:2:2 貌似对于收集器 -XX:+UseParallelGC 没有用
     */
    public static void main(String[] args) {
        byte[] data = new byte[1024 * 1024 * 5];
    }
}
