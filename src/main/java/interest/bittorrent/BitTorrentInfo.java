package interest.bittorrent;

/**
 * bittorret文件信息
 *
 * @author zhangchaopei
 * @version 1.0
 * @date 2020-10-2 20:51
 */

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhao.wu on 2016/12/8.
 */
public class BitTorrentInfo {
    public static List<String> keyList;

    static {
        String[] keys = {"announce", "announce-list", "creation date", "comment", "created by",
                "info", "length", "md5sum", "name", "piece length", "pieces", "files", "path"};
        keyList = Arrays.asList(keys);
    }

    /**
     * 服务器
     */
    private String announce;
    /**
     * 备用服务器列表
     */
    private List<String> announceList;
    /**
     * 种子创建日期
     */
    private long creationDate;
    /**
     * 注释
     */
    private String comment;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 文件主要信息
     */
    private Info info;

    public BitTorrentInfo() {
    }


    /*
     * @param key
     * @param value
     * @return void
     * @desc 设置信息
     * @author zhangchaopei
     * @date 2020-10-2 23:06
     */
    public void setValue(String key, Object value) throws Exception {
        if (!keyList.contains(key)) {
            throw new Exception("not contains this key: " + key);
        } else {
            switch (key) {
                case "announce":
                    this.setAnnounce(value.toString());
                    break;
                case "announce-list":
                    this.getAnnounceList().add(value.toString());
                    break;
                case "creation date":
                    this.setCreationDate(Long.parseLong(value.toString()));
                    break;
                case "comment":
                    this.setComment(value.toString());
                    break;
                case "created by":
                    this.setCreateBy(value.toString());
                    break;
                case "length":
                    List<Files> filesList1 = this.getInfo().getFiles();
                    if (filesList1 != null) {
                        Files files = this.getInfo().getFiles().get(filesList1.size() - 1);
                        files.setLength(Long.parseLong(value.toString()));
                    } else {
                        this.getInfo().setLength(Long.parseLong(value.toString()));
                    }
                    break;
                case "md5sum":
                    List<Files> filesList2 = this.getInfo().getFiles();
                    if (filesList2 != null) {
                        Files files = this.getInfo().getFiles().get(filesList2.size() - 1);
                        files.setMd5sum(value.toString());
                    } else {
                        this.getInfo().setMd5sum(value.toString());
                    }
                    break;
                case "name":
                    this.getInfo().setName(value.toString());
                    break;
                case "piece length":
                    this.getInfo().setPiecesLength(Long.parseLong(value.toString()));
                    break;
                case "pieces":
                    this.getInfo().setPieces((byte[]) value);
                    break;
                case "path":
                    List<Files> filesList3 = this.getInfo().getFiles();
                    Files files3 = filesList3.get(filesList3.size() - 1);
                    files3.getPath().add(value.toString());
                    break;
            }
        }
    }

    public static List<String> getKeyList() {
        return keyList;
    }

    public static void setKeyList(List<String> keyList) {
        BitTorrentInfo.keyList = keyList;
    }

    public String getAnnounce() {
        return announce;
    }

    public void setAnnounce(String announce) {
        this.announce = announce;
    }

    public List<String> getAnnounceList() {
        return announceList;
    }

    public void setAnnounceList(List<String> announceList) {
        this.announceList = announceList;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "BitTorrentInfo{" +
                "announce='" + announce + '\'' +
                ", announceList=" + announceList +
                ", creationDate=" + creationDate +
                ", comment='" + comment + '\'' +
                ", createBy='" + createBy + '\'' +
                ", info=" + info +
                '}';
    }
}