package book.fengkuang.unit18_reflect;

import org.junit.Test;

public class ClassInitializerTest {

    /**
     * 测试使用直接量（final）<br>
     * 结果： 并不会执行初始化阶段（静态加载块）
     *
     * @Description TODO
     * @author zhangchaopei
     * @date 2020年6月25日
     */
    @Test
    public void testUserFinal() {
        System.out.println(TestClass.NAME);
    }
}

class TestClass {
	public final static String NAME = "我是name";

	static {
		System.out.println("执行 类加载块");
	}
}