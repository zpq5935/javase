package test.multithread;

import org.junit.Test;
import util.FileUtil;

import java.io.*;
import java.util.concurrent.ExecutionException;

/**
 * 备份测试类
 */
public class ReplTest {


    /**
     * 简单本地简单备份
     *  -- Total cost time:[0小时3分钟43秒644毫秒]
     */
    @Test
    public void testSimpleRepl() throws IOException, ExecutionException, InterruptedException {
        String sourceDir = "E:\\download";
        String targetDir = "E:\\tmp\\repl\\download";
        Repl repl = new LocalFileRepl().build(sourceDir, targetDir);
        repl.repl();
    }

    /**
     * 简单本地多线程备份
     * 10 实际耗时 -- Total cost time:[0小时1分钟34秒699毫秒]
     * 5 实际耗时 -- Total cost time:[0小时1分钟41秒954毫秒]
     */
    @Test
    public void testMultiThreadRepl() throws IOException, ExecutionException, InterruptedException {
        String sourceDir = "D:\\Download\\EhViewer2";
        String targetDir = "此电脑\\Redmi Note 9\\内部存储设备\\test";
//        Repl repl = new LocalFileMultiThreadRepl().addPartitonCfg("E:\\download\\download", 15).build(sourceDir, targetDir);
        Repl repl = new LocalFileMultiThreadRepl().addPartitonCfg("D:\\Download\\EhViewer2\\debug2", 15).build(sourceDir, targetDir);
        repl.repl();
    }


}
