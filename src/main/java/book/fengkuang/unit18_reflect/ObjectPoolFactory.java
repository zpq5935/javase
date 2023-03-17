package book.fengkuang.unit18_reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;

import org.junit.Test;

/**
 * 模仿从文件读取类名，创建对应对象实例。
 */
public class ObjectPoolFactory {
	private Map<String, Object> objPool = new HashMap<>();

	// public ObjectPoolFactory(Map<String, Object> objPool) {
	// super();
	// this.objPool = objPool;
	// }

	public Object getObject(String key) {
		return objPool.get(key);
	}

	/**
	 * 原先使用的是本地文件系统读取文件，这样的话打包后非但文件不会包含进去，而且文件是读取不到的。
	 * 
	 * @Description TODO
	 * @param filename
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @author zhangchaopei
	 * @date 2020年6月26日
	 */
	public void initObjPool(String filename)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream fis = classLoader.getResourceAsStream(filename);
			// FileInputStream fis = new FileInputStream(filename);
			Properties properties = new Properties();
			properties.load(fis);
			for (String name : properties.stringPropertyNames()) {
				objPool.put(name, createObject(properties.getProperty(name)));
				System.out.println(name + ": " + properties.getProperty(name));
			}
		} catch (IOException e) {
			System.out.println("读取" + filename + "异常");
			e.printStackTrace();
		}

	}

	public Object createObject(String name)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> clazz = Class.forName(name);
		return clazz.newInstance();
	}

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ObjectPoolFactory pool = new ObjectPoolFactory();
		// pool.initObjPool("classpath:plainText\\sett.txt");
		pool.initObjPool("resources/sett.txt");
		System.out.println(pool.getObject("a"));
		System.out.println(pool.getObject("b"));
		System.out.println(pool.getObject("c"));
	}

	/**
	 * 反射利用 Constructor 对象构建对象
	 * 
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void newObjectByCnst() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class clazz = Class.forName("javax.swing.JFrame");
		Constructor constructor = clazz.getConstructor(String.class);
		Object object = constructor.newInstance("窗口1");
		System.out.println(object);
	}

	/**
	 * 测试Class 的newInstance<br>
	 * class的newInstance只能使用默认无参构造器
	 * @Description TODO
	 * @author zhangchaopei
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @date 2020年6月26日
	 */
	@Test
	public void testClassNewinstance() {
		try {
			JFrame instance2 = JFrame.class.newInstance();
			System.out.println(instance2);
			TestClass instance = TestClass.class.newInstance();
			System.out.println(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class TestClass {
		private String name;

		public TestClass(String name) {
			super();
			this.name = name;
		}

	}
}
