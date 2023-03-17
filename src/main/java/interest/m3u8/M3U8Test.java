package interest.m3u8;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;

/**
 * TODO
 *
 * @author zhangchaopei
 * @version 1.0
 * @date 2020-10-2 15:44
 */
public class M3U8Test {
    public static String TEMP_DIR = "temp";
    public static int connTimeout = 30 * 60 * 1000;
    public static int readTimeout = 30 * 60 * 1000;
    //    public static String s1 = "http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8";
//    public static String s1 = "https://play-tx-ugcpub.douyucdn2" +
//            ".cn/live/high_2737593320200921154139-upload-dde3/playlist" +
//            ".m3u8?tlink=5f76fb26&tplay=5f7787c6&exper=0&nlimit=5&us=3db02e5f44e952f3185d970731031601&sign" +
//            "=43b0ce8aae329507f464d63744f85f20&u=39111152&d=3db02e5f44e952f3185d970731031601&ct=web&vid=16317851&pt=2" +
//            "&cdn=tx";
    public static String s1 = "https://play-tx-ugcpub.douyucdn2" +
            ".cn/live/super_2737593320200921154139-upload-dde3/playlist" +
            ".m3u8?tlink=5f76fb26&tplay=5f7787c6&exper=0&nlimit=5&us=3db02e5f44e952f3185d970731031601&sign" +
            "=c28bace468321471fa43a58694657e23&u=39111152&d=3db02e5f44e952f3185d970731031601&ct=web&vid=16317851&pt=2" +
            "&cdn=tx";

    public static void main(String[] args) {
        File tfile = new File(TEMP_DIR);
        if (!tfile.exists()) {
            tfile.mkdirs();
        }

        M3U8 m3u8ByURL = getM3U8ByURL(s1);
        String basePath = m3u8ByURL.getBasepath();
        m3u8ByURL.getTsList().stream().parallel().forEach(m3U8Ts -> {
            File file = new File(TEMP_DIR + File.separator + m3U8Ts.getShortFile());
            if (!file.exists()) {// 下载过的就不管了
                FileOutputStream fos = null;
                InputStream inputStream = null;
                try {
                    URL url = new URL(basePath + m3U8Ts.getFile());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(connTimeout);
                    conn.setReadTimeout(readTimeout);
                    if (conn.getResponseCode() == 200) {
                        inputStream = conn.getInputStream();
                        fos = new FileOutputStream(file);// 会自动创建文件
                        int len = 0;
                        byte[] buf = new byte[1024];
                        while ((len = inputStream.read(buf)) != -1) {
                            fos.write(buf, 0, len);// 写入流中
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {// 关流
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.out.println("文件下载完毕!");
        mergeFiles(tfile.listFiles(), "test.ts");
    }

    public static M3U8 getM3U8ByURL(String m3u8URL) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(m3u8URL).openConnection();
            if (conn.getResponseCode() == 200) {
                String realUrl = conn.getURL().toString();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String basepath = realUrl.substring(0, realUrl.lastIndexOf("/") + 1);
                M3U8 ret = new M3U8();
                ret.setBasepath(basepath);

                String line;
                float seconds = 0;
                int mIndex;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("#")) {
                        if (line.startsWith("#EXTINF:")) {
                            line = line.substring(8);
                            if ((mIndex = line.indexOf(",")) != -1) {
                                line = line.substring(0, mIndex + 1);
                            }
                            try {
                                seconds = Float.parseFloat(line);
                            } catch (Exception e) {
                                seconds = 0;
                            }
                        }
                        continue;
                    }
                    if (line.endsWith("m3u8")) {
                        return getM3U8ByURL(basepath + line);
                    }
                    ret.addTs(new M3U8.Ts(line, seconds));
                    seconds = 0;
                }
                reader.close();

                return ret;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static boolean mergeFiles(File[] fpaths, String resultPath) {
        if (fpaths == null || fpaths.length < 1) {
            return false;
        }

        if (fpaths.length == 1) {
            return fpaths[0].renameTo(new File(resultPath));
        }
        for (int i = 0; i < fpaths.length; i++) {
            if (!fpaths[i].exists() || !fpaths[i].isFile()) {
                return false;
            }
        }
        File resultFile = new File(resultPath);

        try {
            FileOutputStream fs = new FileOutputStream(resultFile, true);
            FileChannel resultFileChannel = fs.getChannel();
            FileInputStream tfs;
            for (int i = 0; i < fpaths.length; i++) {
                tfs = new FileInputStream(fpaths[i]);
                FileChannel blk = tfs.getChannel();
                resultFileChannel.transferFrom(blk, resultFileChannel.size(), blk.size());
                tfs.close();
                blk.close();
            }
            fs.close();
            resultFileChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // for (int i = 0; i < fpaths.length; i ++) {
        // fpaths[i].delete();
        // }

        return true;
    }

}
