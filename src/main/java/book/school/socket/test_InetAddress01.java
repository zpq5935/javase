package book.school.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class test_InetAddress01 {
	InetAddress myIpAddress = null;
	InetAddress serverIpAddress = null;
	public static void main(String[] args) {
		test_InetAddress01 search = new test_InetAddress01();
		System.out.println("本机IP："+search.myIp());
		System.out.println("本机IP："+search.serverIp());
	}
	public InetAddress myIp(){
		try{
			myIpAddress =  InetAddress.getLocalHost();
		}catch(UnknownHostException e)
		{e.printStackTrace();}
		return myIpAddress;
	}
	
	public InetAddress serverIp(){
		try{
			serverIpAddress = InetAddress.getByName("www.baidu.com");
		}catch(UnknownHostException e)
		{e.printStackTrace();}
		return serverIpAddress;
	}
}
