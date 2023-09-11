package book.fengkuang.unit18_reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

	public static void main(String[] args) throws Exception {
		GenericTest mainApp = new GenericTest();
		Field field = mainApp.getClass().getDeclaredField("map");
		System.out.println("getType:" + field.getType());
		//
		Type genericType = field.getGenericType();
		if (genericType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			printParameterType(parameterizedType);
		}

		printMethod(GenericTest.class.getDeclaredMethod("xx"));
		printMethod(GenericTest.class.getDeclaredMethod("xx2"));
		printMethod(GenericTest.class.getDeclaredMethod("xx3"));

	}

	private static void printParameterType(ParameterizedType parameterizedType){
		System.out.println("原始类型：" + parameterizedType.getRawType());
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		System.out.println("泛型信息：");
		for (Type type : actualTypeArguments) {
			System.out.println(type);
		}
	}


	private static void printMethod(Method method){
		Class<?> returnType = method.getReturnType();
		System.out.println(returnType.getName());
		//
		Type genericReturnType = method.getGenericReturnType();
		if(genericReturnType instanceof ParameterizedType){
			printParameterType((ParameterizedType) genericReturnType);
		}
	}

	/**
	 * @return 此处返回的 {@link Type} 数组内的 ? 对应的类型为 {@link java.lang.reflect.TypeVariable}
	 */
	public <S,T> Map<S,T> xx(){
		return null;
	}

	/**
	 * @return 此处返回的 {@link Type} 数组内的 ? 对应的类型为 {@link java.lang.Class}
	 */
	public  Map<String,Object> xx2(){
		return null;
	}

	/**
	 * @return 此处返回的 {@link Type} 数组内的 ? 对应的类型为 {@link java.lang.reflect.WildcardType}
	 */
    public Map<?, ?> xx3() {
		return null;
	}
}
