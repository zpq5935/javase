package book.fengkuang.unit15_io.nio;

import org.junit.Test;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;

public class SimpleTest {
    @Test
    public void test1() {
        try {
            FileChannel channel = new FileOutputStream("filelock.txt").getChannel();
            FileLock lock = channel.tryLock();
            Thread.sleep(1000 * 5);
            lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void printSysCharset() {
        Properties properties = System.getProperties();
        Set<Object> keySet = properties.keySet();

        for (Object key : keySet) {
            System.out.println(key + "=" + properties.get(key));
        }
    }

    @Test
    public void listAllCharset() {
        SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entrySet = availableCharsets.entrySet();
        for (Map.Entry<String, Charset> entry : entrySet) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
