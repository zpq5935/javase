package test.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
    static long KB = 1024;
    static long MB = 1024 * 1024;
    static long MB_50 = MB * 50;

    public static void main(String[] args) {
        List<String> strings = whichBiggerThan(MB_50, "E:\\dev\\git\\java-projects");
        strings.forEach(System.out::println);
    }

    public static List<String> whichBiggerThan(long bytes, String rootDir) {
        List<String> retList = new ArrayList<>();
        File root = new File(rootDir);
        File[] files = root.listFiles();
        Arrays.stream(files).forEach(file -> bfs(file, retList, bytes));
        return retList;
    }

    /**
     * 查找指定文件夹下大小大于bytes的文件
     *
     * @param file
     * @param retList
     * @param bytes
     */
    private static void bfs(File file, List<String> retList, long bytes) {
        if (file.isFile()) {
            checkSize(retList, file, bytes);
            return;
        }
        File[] subFiles = file.listFiles();
        for (File subFile : subFiles) {
            if (subFile.isFile())
                checkSize(retList, subFile, bytes);
            else
                bfs(subFile, retList, bytes);
        }

    }



    public static void checkSize(List<String> fileList, File file, long bytes) {
        if (file.length() > bytes)
            fileList.add(file.getAbsolutePath());
    }
}
