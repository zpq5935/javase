package book.fengkuang.unit17_netCode.multiThreadDown;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DownUtil {
	// 下载资源的路径
	private String path;
	private String targetFile;
	private int threadNum;
	// 所有的下载线程
	private DownThread[] downThreads;
	// 文件大小
	private int totalSize;

	public DownUtil(String path, String targetFile, int threadNum) {
		super();
		downThreads = new DownThread[threadNum];
		this.path = path;
		this.targetFile = targetFile;
		this.threadNum = threadNum;
	}

	public void download() throws Exception {
		URL url = new URL(path);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setConnectTimeout(5000);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accrpt",
				"image/gif,image/jpeg,image/pjpeg," + "application/x-shockwave-flash,applicaiton/xaml+xml,"
						+ "application/vnd.ms-xpsdocument,applicaiton/x-ms-xbap" + "applicaiton/,*/*");
		connection.setRequestProperty("Accept-Language", "zh_CN");
		connection.setRequestProperty("Charset", "utf-8");
		connection.setRequestProperty("Connection", "Keep-Alive");
		//
		totalSize = connection.getContentLength();
		connection.disconnect();
		int currentPartSize = totalSize / threadNum + 1;
		RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile, "rw");
		randomAccessFile.setLength(totalSize);
		randomAccessFile.close();
		for (int i = 0; i < threadNum; i++) {
			int startPos = currentPartSize * i;
			RandomAccessFile currentPartfile = new RandomAccessFile(targetFile, "rw");
			currentPartfile.seek(startPos);
			downThreads[i] = new DownThread(startPos, currentPartSize, currentPartfile);
			downThreads[i].start();
		}
	}

	public double getComplementPercent() {
		int sumSize = 0;
		for (int i = 0; i < threadNum; i++) {
			sumSize += downThreads[i].length;
		}

		return sumSize * 1.0 / totalSize;
	}

	/**
	 * 内部类-感觉这个最不熟悉了
	 * 
	 * @author zcp
	 *
	 */
	private class DownThread extends Thread {
		private int startPos;
		// 定义需要下载的字节数
		private int currentPartSize;
		private RandomAccessFile currentPartFile;
		// 定义已下载的字节数
		public int length;

		public DownThread(int starPos, int currentPartSize, RandomAccessFile currentPartFile) {
			this.currentPartSize = currentPartSize;
			this.startPos = starPos;
			this.currentPartFile = currentPartFile;
		}

		@Override
		public void run() {
			try {
				URL url = new URL(path);
				HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
				connection.setConnectTimeout(5000);
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accrpt",
						"image/gif,image/jpeg,image/pjpeg," + "application/x-shockwave-flash,applicaiton/xaml+xml,"
								+ "application/vnd.ms-xpsdocument,applicaiton/x-ms-xbap" + "applicaiton/,*/*");
				connection.setRequestProperty("Accept-Language", "zh_CN");
				connection.setRequestProperty("Charset", "utf-8");
				InputStream in = connection.getInputStream();
				//
				in.skip(startPos);
				byte[] bs = new byte[1024];
				int hasRead = 0;
				while ((hasRead = in.read(bs)) != -1 && length < currentPartSize) {
					currentPartFile.write(bs, 0, hasRead);
					length += hasRead;
				}
				//
				currentPartFile.close();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
