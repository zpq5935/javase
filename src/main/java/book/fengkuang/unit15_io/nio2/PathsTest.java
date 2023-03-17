package book.fengkuang.unit15_io.nio2;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

/**
 * 测试 Paths 工具类
 */
public class PathsTest {
	@Test
	public void test1() {
		Path path = Paths.get(".");
		System.out.println(path);
		System.out.println(path.getNameCount());
		System.out.println(path.getParent());
		System.out.println(path.getRoot());
	}
}
