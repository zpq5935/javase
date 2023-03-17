//package jvm.unit2;
//
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//
///**
// * 测试 直接内存异常
// */
//public class DirectMemoryOOM {
//    private static final int _1MB = 1024 * 1024;
//
//    /**
//     * -Xms20M -XX:MaxDirectMemorySize=10M（加完后，IDE都退出了。。。）
//     *
//     * @param args
//     */
//    public static void main(String[] args) throws IllegalAccessException {
//        // 方式1：无法直接获取 Unsave实例，设计者希望只有rt.jar内的类能使用Unsafe
////        Unsafe unsafe = Unsafe.getUnsafe();
//        // 方式2：通过反射
//        Field declaredField = Unsafe.class.getDeclaredFields()[0];
//        declaredField.setAccessible(true);
//        Unsafe unsafe = (Unsafe) declaredField.get(null);
//        long n = 0;
//        try {
//            while (true) {
//                Thread.sleep(100);
//                unsafe.allocateMemory(_1MB *10);
//                n++;
//            }
//
//        } catch (Error e) {
//            System.err.println(n);
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
