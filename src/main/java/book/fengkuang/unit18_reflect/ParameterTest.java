package book.fengkuang.unit18_reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;

import org.junit.Test;

/**
 * 获取形参测试
 *
 * @author zpq5935
 */
public class ParameterTest {

    @Test
    public void testModify() {
        ParameterTest parameterTest = new ParameterTest();
        Class clazz = this.getClass();
        Class objCla = java.lang.Object.class;
        System.out.println(Modifier.toString(clazz.getModifiers()));
        System.out.println(clazz.isInstance(parameterTest));
        System.out.println(objCla.isInstance(parameterTest));
    }

    /**
     * 测试获取方法的参数详细,好吧没用，书上说javac -parameters，添加这个参数但是我想试了，p836
     */
    public void m1(String arString, int age, List<Long> sex) {
    }

    @Test
    public void testParameter() throws NoSuchMethodException, SecurityException {
        Class class1 = this.getClass();
        Method method = class1.getDeclaredMethod("m1", String.class, int.class, List.class);
        Parameter[] parameters = method.getParameters();
        int i = 0;
        for (Parameter parameter : parameters) {
            i++;
//			if (parameter.isNamePresent()) {
            System.out.println("第" + i + "个参数信息");
            System.out.println("\tname:" + parameter.getName());
            System.out.println("\t类型：" + parameter.getType());
            System.out.println("\t泛型类型：" + parameter.getParameterizedType());
//			}
        }
    }
}
