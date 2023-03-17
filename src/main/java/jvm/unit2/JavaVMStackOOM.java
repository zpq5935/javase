package jvm.unit2;

/**
 * 测试 虚拟机栈+本地方法栈 OutOfMemoryError
 *
 * @author zhangchaopei
 * @version 1.0
 * @date 2020-10-4 12:12
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
//        long count = 0;
        while (true) {
//            System.out.println(count++);
            Thread thread = new Thread(() -> dontStop());
            thread.start();
        }
    }

    /**
     * -Xss2M 小心，电脑会死机的,每个线程栈大小
     *
     * @param args
     */
    public static void main(String[] args) {
        new JavaVMStackOOM().stackLeakByThread();
    }
}
