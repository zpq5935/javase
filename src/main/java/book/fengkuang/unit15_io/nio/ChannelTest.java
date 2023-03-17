package book.fengkuang.unit15_io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;

import org.junit.Test;

public class ChannelTest {
    public static void main(String[] args) {

    }

    @Test
    public void testFileChannel() throws Exception {
        File file = new File("plainText/poem.text");
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(MapMode.READ_ONLY, 0, file.length());
        System.out.println(Charset.forName("utf-8").decode(mappedByteBuffer));
        //
        RandomAccessFile file2 = new RandomAccessFile("plainText/poemCopy.text","rw");
        FileChannel outChannel = file2.getChannel();
        channel.transferTo(0, channel.size(),outChannel);//
    }

    @Test
    public void testFileChannel2() throws Exception {
        File file = new File("out.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        ByteBuffer dst = ByteBuffer.allocate(200);
        while (channel.read(dst) != -1) {
            dst.flip();// 为读做准备
            System.out.println(Charset.forName("utf-8").decode(dst));
            dst.clear();
        }
    }
}
