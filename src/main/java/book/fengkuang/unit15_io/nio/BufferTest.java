package book.fengkuang.unit15_io.nio;

import java.nio.CharBuffer;

import org.junit.Test;

public class BufferTest {

    @Test
    public void t1() {
        CharBuffer charBuffer = CharBuffer.allocate(8);
        printBuffer("初始化", charBuffer);
        //
        charBuffer.put('a');
        charBuffer.put('b');
        charBuffer.put('c');
        printBuffer("\nput3个元素后", charBuffer);
        //
        charBuffer.flip();
        printBuffer("\nflip后", charBuffer);
        //
        System.out.println(charBuffer.get(0));
        printBuffer("\n取一个数据后", charBuffer);
        //
        charBuffer.clear();
        printBuffer("\nclear", charBuffer);

    }

    private void printBuffer(String msg, CharBuffer charBuffer) {
        System.out.println(msg);
        System.out.println("position:" + charBuffer.position());
        System.out.println("limit:" + charBuffer.limit());
        System.out.println("capacity:" + charBuffer.capacity());
    }
}
