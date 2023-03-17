package interest.video;

import book.fengkuang.unit17_netCode.GetPostTest;

import java.io.IOException;

/**
 * http想下载视频
 *
 * @author zhangchaopei
 * @version 1.0
 * @date 2020-9-29 21:20
 */
public class VideoDownloadUtil {
    public static void main(String[] args) throws IOException {
        GetPostTest.testGet("https://play-tx-ugcpub.douyucdn2" +
                ".cn/live/super_2737593320200921154139-upload-dde3/transcode_2737593320200921154139-upload" +
                "-dde3_128440_0000014.ts?tlink=5f734a9b&tplay=5f73d73b&exper=0&nlimit=5&us" +
                "=3db02e5f44e952f3185d970731031601&sign=27348a207f9d7847a6eb4de61c6ac8aa&u=39111152&d" +
                "=3db02e5f44e952f3185d970731031601&ct=web&vid=16317851&pt=2&cdn=tx",null);
    }
}
