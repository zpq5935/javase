package juc;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    @Test
    public void testH2O() throws InterruptedException, IOException {
        String input = "OOHHHH";
        int oCout = 0;
        int hCout = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i, i + 1).equalsIgnoreCase("o")) oCout++;
            else if (input.substring(i, i + 1).equalsIgnoreCase("h")) hCout++;
        }
        H2O h2O = new H2O();

        int finalHCout = hCout;
        new Thread(() -> {
            for (int i = 0; i < finalHCout; i++) {
                try {
                    h2O.hydrogen(() -> System.out.print("H"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        int finalOCout = oCout;
        new Thread(() -> {
            for (int i = 0; i < finalOCout; i++) {
                try {
                    h2O.oxygen(() -> System.out.print("O"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (Thread.activeCount() > 1) {
            Thread.sleep(10);
        }
    }


    static class H2O {


        Semaphore hSemaphore = new Semaphore(2);
        Semaphore oSemaphore = new Semaphore(0);
        int hcount = 0;

        public H2O() {

        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hSemaphore.acquire(1);
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            hcount++;
            if (hcount == 2) {
                hcount = 0;
                oSemaphore.release(1);
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            oSemaphore.acquire(1);
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            hSemaphore.release(2);
        }
    }
}
