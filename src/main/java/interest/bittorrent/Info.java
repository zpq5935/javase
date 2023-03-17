package interest.bittorrent;

/**
 * BitTorrent内info对应
 *
 * @author zhangchaopei
 * @version 1.0
 * @date 2020-10-2 20:53
 */


import java.util.Arrays;
import java.util.List;


public class Info {
    private String name;
    private byte[] pieces;
    private long piecesLength;
    /**
     * 文件长度
     */
    private long length;
    private String md5sum;
    private List<Files> files;

    public Info() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPieces() {
        return pieces;
    }

    public void setPieces(byte[] pieces) {
        this.pieces = pieces;
    }

    public long getPiecesLength() {
        return piecesLength;
    }

    public void setPiecesLength(long piecesLength) {
        this.piecesLength = piecesLength;
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

    public List<Files> getFiles() {
        return files;
    }

    public void setFiles(List<Files> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "Info{" +
                "name='" + name + '\'' +
                ", pieces=" + Arrays.toString(pieces) +
                ", piecesLength=" + piecesLength +
                ", length=" + length +
                ", md5sum='" + md5sum + '\'' +
                ", files=" + files +
                '}';
    }
}
