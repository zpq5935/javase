package book.fengkuang.unit15_io.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class Process_test01 {

    /**
     * 本程序读取其他进程的数据
     *
     * @throws IOException
     */
    @Test
    public void t1() throws IOException {
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
        Process process = Runtime.getRuntime().exec("jar");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(new String(line.getBytes(), "utf-8"));
        }
    }


}
