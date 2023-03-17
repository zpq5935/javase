package book.fengkuang.unit15_io.nio2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

/**
 * 测试 Files 工具类？
 */
public class FilesTest {
    @Test
    public void test1() throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("src", "book/fengkuang", "unit15_io", "nio2", "FilesTest.java"),
                Charset.forName("GBK"));
        allLines.forEach(System.out::println);
        //

    }

    @Test
    public void testFileStore() throws IOException {
        Files.list(Paths.get(".")).forEach(path -> System.out.println(path));
        Path path = Paths.get("C:");
        FileStore fileStore = Files.getFileStore(path);
        System.out.println(fileStore.getTotalSpace() / 1024 / 1024 / 1024 + " " + fileStore.getUsableSpace() / 1024);

    }
}
