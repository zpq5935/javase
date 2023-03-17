package book.fengkuang.unit14_annotation;

import java.lang.reflect.Method;

public class RunTest {
	public static void main(String[] args) throws Exception {
		Method[] methods = Class.forName("test_14_annotation.Annotation_test01").getMethods();
		//Method[] methods = Annotation_test01.class.getMethods();
		for (Method method : methods) {
			System.out.println(method);
		}

	}
	
	
}
