package juc;

import java.util.concurrent.TimeUnit;

public class VolatileTest {
    private static  int number = 0;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (number == 0) {
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        number = 1;
    }
}
