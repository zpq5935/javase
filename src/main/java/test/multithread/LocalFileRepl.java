package test.multithread;

import java.io.*;

/**
 * 本地文件单线程备份
 */
public class LocalFileRepl implements Repl {
    String sourceDir;
    String targetDir;

    @Override
    public void repl() throws IOException {
        File sourceFile = new File(sourceDir);
        File targetFile = new File(targetDir);
        long startTime = System.currentTimeMillis();
        replInner(0, sourceFile, targetFile);
        ptTime("",System.currentTimeMillis() - startTime);
    }

    @Override
    public Repl build(Object source, Object target) {
        sourceDir = String.valueOf(source);
        targetDir = String.valueOf(target);
        return this;
    }

    private void replInner(int depth, File sourceFile, File targetFile) throws IOException {
        if (sourceFile.isDirectory()) {
            String targetFilePath = targetFile.getPath();
            File[] subFiles = sourceFile.listFiles();
            for (File file : subFiles) {
                File thisTargetFile = new File(targetFilePath.endsWith(File.separator) ?
                        targetFilePath + file.getName() : targetFilePath + File.separator + file.getName());
                info(depth + 1, file.getCanonicalPath(), thisTargetFile.getCanonicalPath());
                replInner(depth + 1, file, thisTargetFile);
            }
        } else {
            targetFile.getParentFile().mkdirs();
            targetFile.createNewFile();
            FileInputStream inputStream = new FileInputStream(sourceFile);
            OutputStream outputStream = new FileOutputStream(targetFile);
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(data)) > 0) {
                outputStream.write(data, 0, len);
            }
        }
    }
}
