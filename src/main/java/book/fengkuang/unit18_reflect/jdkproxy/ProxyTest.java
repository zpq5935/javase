//package book.fengkuang.unit18_reflect.jdkproxy;
//
//import org.junit.Test;
//import sun.misc.ProxyGenerator;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * 测试 JDK 动态代理
// *
// * @author zpq5935
// */
//public class ProxyTest {
//    public static void main(String[] args) throws FileNotFoundException {
//
//        //
//        Person target = new Student("zhangsan");
//        target.drink();
//        System.out.println("--代理后--");
//        // 保存代理类
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        Person proxyObject = (Person) ProxyFactory.getProxyObject(target);
//        proxyObject.drink();
//        ((Student) target).setName("111");
//        target.drink();
//        proxyObject.drink();
//    }
//
//    @Test
//    public void tt1() {
//        byte[] bytes = ProxyGenerator.generateProxyClass("PersonProxy", Student.class.getInterfaces());
//        String path = "E:\\tmp\\proxyClass\\PersonProxy.class";
//        File file = new File(path);
//        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            fos.write(bytes);
//            System.out.println("over");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
