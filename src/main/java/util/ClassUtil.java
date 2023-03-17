//package util;
//
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//
//public class ClassUtil {
//    public static Class getClass(String className) throws Exception {
//        ClassLoader classLoader = ClassUtil.class.getClassLoader();
//        Class<?> aClass = classLoader.loadClass(className);
//        return aClass;
//    }
//
//    public static void main(String[] args) throws Exception {
//        Class aClass = ClassUtil.getClass("java.util.stream.StreamOpFlag");
//        System.out.println(aClass);
//        Field is_distinct = aClass.getDeclaredField("IS_DISTINCT");
//        Field NOT_DISTINCT = aClass.getDeclaredField("NOT_DISTINCT");
//        is_distinct.setAccessible(true);
//        NOT_DISTINCT.setAccessible(true);
//        System.out.println(is_distinct.get(aClass));
//        System.out.println(NOT_DISTINCT.get(aClass));
//    }
//}
