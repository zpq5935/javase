package test.multithread;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

public class LocalFileMultiThreadRepl implements MultiThreadRepl {
    List<PartitionCfg> partitionCfgs = new ArrayList<>();
    String sourceDir;
    String targetDir;
    HashSet<String> excludeDirs = null;
    ExecutorService executorService = Executors.newFixedThreadPool(15);// 后续根据分片配置动态创建线程池
    long start = 0;

    @Override
    public void repl() throws IOException, ExecutionException, InterruptedException {
        start = System.currentTimeMillis();
        if (partitionCfgs != null) {
            excludeDirs = new HashSet<>();
            for (PartitionCfg partitionCfg : partitionCfgs) {
                File tmp = new File(partitionCfg.dirName);
                if (tmp.exists() && tmp.getCanonicalPath().contains(sourceDir)) {
                    excludeDirs.add(tmp.getCanonicalPath());
                    partition(tmp.getCanonicalPath(), partitionCfg.threadCount);// 任务分解
                } else {
                    System.out.println(String.format("当前配置无效:路径=%s", partitionCfg.dirName));
                }
            }
        }
        Future<Long> mainFuture = executorService.submit(new ReplThread(new ReplTask[]{new ReplTask(sourceDir, targetDir)}));
        mainFuture.get();
        ptTime("实际耗时", System.currentTimeMillis() - start);
    }

    /**
     * 目前默认这是一个文件夹，包含多个文件；而不是说一个大文件需要分段备份
     *
     * @param canonicalPath
     * @param threadCount
     */
    private void partition(String canonicalPath, Integer threadCount) throws ExecutionException, InterruptedException, IOException {
        File dir = new File(canonicalPath);
        File[] files = dir.listFiles();
        int taskCount = files.length / threadCount;
        int remain = files.length % taskCount;
        List<Future<Long>> futures = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            int count = i == threadCount - 1 ? taskCount + remain : taskCount;
            ReplTask[] tasks = new ReplTask[count];
            for (int j = 0; j < count; j++) {
                int index = i * taskCount + j;
                File file = files[index];
                String relativePath = file.getCanonicalPath().replace(sourceDir, "");
                File thisTargetFile = new File(targetDir.endsWith(File.separator) ?
                        targetDir + relativePath : targetDir + File.separator + relativePath);
                tasks[j] = new ReplTask(file.getCanonicalPath(), thisTargetFile.getCanonicalPath());
            }
            Future<Long> submit = executorService.submit(new ReplThread(tasks));
            futures.add(submit);
        }
        for (Future future : futures)
            future.get();
        ptTime("实际耗时", System.currentTimeMillis() - start);

    }

    @Override
    public Repl build(Object source, Object target) throws IOException {
        sourceDir = new File(String.valueOf(source)).getCanonicalPath();
        targetDir = new File(String.valueOf(target)).getCanonicalPath();
        return this;
    }

    /**
     * 任务分片
     * 对于 1级子目录平均分配任务的话，较为简单；
     * 但存在某个或某几个深级子目录，需要分片的，此时任务分配较为麻烦
     */
    class PartitionCfg {
        String dirName;
        Integer threadCount;
    }

    public LocalFileMultiThreadRepl addPartitonCfg(String dirName, Integer threadCount) {
        PartitionCfg partitionCfg = new PartitionCfg();
        partitionCfg.threadCount = threadCount;
        partitionCfg.dirName = dirName;
        this.partitionCfgs.add(partitionCfg);
        return this;
    }

    class ReplTask {
        String sourceDir;
        String targetDir;

        @Override
        public String toString() {
            return "ReplTask{" +
                    "sourceDir='" + sourceDir + '\'' +
                    ", targetDir='" + targetDir + '\'' +
                    '}';
        }

        public ReplTask(String sourceDir, String targetDir) {
            this.sourceDir = sourceDir;
            this.targetDir = targetDir;

        }
    }

    class ReplThread implements Callable<Long> {
        ReplTask[] tasks;
        boolean shouleExclude = true;

        public ReplThread(ReplTask[] tasks) {
            this.tasks = tasks;
        }


        private void replInner(int depth, File sourceFile, File targetFile) throws IOException {
            if (shouleExclude && excludeDirs.contains(sourceFile.getCanonicalPath())) {
                return;
            }
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

        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            System.err.println(String.format("当前备份%s", Arrays.toString(tasks)));
            for (ReplTask task : tasks) {
                File sourceFile = new File(task.sourceDir);
                File targetFile = new File(task.targetDir);
                replInner(0, sourceFile, targetFile);

            }
            long cost = System.currentTimeMillis() - startTime;
            ptTime(Thread.currentThread().getName(), cost);
            return cost;
        }
    }
}
