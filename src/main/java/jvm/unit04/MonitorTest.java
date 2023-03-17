package jvm.unit04;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MonitorTest {
    static class OOMObject {
        public byte[] pld = new byte[64 * 1024];
    }

    /**
     * -Xms100m -Xmx100m -XX:+UseSerialGC
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        scanner.next();
    }

    public static void fillHeap(int num) throws InterruptedException {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        while (!scanner.next().equals("1")) {
        }
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }
}
