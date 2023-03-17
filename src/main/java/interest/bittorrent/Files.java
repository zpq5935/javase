package interest.bittorrent;

/**
 * 单个文件信息
 *
 * @author zhangchaopei
 * @version 1.0
 * @date 2020-10-2 20:54
 */


import java.util.List;


public class Files {
    private long length;
    private String md5sum;
    private List<String> path;

    public Files() {
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getMd5sum() {
        return md5sum;
    }

    public void setMd5sum(String md5sum) {
        this.md5sum = md5sum;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Files{" +
                "length=" + length +
                ", md5sum='" + md5sum + '\'' +
                ", path=" + path +
                '}';
    }
}