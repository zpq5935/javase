package book.fengkuang.unit18_reflect.classloadertest;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.junit.Test;

/**
 * 类加载器测试
 *
 * @author zpq5935
 */
public class ClassLoaderTest {
    /**
     * 根加载器，该错误无视即可
     */
    @Test
    public void testRootClassLoader() {
//		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
//		for (URL url : urls) {
//			System.out.println(url);
//		}
    }

    @Test
    public void testClassLoader() throws IOException {
        System.out.println(new ClassLoaderTest());
        // 获取系统类加载器
        ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
        System.err.println("系统类加载器：" + sysClassLoader);
        //
        System.err.println("系统类加载加载路径：");
        Enumeration<URL> enumeration = sysClassLoader.getResources("");
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
        // 系统保存的Properties
        System.err.println("拓展类加载器：" + sysClassLoader.getParent());
        System.out.println("----------System Properties----------");
        Properties properties = System.getProperties();
        Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            System.out.println(key + ": " + properties.getProperty(key));
        }
    }
}
