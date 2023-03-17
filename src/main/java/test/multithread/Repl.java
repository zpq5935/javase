package test.multithread;

import util.TimeUtil;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * todo 同步进度条添加，同步任务概览（文件数量、文件大小）
 */
public interface Repl {
    void repl() throws IOException, ExecutionException, InterruptedException;

    Repl build(Object source, Object target) throws IOException;

    default void info(int depth, String source, String target) {
        if (!log())
            return;
        String format = String.format("%-50s ---> %s", source, target);
        while (depth-- > 0)
            format = "\t" + format;
        System.out.println(format);
    }

    default boolean log() {
        return false;
    }

    default void ptTime(String tag, long ms) {
        TimeUtil.ptTime(tag, ms);
    }

}
