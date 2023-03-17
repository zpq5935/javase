package book.fengkuang.unit14_annotation;

import java.lang.annotation.Annotation;

public class AnnotationTest {
	@MyTag(age = 121, name = "我是Boss章")
	public void tt() {
	}

	public void tt2() {
	}

	@MyTag(age = 1234, name = "打破皮")
	public void tt3() {
	}

	@MyTag(age = 22, name = "ad考虑")
	public void tt4() {
	}

	public void tt5() {
	}

	public static void main(String[] args) throws Exception {

		Annotation[] annotations = Class.forName("test_unit14_annotation.Annotation_test01").getMethod("tt").getAnnotations();
		
		//Annotation[] annotations = Annotation_test01.class.getMethod("tt").getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println(annotation);
			if (annotation instanceof MyTag) {
				System.out.println("age:" + ((MyTag) annotation).age());
				System.out.println("age:" + ((MyTag) annotation).name());
			}

		}

	}
}
