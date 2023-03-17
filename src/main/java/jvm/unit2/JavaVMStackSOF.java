package jvm.unit2;

/**
 * 测试 虚拟机栈+本地方法栈 StackOverflowError
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    private void addLen() {
        stackLength++;
        addLen();
    }

    /**
     * -Xss128k(每个线程可占用的虚拟机栈大小)
     * 128 988
     * 628 11101
     * @param args
     */
    public static void main(String[] args) {
        JavaVMStackSOF main = new JavaVMStackSOF();
        try {

            main.addLen();
        } catch (Throwable e) {
            System.out.println(main.stackLength);
            throw e;
        }

    }
}
