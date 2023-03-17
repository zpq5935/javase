package book.school.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL类
 * 
 * @author zcp
 * 
 */
public class test_socket01 {

	public static void main(String[] args) {
		String urlName = "http://www.edu.cn/index.html";
		if (args.length > 0) {
			urlName = args[0];
			System.out.println("1111111111111");
		}

		new test_socket01().display(urlName);
	}
	
	public void display(String urlName) {
		try {
			URL url = new URL(urlName);
			InputStreamReader inReader = new InputStreamReader(url.openStream());
			BufferedReader bufferedReader = new BufferedReader(inReader);
			String aLine;
			while ((aLine = bufferedReader.readLine()) != null)
				System.out.println(aLine);
		} catch (MalformedURLException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

	}

}
