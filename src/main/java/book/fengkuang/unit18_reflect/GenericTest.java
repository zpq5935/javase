package book.fengkuang.unit18_reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 泛型类型测试
 * 
 * @author zpq5935
 *
 */
public class GenericTest {
	private Map<String, Object> map;

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		GenericTest mainApp = new GenericTest();
		Field field = mainApp.getClass().getDeclaredField("map");
		System.out.println("getType:" + field.getType());
		//
		Type genericType = field.getGenericType();
		if (genericType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			System.out.println("原始类型：" + parameterizedType.getRawType());
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			System.out.println("泛型信息：");
			for (Type type : actualTypeArguments) {
				System.out.println(type);
			}
		}
	}
}
