package book.fengkuang.unit17_netCode;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class GetPostTest {

    public static void testGet(String urlStr, String param) {
        try {
            System.out.println("url:\t" + urlStr + "\nparam:\t" + param);
            URL url = new URL(urlStr + "?" + param);
            URLConnection connection = url.openConnection();
            connection.connect();
            Map<String, List<String>> headerFields = connection.getHeaderFields();
            for (String key : headerFields.keySet()) {
                System.out.println(key + " : " + headerFields.get(key));
            }
            System.out.println("--------------------");
            List<String> strings = headerFields.get("Content-Type");
            boolean hasVideo = false;
            for (String s : strings) {
                if (s.contains("video")) {
                    hasVideo = true;
                    break;
                }
            }
            if (hasVideo) {
                System.out.println("video");
                InputStream inputStream = connection.getInputStream();
                OutputStream out = new FileOutputStream("D://download.mp4");
                byte[] data = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(data)) != 0) {
                    out.write(data, 0, length);
                }
            } else {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), Charset.forName("gbk")));
                String aLine = null;
                while ((aLine = bufferedReader.readLine()) != null) {
                    System.out.println(aLine);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testPost(String urlStr, String param) {
        URL url;
        try {
            url = new URL(urlStr);
            URLConnection openConnection = url.openConnection();
            openConnection.setDoInput(true);
            openConnection.setDoOutput(true);
            //
            OutputStream outputStream = openConnection.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.print(param);
            //
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream(),
                    "gbk"));
            String aLine = null;
            while ((aLine = bufferedReader.readLine()) != null) {
                System.out.println(aLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        // testGet("https://zhidao.baidu.com/question/11724962.html", null);
        testPost("https://zhidao.baidu.com/question/11724962.html", null);
    }
}
