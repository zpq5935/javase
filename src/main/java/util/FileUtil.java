package util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    static public boolean delete(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists())
            return false;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File subFile : files)
                delete(subFile.getCanonicalPath());
        }
        return file.delete();
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        FileUtil.delete("E:\\dev\\java-projects\\javase\\此电脑");
        TimeUtil.ptTime("删除耗时", System.currentTimeMillis() - start);

    }
}
