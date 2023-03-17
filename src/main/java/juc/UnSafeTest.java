//package juc;
//
//import org.junit.Test;
//import sun.misc.Unsafe;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.concurrent.locks.AbstractQueuedSynchronizer;
//
//public class UnSafeTest {
//
//    private volatile int state;
//    private volatile int state2;
//
//    @Test
//    public void t1() throws IllegalAccessException, NoSuchFieldException, IOException {
//        Field declaredField = Unsafe.class.getDeclaredFields()[0];
//        declaredField.setAccessible(true);
//        Unsafe unsafe = (Unsafe) declaredField.get(null);
//        long state = unsafe.objectFieldOffset
//                (AbstractQueuedSynchronizer.class.getDeclaredField("state"));
//        long curState1 = unsafe.objectFieldOffset
//                (UnSafeTest.class.getDeclaredField("state"));
//        long curState2 = unsafe.objectFieldOffset
//                (UnSafeTest.class.getDeclaredField("state2"));
//        UnSafeTest unSafeTest = new UnSafeTest();
//        System.out.println(unSafeTest.state);
//        unsafe.compareAndSwapInt(unSafeTest, curState1, 1,12);
//        System.out.println(unSafeTest.state);
//        System.in.read();
//    }
//}
