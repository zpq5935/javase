package book.fengkuang.unit17_netCode;

import java.io.IOException;
import java.net.InetAddress;

import org.junit.Test;

public class InetAddressTest {
	@Test
	public void tt1() throws IOException {
		InetAddress inetAddress = InetAddress.getLocalHost();
		System.out.println(inetAddress.getHostName());
		System.out.println(inetAddress.getHostAddress());
		/*
		 * ---------------
		 */
		System.out.println("---------------");
		InetAddress inetAddress2 = InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 });
		System.out.println(inetAddress2.getCanonicalHostName());
		System.out.println(inetAddress2.isReachable(2000));
		/*
		 * ---------------
		 */
		System.out.println("---------------");
		InetAddress ip = InetAddress.getByName("www.baidu.com");
		System.out.println(ip.isReachable(2000));
		System.out.println(ip.getCanonicalHostName());
		System.out.println(ip.getHostName());
		System.out.println(ip.getHostAddress());
	}

}
